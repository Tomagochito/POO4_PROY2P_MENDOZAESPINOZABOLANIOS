/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.Base;
import static Modelo.Base.bases;
import Modelo.IncompleteStageException;
import Modelo.ManejoArchivos;
import Modelo.Pedido;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 *
 * @author tomas
 */
public class VentanaBasesController implements Initializable {
    
    @FXML
    private VBox nodoVbox;
    @FXML
    private HBox nodoHbox31;
    @FXML
    private HBox nodoHbox32;
    @FXML
    private HBox nodoHbox33;
    @FXML
    private VBox nodoVbox331;
    
    @FXML
    private VBox nodoVbox332;
 
    @FXML
    private VBox nodoVbox333;

    @FXML
    private HBox nodoHbox34;
    private Label nodoLabeltotalAcumulandose;
    @FXML
    private Label nodoLabelprecio1;
    @FXML
    private Label nodoLabelprecio2;
    @FXML
    private Label nodoLabelprecio3;
    @FXML
    private Canvas nodoCanvas;
    @FXML
    private Button botonContinuar;
    @FXML
    private Label total;
    @FXML
    private ToggleButton tboton331;
    @FXML
    private ToggleButton tboton332;
    @FXML
    private ToggleButton tboton333;
    
    public static Base baseElegida;
    boolean amarillo_a_verde;

     /**
     * Initializes the controller class.
     **/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        GraphicsContext gc = nodoCanvas.getGraphicsContext2D();
        dibujarHeladoDerretido(gc);
        total.setText("0.00");
        Collections.sort(bases);
        tboton331.getStyleClass().add("estiloBotonAmarillo");
        tboton332.getStyleClass().add("estiloBotonAmarillo");
        tboton333.getStyleClass().add("estiloBotonAmarillo");
        System.out.println(baseElegida);
        // aqui he hecho este if para lo normal, es decir, para las 3 bases por defecto;
        //si se agregan mas bases deben crearse nuevos 
        //contenedores , botones, labels, e incluso la imagen de la base, de manera dinamica
     
        if(bases.size()<4){
            insertarDatosBoton_Precio("baseHelado.png",tboton331,nodoLabelprecio1,0);
            insertarDatosBoton_Precio("baseVegano.png",tboton332,nodoLabelprecio2,1);
            insertarDatosBoton_Precio("baseYogurt.png",tboton333,nodoLabelprecio3,2);
        }
        // completar mas tarde xd
        else{System.out.println("la lista es mayor a 3");}

    }    
    
    private void dibujarHeladoDerretido(GraphicsContext gc) {
        double centroX = 411;
        double inicioY = 0; // Mantenemos el signo negativo
        double escalaX = 100;
        double escalaY = 80;
        gc.setFill(Color.hsb(180, 1, 1, 0.5));
        gc.beginPath();
        for (double x = -Math.PI * 2; x <= Math.PI * 2; x += 0.01) {
            double y = -Math.abs(Math.sin(3 * x)) * escalaY; // Cambiamos el signo a negativo
            double xPixel = centroX + x * escalaX;
            double yPixel = inicioY - y; // Mantenemos el signo negativo
            gc.lineTo(xPixel, yPixel);
        }
        gc.closePath();
        gc.fill();
    }

         
    @FXML
    private void continuar(ActionEvent event) {
        try{    
             
            if(baseElegida==null){
                throw new IncompleteStageException("Debe escoger una base");             
            }
            else{
                System.out.println("La base escogida antes de pasar a la otra ventana fue: "+baseElegida.getNombreBase());
                //----------Prueba
                App app = new App();
                Pedido pedido1 = app.getPedidoactual();
                pedido1.setBase1(baseElegida);
                
                
                
                //----------
                try{
                App.setRoot("VentanaSabores");
                }
                catch(IOException ioe){
                    System.out.println("Ocurrio un error al intentar cambiar a la escena de sabores");
                }         
            }        
        }
        catch(IncompleteStageException ise){
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Número de bases faltantes");
              alert.setHeaderText(null);
              alert.setContentText(ise.getMessage());
              alert.showAndWait();
        }
    }


    // dentro del boton se agrega una imagen, esta dificilisimo hacer que la calidad se mantenga,
    //no se porque luce tan punteada la imagen
    public void insertarDatosBoton_Precio(String archivo,ToggleButton tboton,Label l,int indice){
       try(FileInputStream fis = new FileInputStream (new File(ManejoArchivos.rutaArchivos+archivo) )){    
        Image imagen = new Image(fis);        
        ImageView imagenEnBoton = new ImageView(imagen);
        imagenEnBoton.setPreserveRatio(true);       
        // Ajustar el tamaño de la imagen dentro del botón
        imagenEnBoton.fitWidthProperty().bind(tboton.widthProperty().divide(2.5));
        imagenEnBoton.fitHeightProperty().bind(tboton.heightProperty());
        tboton.setGraphic(imagenEnBoton);
        tboton.setText( ManejoArchivos.capitalizar ( bases.get(indice).getNombreBase() ) );       
        tboton.setGraphicTextGap(10);
        l.setText(  Double.toString ( bases.get(indice).getPrecioBase() )  +  "0" );
       }
       catch(IOException ioe){
       }
    }

            
    private void DeseleccionarAnteriorSeleccion(ActionEvent ae){
        ToggleButton botonCausaEvento = (ToggleButton) ae.getSource();
        System.out.println("estaseleccionado?"+botonCausaEvento.isSelected()+" "+botonCausaEvento.getText());

        
        for (int i=0;i<3;i++){
            VBox vbox = (VBox) nodoHbox33.getChildren().get(i);
            ToggleButton botonParcial = (ToggleButton) vbox.getChildren().get(0);
            if(botonParcial != null){
                if (botonParcial.isSelected() && botonParcial != botonCausaEvento ){  
                    System.out.println("Hay un boton seleccionado y no es igual al que causa el evento");
                    botonParcial.setSelected(false);
                    amarillo_a_verde=false;
                    cambiarEstilos(botonParcial);
                    System.out.println("El boton anteriormente seleccionado, cambio su estado a no seleccionado");
                    
                    baseElegida=null;
                }
                else if(botonCausaEvento.isSelected() && botonParcial == botonCausaEvento){
                    amarillo_a_verde=true;
                    cambiarEstilos(botonCausaEvento);
                    System.out.println("El boton que causo el evento fue seleccionado, base elegida es:");                 
                }
            }
        }
        
    }
    
    private void ActualizarBaseElegida(ActionEvent ae){
        
        ToggleButton botonCausaEvento = (ToggleButton) ae.getSource();
        
        VBox vbox = (VBox) botonCausaEvento.getParent();
        String nombreBase = botonCausaEvento.getText();
        Label l = (Label) vbox.getChildren().get(1);
        double precio = Double.parseDouble ( l.getText() );
        total.setText( Double.toString(precio) + "0" );
        if(baseElegida!= null){
            if(  baseElegida.equals( new Base (nombreBase,precio) ) == true){
                baseElegida=null;
                System.out.println("la base anterior es igual a la que seleciono y se hizo nula");
                amarillo_a_verde=false;
                cambiarEstilos(botonCausaEvento);
                total.setText("0.00"); 
              }

        }
        else if(baseElegida==null){baseElegida=new Base(nombreBase,precio);
            System.out.println("La base elegida se actualizo ahora es:");
            System.out.println(baseElegida.getNombreBase());
  
        }
 
    }

    
    private void cambiarEstilos(ToggleButton tb){
        if( tb.getText() .equals( tboton331.getText() ) && !amarillo_a_verde ){
            tboton331.getStyleClass().remove("estiloBotonVerde");
            tboton331.getStyleClass().add("estiloBotonAmarillo");
            System.out.println("Entro verde a amarillo en 1erboton");
        }
        else if( tb.getText() .equals(tboton332.getText() ) && !amarillo_a_verde ){
            tboton332.getStyleClass().remove("estiloBotonVerde");
            tboton332.getStyleClass().add("estiloBotonAmarillo");     
            System.out.println("Entro verde a amarillo en 2doboton");
        }
        else if( tb.getText() .equals( tboton333.getText() ) && !amarillo_a_verde ){
            tboton333.getStyleClass().remove("estiloBotonVerde");
            tboton333.getStyleClass().add("estiloBotonAmarillo");            
            System.out.println("Entro verde a amarillo en 3erboton");    
        }
        else if( tb.getText() .equals( tboton331.getText() ) && amarillo_a_verde ){
            tboton331.getStyleClass().remove("estiloBotonAmarillo");
            tboton331.getStyleClass().add("estiloBotonVerde");
            System.out.println("Entro amarillo a verde en 1erboton");            
        }
        else if( tb.getText() .equals(tboton332.getText() ) && amarillo_a_verde ){
            tboton332.getStyleClass().remove("estiloBotonAmarillo");
            tboton332.getStyleClass().add("estiloBotonVerde");           
            System.out.println("Entro amarillo a verde en 2doboton");  
        }
        else if( tb.getText() .equals( tboton333.getText() ) && amarillo_a_verde ){
            tboton333.getStyleClass().remove("estiloBotonAmarillo");
            tboton333.getStyleClass().add("estiloBotonVerde");    
            System.out.println("Entro amarillo a verde en 3erboton");  
        }
        
    
    }
    
    @FXML
    private void cambiarColortb1(ActionEvent event) {
        
        DeseleccionarAnteriorSeleccion(event);
        ActualizarBaseElegida(event);
    }

    @FXML
    private void cambiarColortb2(ActionEvent event) {
        DeseleccionarAnteriorSeleccion(event);
        ActualizarBaseElegida(event);
    }

    @FXML
    private void cambiarColortb3(ActionEvent event) {
        DeseleccionarAnteriorSeleccion(event);
        ActualizarBaseElegida(event);
    }
    
    
}
