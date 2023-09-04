/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;
//Guillermo
import Modelo.ManejoArchivos;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Guillermo Mendoza
 */
public class OrdenController implements Initializable {

    /**
     * 
     * @param url Localizacion del FXML
     * @param rb Recursos del controlador
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(FileInputStream in = new FileInputStream(ManejoArchivos.rutaImagenes+"banner.png")){
            Image image=new Image(in);
            imgvdeco.setImage(image);
        }catch(Exception e){
            System.out.println("Erro al cargar imagen");
        }
        try(FileInputStream in2 = new FileInputStream(ManejoArchivos.rutaImagenes+"F.gif")){
            Image image2=new Image(in2);
            imgvgif.setImage(image2);
        }catch(Exception e){
            System.out.println("Erro al cargar imagen");
        }
        lblnumPedido.setText("Tu pedido es el #"+(VentanaToppingsController.numPedido+1)+" pronto te llamaremos.");
        task();
    }
    @FXML
    private StackPane stackpane;
    @FXML
    private ImageView imgvdeco;
    @FXML
    private VBox vb1;
    @FXML
    private Label lbl1;
    @FXML
    private Label lblnumPedido;
    @FXML
    private VBox vb2;
    @FXML
    private Label lblcontador;
    @FXML
    private ImageView imgvgif;
    /**
     * Metodo que se ejecuta para la correcta utilizacion de Hilos en javafx
     */
    public void task(){
        Thread hilo=new Thread(new Runnable() {
            @Override
            public void run() {
                contador();
            }
        });
        hilo.setDaemon(true);
        hilo.start();
    }
    /**
     * Metodo utilizado para actualizar el label del contador y este cumpla su correcta funcion en javafx
     */
    public void contador(){
        for(int i=5;i>=0;i--){
            String contador="Esta ventana se cerrara en "+i+" segundos...";
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lblcontador.setText(contador);
                }
            });
            try{
                Thread.sleep(1000);
            }catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        try{
            App.setRoot("VentanaInicio");
        }catch(IOException ioe){
            System.out.println("Error al cambiar ventana");
        }
    }
}