/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.ManejoArchivos;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tomas
 */
public class VentanaBienvenidaController implements Initializable {
   /**
    * Usuario del cliente
    */
    public static String usuarioVentana2;
    
    @FXML
    private Label nombreClienteB;
    @FXML
    private Button localesBoton;
    @FXML
    private Button pedidosBoton;
    @FXML
    private StackPane nodoStackPane2;
    @FXML
    private ImageView nodoImageView2;
    @FXML
    private HBox nodoHbox2;
    @FXML
    private VBox nodoVbox2;

    /**
     * Inicializa el controlador de la clase
     * @param url Localizacion del FXML
     * @param rb Recursos utilizados en el controlador
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        try(FileInputStream fis = new FileInputStream (ManejoArchivos.rutaArchivos+"heladosVentanaInicio.png") ) {
            Image i = new Image(fis);
             nodoImageView2.setImage(i);
        }       
        catch(Exception e){
            System.out.println("Proceso fallido");
        }      
        String bien = nombreClienteB.getText() + " " + VentanaInicioController.usuarioVentana1;
        nombreClienteB.setText(bien);    
    }    

    /**
     * Muestras los locales disponibles
     * @param event Evento al accionar el boton
     * @throws IOException Excepcion a manejar 
     */
    @FXML
    private void mostrarLocales(ActionEvent event) throws IOException {
        App.setRoot("VentanaUbicacion");
            //Hilo para que en 30 segundos vuelva a la ventana anterior para continuar con el pedido
        Thread regresar = new Thread(() -> {
            try {
                Thread.sleep(60000);
                Platform.runLater(() -> volverAventanaAnterior());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        regresar.start();
    }      
      private void volverAventanaAnterior() {
        try {
            App.setRoot("VentanaBienvenida");
        } catch (IOException ex) {
            System.out.println("Ocurrio un error al regresar de escena ");
        }
    }

    /**
     * Cambia a la ventana de bases
     * @param event Evento al accionar boton
     * @throws IOException Excepcion a manejar 
     */
    @FXML
    private void agregarBase(ActionEvent event) throws IOException{
        App.setRoot("VentanaBases");
    }
    
}
