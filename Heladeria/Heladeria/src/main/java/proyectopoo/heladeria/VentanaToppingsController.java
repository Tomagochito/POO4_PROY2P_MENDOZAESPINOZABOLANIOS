/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.ManejoArchivos;
import Modelo.Sabor;
import Modelo.Topping;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author User
 */
public class VentanaToppingsController implements Initializable {
        @FXML
        private VBox root_toppings;
        @FXML
        private HBox HBox1Toppings;
        @FXML
        private HBox HBox2Toppings;
        @FXML
        private HBox HBox3Toppings;
        @FXML
        private VBox VBoxtoppings;
        @FXML
        private Label totaltoppings;
        @FXML
        private Button botonContinuarToppings;
                 ArrayList<Topping> listatoppings = new ArrayList<Topping>();

        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargartoppings();
        crearcheckbox();
    }    
    
    
     public void cargartoppings() {
        try (BufferedReader bf = new BufferedReader(new FileReader(ManejoArchivos.rutaArchivos + "toppings.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String[] ltoppings = linea.split(",");
                String nomtopping = ltoppings[0];
                Double preciotopping = Double.parseDouble(ltoppings[1]);
                Topping topping = new Topping(nomtopping, preciotopping);
                listatoppings.add(topping);
            }
        } catch (FileNotFoundException a) {
            System.out.println("Ocurrio un error al leer el archivo de toppings");
        } catch (IOException a2) {
            System.out.println("Ocurrio un error inesperado en la lectura del archivo de toppings");
        }
    }
    
    public void crearcheckbox(){
        
        for(Topping tp:listatoppings){
            CheckBox chb = new CheckBox(tp.toString());
            VBoxtoppings.getChildren().addAll(chb);         
        }
    }
}