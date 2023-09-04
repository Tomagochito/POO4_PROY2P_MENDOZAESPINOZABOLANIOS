package proyectopoo.heladeria;


import Modelo.Pedido;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


/**
 * JavaFX App
 */

public class App extends Application {
    
        /**
         * Es el pedido actual, con la capacidad de ser accedido dedsde otras clases
         */
        public static Pedido pedidoactual = new Pedido(null,null,null);
    

     /**
      * Escena del escenario
      */
    private static Scene scene;
    /**
     * 
     * @param stage Escenario de JavaFX
     * @throws IOException Excepcion a manejar
     */
    @Override
    public void start(Stage stage) throws IOException {      
        scene = new Scene(loadFXML("Cargando"), 822, 462);
        stage.setScene(scene);
        stage.setTitle("Heladeria");
        stage.show();   
    }

    /**
     * Metodo para cambiar la escena
     * @param fxml Ventana que sera puesta en la escena
     * @throws IOException Excepcion a manejar
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        
    }

    /**
     * Metodo para cargar la escena
     * @param fxml  Ventana que sera puesta en la escena
     * @return La escena cargada
     * @throws IOException  Excepcion a manejar
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Metodo que da inicio al programa
     * @param args *
     */
    public static void main(String[] args) {

        launch();
    }
        
 
}