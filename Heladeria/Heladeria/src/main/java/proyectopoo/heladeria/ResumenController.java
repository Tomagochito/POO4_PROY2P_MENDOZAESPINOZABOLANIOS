/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.*;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static proyectopoo.heladeria.VentanaToppingsController.numPedido;
import static proyectopoo.heladeria.VentanaToppingsController.total;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ResumenController implements Initializable {

    
    /**
     * Inicializa el controlador
     * @param url Localizacion del FXML
     * @param rb Recursos del controlador
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(FileInputStream in = new FileInputStream(ManejoArchivos.rutaImagenes+"banner.png")){
            Image i = new Image(in);
            imgvfondo.setImage(i);
        }catch(IOException ioe){
            System.out.println("Erro al cargar imagenes");
        }
        try(FileInputStream in = new FileInputStream(ManejoArchivos.rutaImagenes+"resumen.gif")){
            Image i = new Image(in);
            imgvgif.setImage(i);
        }catch(IOException ioe){
            System.out.println("Erro al cargar imagenes");
        }
        cargarinfo();
    }    
    @FXML
    private ListView listview;
    @FXML
    private Label total;
    @FXML
    private ImageView imgvfondo;
    @FXML
    private Button btneliminar;
    @FXML
    private Button btncancelar;
    @FXML
    private Button btnconfirmar;
    @FXML
    private ImageView imgvgif;
    private Label msj=new Label();
    /**
     * Total calculado en la ventana resumen
     */
    public static double totalResumen=VentanaToppingsController.total;
    /**
     * Metodo al accionar el boton eliminar
     */
    @FXML
    public void eliminar(){
      Stage popup=new Stage();
      VBox contenido=new VBox();
      contenido.setSpacing(80);
      contenido.setAlignment(Pos.CENTER);
      msj.setText("¿Estas seguro de eliminar este componente?");
      HBox botones= new HBox();
      Button aceptar=new Button("Aceptar");
      Button cancelar=new Button("Cancelar");
      botones.setSpacing(80);
      botones.setAlignment(Pos.CENTER);
      botones.getChildren().addAll(aceptar,cancelar);
      contenido.getChildren().addAll(msj,botones);
      popup.setScene(new Scene(contenido));
      popup.show();
      aceptar.setOnAction((t) -> {eliminarComponente();
      });
      cancelar.setOnAction((t) -> {popup.close();
      });
    }
    /**
     * Metodo al accionar el boton cancelar
     */
    @FXML
    public void cancelar(){
      Stage popup=new Stage();
      VBox contenido=new VBox();
      contenido.setSpacing(80);
      contenido.setAlignment(Pos.CENTER);
      Label L=new Label("¿Estas seguro de cancelar el pedido?");
      HBox botones= new HBox();
      Button si=new Button("Si");
      Button no=new Button("No");
      botones.setSpacing(80);
      botones.setAlignment(Pos.CENTER);
      botones.getChildren().addAll(si,no);
      contenido.getChildren().addAll(L,botones);
      popup.setScene(new Scene(contenido));
      popup.show();
      si.setOnAction((t) -> {cambiarVentana(popup);
      });
      no.setOnAction((t) -> {popup.close();
      });
    }
    /**
     * Metodo al accionar el boton confirmar
     */
    @FXML
    public void confirmar(){
        Pedido.serializarPedido(App.pedidoactual, "pedido" + String.valueOf(numPedido) + ".bin");
        guardarPedido();
    }
    /**
     * Cargar informacion de la ventana anterior
     */
    public void cargarinfo(){
        //cargar el total
        total.setText(String.valueOf(totalResumen));
        //cargar listview
        String base= "Base: "+App.pedidoactual.getBase1().getNombreBase();
        listview.getItems().add(base);
        for(Sabor s:App.pedidoactual.getListasabores()){
            String sabor="Sabor: "+s.getNombreSabor();
            listview.getItems().add(sabor);
        }
        for(Topping t:App.pedidoactual.getListatopping()){
            String topping="Topping: "+t.getNombreTopping();
            listview.getItems().add(topping);
        }
        
    }
    /**
     * Elimina el componente del ListView
     */
    public void eliminarComponente(){
        String s=(String)listview.getSelectionModel().getSelectedItem();
        String datos[]=s.trim().split(":");
        String componente = datos[0];
        String nombreSabor= datos[1].substring(1);
        if(componente.equals("Sabor")){
            if (App.pedidoactual.getListasabores().size()>1){
                listview.getItems().remove(s); 
                for(Sabor sa : App.pedidoactual.getListasabores()){
                    if (sa.getNombreSabor().equals(nombreSabor)){
                        App.pedidoactual.getListasabores().remove(sa);
                        totalResumen-=sa.getPrecioSabor();
                    }
                }
            }
            else{
                msj.setText("Solo puedes eliminar 1 sabor");
            }
        }
        else{
            msj.setText("Solo puedes eliminar sabores de tu pedido");
        }
        total.setText(String.valueOf(totalResumen));
    }
      /**
     * Metodo llamado por botonContinuar() para guardar el pedido en el archivo
     * pedido.txt, tambien calcula el total sin incluir el IVA.
     */
    public void guardarPedido() {
        
         try(BufferedWriter bf=new BufferedWriter(new FileWriter(ManejoArchivos.rutaArchivos+"pedido.txt",true))){
            String linea=numPedido+", "+VentanaInicioController.clienteActual.getUsuario()+", "+totalResumen+"\n";
            bf.write(linea);
        }catch(IOException ioe){

                System.out.println(ioe.getMessage());
            }
        numPedido--;
        try{
            App.setRoot("Pago");
        }catch(IOException ioe){
            System.out.println("Error al cambiar ventana");
        }
    }  
    /**
     * Metodo que cancela el pedido
     * @param s Popup de cancelar
     */
    public void cambiarVentana(Stage s){
        s.close();
        try{
            App.setRoot("VentanaInicio");
        }catch(IOException ioe){
            System.out.println("Error al cambiar la ventana");
        }
    }
}
