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

/**
 * FXML Controller class
 *
 * @author tomas
 */
public class VentanaBienvenidaController implements Initializable {
   
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
     * Initializes the controller class.
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

    
    @FXML
    private void mostrarLocales(ActionEvent event) {
    }

    @FXML
    private void agregarBase(ActionEvent event) throws IOException{
        try{
        App.setRoot("VentanaBases");
        }
        catch(IOException ioe){}
    }
    
}
