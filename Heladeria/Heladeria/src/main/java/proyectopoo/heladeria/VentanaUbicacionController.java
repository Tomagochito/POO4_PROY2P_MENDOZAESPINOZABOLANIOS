package proyectopoo.heladeria;
import javafx.scene.input.MouseEvent;

import Modelo.Local;
import Modelo.ManejoArchivos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class VentanaUbicacionController implements Initializable {
     private ArrayList<Local> locales = new ArrayList<>();
    String rutamapa = ManejoArchivos.rutaArchivos + "baseYogurt.png";

//    @FXML
//    private ImageView Mapa;
    @FXML
    private Pane root1;
    @FXML
    private ImageView iv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CargarFondo();
          for (Local local : locales) {
        ImageView localImageView = new ImageView(); // Crea una nueva imagen para el local (puedes cargar la imagen aquí)
        localImageView.setUserData(local); // Almacena el objeto Local en el UserData de la imagen
        localImageView.setOnMouseClicked(this::mostrarDetalleLocal);
        root1.getChildren().add(localImageView);
    }}

    public void CargarFondo() {
        try (FileInputStream input = new FileInputStream(ManejoArchivos.rutaArchivos + "mapa.png")) {
            Image imgmapa = new Image(input);
            iv.setImage(imgmapa);
            iv.setFitHeight(800);
            iv.setFitWidth(820);
            //cargarlocales();
        } catch (FileNotFoundException a) {
            a.printStackTrace();
            System.out.println("No se encontró el archivo");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Ocurrió un error al cargar la imagen");

        }
    }



    @FXML
    public void CargarImagenes() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try (BufferedReader bf = new BufferedReader(new FileReader(ManejoArchivos.rutaArchivos + "locales.txt"))) {
                    String linea;
                    while ((linea = bf.readLine()) != null) {
                        String[] datos = linea.split(",");
                        Double posx = Double.parseDouble(datos[0]);
                        Double posy = Double.parseDouble(datos[1]);
                        String nomlocal = datos[2];
                        String horarios = datos[3];
                        Local local = new Local(posx,posy,nomlocal,horarios);
                        Platform.runLater(() -> cargarlocales(local.getEjex(), local.getEjey()));
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
         t.start();
    }
    
    public void cargarlocales(Double ejex,Double ejey) {
        try (FileInputStream input = new FileInputStream(ManejoArchivos.rutaArchivos + "heladomapa2.png")) {

            Image imglocal = new Image(input);

            ImageView imageView1 = new ImageView(imglocal);
            imageView1.setImage(imglocal);

            imageView1.setFitHeight(50);
            imageView1.setFitWidth(50);
       
            imageView1.setLayoutX(ejex); // Posición X
            imageView1.setLayoutY(ejey); // PosiciónY

            root1.getChildren().addAll(imageView1);
        } catch (IOException e) {
            System.out.println("Error al cargar los locales");
        }

    }
    private void mostrarDetalleLocal(MouseEvent event) {
        ImageView localImageView = (ImageView) event.getSource();
        Local local = (Local) localImageView.getUserData();
        Popup popup = new Popup();
        Label contenidoPopup = new Label("Detalles del local:\nNombre: " + local.getLugar()+ "\nDirección: " + local.getHorario());
        popup.getContent().add(contenidoPopup);

        double x = event.getScreenX();
        double y = event.getScreenY();
        popup.show(root1.getScene().getWindow(), x, y);
    }
}
