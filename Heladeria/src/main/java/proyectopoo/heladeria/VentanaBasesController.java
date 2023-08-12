/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

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
import java.util.ResourceBundle;

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
    private Button nodoBoton331;
    @FXML
    private VBox nodoVbox332;
    @FXML
    private Button nodoBoton332;
    @FXML
    private VBox nodoVbox333;
    @FXML
    private Button nodoBoton333;
    @FXML
    private HBox nodoHbox34;
    @FXML
    private Label nodoLabeltotalAcumulandose;
    @FXML
    private Label nodoLabelprecio1;
    @FXML
    private Label nodoLabelprecio2;
    @FXML
    private Label nodoLabelprecio3;
    @FXML
    private Canvas nodoCanvas;

    /**
     * Initializes the controller class.
     *//*"heladoUpper2.png""heladoUpper1.png"*/

    
@Override
public void initialize(URL url, ResourceBundle rb) {

        
        GraphicsContext gc = nodoCanvas.getGraphicsContext2D();
 

        dibujarHeladoDerretido(gc);
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
    private void setBase1(ActionEvent event) {
    }

    @FXML
    private void setBase2(ActionEvent event) {
    }

    @FXML
    private void setBase3(ActionEvent event) {
    }
    
}
