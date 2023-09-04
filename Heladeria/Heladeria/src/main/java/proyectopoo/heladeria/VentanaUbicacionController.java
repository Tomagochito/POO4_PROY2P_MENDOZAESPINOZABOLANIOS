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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * 
 * @author Nahin Espinoza
 */
public class VentanaUbicacionController implements Initializable {

    /**
     * Variables
     */
    private ArrayList<Local> locales = new ArrayList<>();
    String rutamapa = ManejoArchivos.rutaImagenes + "baseYogurt.png";

    @FXML
    private Pane root1;
    @FXML
    private ImageView iv;

    /**
     * Metodo para inicializar el controller
     *
     * @param url se utiliza para especificar la ubicación del archivo FXML
     * @param rb maneja los recursos locales
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CargarFondo();
        CargarImagenes();
    }

    /**
     * Metodo para cargar la imagen del fondo de la escena
     */
    public void CargarFondo() {
        try (FileInputStream input = new FileInputStream(ManejoArchivos.rutaImagenes + "mapa.png")) {
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

    /**
     * Metodo para cargar los icons de los Helados y sus datos
     */
    @FXML
    public void CargarImagenes() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try (BufferedReader bf = new BufferedReader(new FileReader(ManejoArchivos.rutaImagenes + "locales.txt", StandardCharsets.UTF_8))) {
                    String linea;
                    while ((linea = bf.readLine()) != null) {
                        String[] datos = linea.split(",");
                        Double posx = Double.parseDouble(datos[0]);
                        Double posy = Double.parseDouble(datos[1]);
                        String nomlocal = datos[2];
                        String horarios = datos[3];
                        Local local = new Local(posx, posy, nomlocal, horarios);
                        Platform.runLater(() -> cargarLocales(local.getEjex(), local.getEjey(), local.getHorario(), local.getLugar()));
                        //Se genera un numero aletario para la generacion del siiguiente local
                        int tiempoaleatorio = (int) (Math.random() * 10) + 1;
                        try {
                            Thread.sleep(tiempoaleatorio * 1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }

                } catch (FileNotFoundException ae) {
                    System.out.println("Ocurrio un error");

                } catch (IOException a) {
                    System.out.println("Ocurrio un error inesperado");

                }

            }
        });
        t.start();
    }

    /**
     * Metodo para uibar los icons de los locales
     *
     * @param ejex La coordenada que tomara en el eje x
     * @param ejey La coordenada que tomara en el eje y
     * @param direccion La direccion del Local
     * @param horarioslocal Los horarios de inicio y cierre del local 
     */
    public void cargarLocales(Double ejex, Double ejey, String direccion, String horarioslocal) {
        try (FileInputStream input = new FileInputStream(ManejoArchivos.rutaImagenes + "heladomapa2.png")) {
            Image imgLocal = new Image(input);

            ImageView imageView1 = new ImageView(imgLocal);
            imageView1.setFitHeight(50);
            imageView1.setFitWidth(50);
            imageView1.setLayoutX(ejex);
            imageView1.setLayoutY(ejey);

            // Crea una instancia de Local y se guarda en UserData
            Local local = new Local(ejex, ejey, direccion, horarioslocal);
            imageView1.setUserData(local);

            // Se asigna el evento de clic al ImageView1
            imageView1.setOnMouseClicked(event -> mostrarDetalleLocal(event, local.getHorario(), local.getLugar()));

            root1.getChildren().addAll(imageView1);
        } catch (IOException e) {
            System.err.println("Error al cargar los locales: " + e.getMessage());
        }
    }

    /**
     * Metodo que mostara una ventana pop up con los detalles de cada local
     *
     * @param event Evento que llama al metodo
     * @param nombreLocal El nombre del local elegido
     * @param horariosLocal El horario de inicio y cierre de cada local
     */
    private void mostrarDetalleLocal(MouseEvent event, String nombreLocal, String horariosLocal) {
        Label contenidoPopup = new Label("Detalles del local:\nDireccion: " + nombreLocal + "\nHorarios: " + horariosLocal);
        VBox pane = new VBox();
        VBox pane2 = new VBox();
        //Estetica y ubicacion
        pane.setAlignment(Pos.CENTER);
        pane.setBackground(new Background(new BackgroundFill(Color.DARKKHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().add(contenidoPopup);
        pane2.setAlignment(Pos.CENTER_RIGHT);
        pane.getChildren().add(pane2);

        Stage popupStage = new Stage();
        popupStage.setTitle("Detalle del Local");
        popupStage.setScene(new Scene(pane, 230, 100));

        // Posición de la ventana emergente en relación con la ventana principal
        double x = event.getScreenX();
        double y = event.getScreenY();
        popupStage.setX(x);
        popupStage.setY(y);
        popupStage.show();

        // Hilo para que se cierre automaticamente en 5 segundos
        Thread contadorThread = new Thread(() -> {
            try {
                for (int segundos = 5; segundos > 0; segundos--) {
                    Thread.sleep(1000); // Espera 1 segundo
                    final int segundosRestantes = segundos;
                    // Actualiza el mensaje en la interfaz gráfica usando Platform.runLater
                    javafx.application.Platform.runLater(() -> {
                        contenidoPopup.setText("Detalles del local:\nNombre: " + nombreLocal + "\nHorarios: " + horariosLocal + "\nCerrando en " + segundosRestantes + " segundos");
                    });
                }
                // Cerrar la ventana emergente después de 5 segundos
                javafx.application.Platform.runLater(() -> {
                    popupStage.close();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        contadorThread.start();
        Button botoncerrar = new Button("Cerrar Ventana");
        // Ubicamos el boton cerrar en la ventana 
        pane2.getChildren().addAll(botoncerrar);
        botoncerrar.setOnAction(e -> cerrarVentana(popupStage));
    }

    /**
     * metodo para cerrar el pop up
     *
     * @param stage Ventana actual
     */
    private void cerrarVentana(Stage stage) {
        stage.close();
    }

}
