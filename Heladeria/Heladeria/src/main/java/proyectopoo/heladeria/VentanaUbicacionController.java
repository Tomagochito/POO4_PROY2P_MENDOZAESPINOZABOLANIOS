package proyectopoo.heladeria;

import Modelo.ManejoArchivos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VentanaUbicacionController implements Initializable {

    String rutamapa = ManejoArchivos.rutaArchivos + "baseYogurt.png";

//    @FXML
//    private ImageView Mapa;
    @FXML
    private VBox root1;
    @FXML
    private ImageView iv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("algo se ejecuta porlomenos");
        try {
            App.setRoot("VentanaUbicacion.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        CargarFondo();
    }

    public void CargarFondo() {
        
        ImageView ima = null;
        Label label = new Label("Hola");
            root1.getChildren().add(label);

        try (FileInputStream input = new FileInputStream(ManejoArchivos.rutaArchivos + "baseYogurt.png")) {
            System.out.println("Se deberia ejecutar algo");

            Image imgmapa = new Image(input);
            ima = new ImageView(imgmapa);
            root1.getChildren().add(ima);

            iv.setImage(imgmapa);
        } catch (FileNotFoundException a) {
            a.printStackTrace();
            System.out.println("No se encontró el archivo");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Ocurrió un error al cargar la imagen");
        }
    }

    @FXML
    void CargarImagenes(ActionEvent event) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try (BufferedReader bf = new BufferedReader(new FileReader(ManejoArchivos.rutaArchivos + "locales.txt"))) {
                    String linea;
                    while ((linea = bf.readLine()) != null) {
                        String[] datos = linea.split(",");
                        String ejex = datos[0];
                        String ejey = datos[1];
                        String nomlocal = datos[2];
                        String horarios = datos[3];
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }

                } catch (FileNotFoundException ae) {

                } catch (IOException a) {

                }

            }
        });
    }
}
