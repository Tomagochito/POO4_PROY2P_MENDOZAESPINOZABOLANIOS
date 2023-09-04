/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.ManejoArchivos;
import Modelo.Pedido;
import Modelo.Sabor;
import Modelo.Topping;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static proyectopoo.heladeria.VentanaSaboresController.sabor1;
import static proyectopoo.heladeria.VentanaSaboresController.sabor2;

/**
 * FXML Controller class
 *
 * @author Nahim
 */
public class VentanaToppingsController implements Initializable {

    /**
     * Variable estatica que corresponde al ID del pedido, permitiendo acceder a
     * el desde otras clases del proyecto
     */
    public static int numPedido = 9999;
    /**
     * Variable estatica del total sin adicionar del IVA, permitiendo acceder a
     * ella desde otras clases del proyecto
     */
    public static double total=VentanaSaboresController.totalpago;

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
    @FXML
    private ImageView imgvtoppings;
    @FXML
    private ImageView imgvgif;
    /**
     * Lista de toppings cargados
     */
    ArrayList<Topping> listatoppings = new ArrayList<Topping>();
    /**
     * Lista de toppings seleccionados
     */
    ArrayList<Topping> toppingselec = new ArrayList<Topping>();

    /**
     *
     * @param url localizacion del FXML
     * @param rb recursos del controlador
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try (FileInputStream f = new FileInputStream(ManejoArchivos.rutaImagenes + "bases3.png")) {
            Image i = new Image(f);
            imgvtoppings.setImage(i);
        } catch (IOException i) {
            System.out.println("Error al cargar imagen");
        }
        try (FileInputStream g = new FileInputStream(ManejoArchivos.rutaImagenes + "L.gif")) {
            Image im = new Image(g);
            imgvgif.setImage(im);
        } catch (IOException i) {
            System.out.println("Error al cargar imagen");
        }
        totaltoppings.setText(String.valueOf(total));
        cargartoppings();
        crearcheckbox();

    }

    /**
     * Carga los toppings del archivo de toppings.
     */
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

    /**
     * Crea los checkbox de cada topping en la ventana de toppings.
     */
    public void crearcheckbox() {
        for (Topping tp : listatoppings) {
            CheckBox chb = new CheckBox(tp.toString());
            VBoxtoppings.getChildren().addAll(chb);
            chb.setOnAction(event -> recuperartoppings(chb, tp));
        }
    }

    /**
     *
     * @param ch variable de cada checkbox de los toppings, se usa para validar
     * si el checkbox esta seleccionado o no.
     * @param tp es el topping al que pertenece al checkbox, si el checkbox se
     * encuentra seleccionado este se guarda en una lista de toppings
     * seleccionados
     */
    public void recuperartoppings(CheckBox ch, Topping tp) {
        if (ch.isSelected()) {
            total += tp.getPrecioTopping();
            toppingselec.add(tp);
        } else {
            total -= tp.getPrecioTopping();
            toppingselec.remove(tp);
        }
        totaltoppings.setText("$ " + total);
    }

    /**
     * Metodo del boton continuar en la ventana topping, se utiliza para generar
     * el pedido del cliente y guardarlo en un archivo, para luego cambiar a la
     * ventana pago
     * @param e 
     */
    @FXML
    public void botonContinuar(ActionEvent e) {
        //Se debe generar pedido y guardar en pedidotxt
        App.pedidoactual.setListatopping(toppingselec);
        try {
            App.setRoot("Resumen");
        } catch (IOException ioe) {
            System.out.println("No se ha podido cambiar la ventana");
        }
        
    }

}
