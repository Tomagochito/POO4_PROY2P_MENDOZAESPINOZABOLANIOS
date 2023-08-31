/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.IncompleteStageException;
import Modelo.Local;
import Modelo.ManejoArchivos;
import Modelo.Pedido;
import Modelo.Sabor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author tomas
 */
public class VentanaSaboresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox VBox1Sabores;
    @FXML
    private VBox VBoxSabor1;
    @FXML
    private VBox VBox2Sabores;
    @FXML
    private VBox VBoxSabor2;
    @FXML
    private ComboBox<Sabor> cbsabor1;
    @FXML
    private ComboBox<Sabor> cbsabor2;
    @FXML
    private Button botonContinuarSabores;
    @FXML
    private HBox HBox1Sabores;
    @FXML
    private HBox HBox2Sabores;
    @FXML
    private HBox HBox3Sabores;
    ArrayList<Sabor> listasabores = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarsabores();
        cargarcombo();

    }

    public void cargarsabores() {
        try (BufferedReader bf = new BufferedReader(new FileReader(ManejoArchivos.rutaArchivos + "sabores.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String[] lsabores = linea.split(",");
                String nomsabor = lsabores[0];
                Double preciosabor = Double.parseDouble(lsabores[1]);
                Sabor sabor = new Sabor(nomsabor, preciosabor);
                listasabores.add(sabor);
            }
        } catch (FileNotFoundException a) {
            System.out.println("Ocurrio un error al leer el archivo de sabores");
        } catch (IOException a2) {
            System.out.println("Ocurrio un error inesperado en la lectura del archivo de sabores");
        }
    }

    public ArrayList<Sabor> ordenarlista(ArrayList<Sabor> lista) {
        ArrayList<Sabor> listaOrdenada = new ArrayList<>(lista);
        Collections.sort(listaOrdenada, new Comparator<Sabor>() {
            @Override
            public int compare(Sabor sabor1, Sabor sabor2) {
                return sabor1.getNombreSabor().compareTo(sabor2.getNombreSabor());
            }
        });
        return listaOrdenada;
    }

    public void cargarcombo() {
        ArrayList<Sabor> listaordenada = new ArrayList(ordenarlista(listasabores));
            cbsabor1.getItems().setAll(listaordenada);
            cbsabor2.getItems().setAll(listaordenada);
        
    }

    @FXML
    private void botoncontinuar(ActionEvent event) {
        try {
            boolean alMenosUnoSeleccionado = (cbsabor1.getValue() != null || cbsabor2.getValue() != null);
            if (alMenosUnoSeleccionado) {
                System.out.println("Al menos un ComboBox tiene algo seleccionado.");
                //----------Prueba
                App app = new App();
                Pedido pedido1 = app.getPedidoactual();
                pedido1.setSabor1(cbsabor1.getValue());
                pedido1.setSabor2(cbsabor2.getValue());
                //----------
                try{
                App.setRoot("VentanaToppings");
                }
                catch(IOException ioe){
                    System.out.println("Ocurrio un error al intentar cambiar a la escena de sabores");
                }    
            } else {
                System.out.println("Ningún ComboBox tiene algo seleccionado.");
                throw new IncompleteStageException("Debe escoger una sabor");
            }
        } catch (IncompleteStageException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se ha producido un error:\n" + e.getMessage());
            alert.showAndWait();
        }
    }

}
