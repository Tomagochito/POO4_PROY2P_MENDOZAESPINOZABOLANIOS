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
            //public static ArrayList<Pedido> pedidos;

        public static Pedido pedidoactual = new Pedido(null,null,null);
    
   /** public Pedido getPedidoactual() {
        return pedidoactual;
    }

    public void setPedidoactual(Pedido pedidoactual) {
        this.pedidoactual = pedidoactual;
    }**/

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {      
        scene = new Scene(loadFXML("Cargando"), 822, 462);
        stage.setScene(scene);
        stage.setTitle("Heladeria");
        stage.show();   
    }

    
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        
    }

    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    
    public static void main(String[] args) {

        launch();
    }
        
 
}