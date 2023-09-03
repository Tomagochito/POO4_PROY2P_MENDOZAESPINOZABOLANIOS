/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;
//Guillermo
import Modelo.IncompleteStageException;
import Modelo.ManejoArchivos;
import Modelo.TipoPago;
import com.sun.javafx.font.LogicalFont;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static proyectopoo.heladeria.VentanaToppingsController.total;


/**
 * FXML Controller class
 *
 * @author Guillermo Mendoza
 */
public class PagoController implements Initializable {
    /**
     * Variable del IVA estatico para que sea posible acceder a ella desde otras  clases dle proyecto
     */
    public static double IVA;
    /**
     * Variable del precio del helado mas el IVA, que es estatica para que sea posible acceder a ella desde otras  clases dle proyecto
     */
    public static double totalIVA;
    /**
     * Variable del Adicional que se aplica a un pago con tarjeta, estatica para que sea posible acceder a ella desde otras  clases dle proyecto
     */
    public static double AdicionalT;
    /**
     * Total de pago con tarjeta del totalIVA mas el Adicional de la tarjeta, estatica para que sea posible acceder a ella desde otras  clases dle proyecto
     */
    public static double totalTarjeta;

    /**
     * Initializes the controller class.
     */
    public static TipoPago clasep;
    @FXML
    private VBox vbpago;
    @FXML
    private ImageView imvdecoracion;
    @FXML
    private HBox hbtpago;
    @FXML
    private HBox hbdetalles;
    @FXML
    private VBox vblabels;
    @FXML
    private VBox vbtxts;
    @FXML
    private TextField txtvalor;
    @FXML
    private TextField txtadicional;
    @FXML
    private TextField txtiva;
    @FXML
    private TextField txttotal;
    @FXML
    private Label lblmensaje;
    @FXML
    private Button btnconfirmar;
    @FXML
    private Button btncancelar;
    @FXML
    private RadioButton rbefectivo;
    @FXML
    private RadioButton rbtarjeta;
    @FXML
    private ToggleGroup tipopago;
    @FXML
    private HBox hbtipopago;
    @FXML
    private ImageView imgvdeco;
    
    TextField name=new TextField();
    TextField number=new TextField();
    TextField date=new TextField();
    TextField cv=new TextField();

    /**
     * 
     * @param url Localizacion del FXML
     * @param rb Recursos del controlador
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try(FileInputStream in = new FileInputStream(ManejoArchivos.rutaArchivos+"pago.jpg")){
            Image image=new Image(in);
            imvdecoracion.setImage(image);
        }catch(Exception e){
            System.out.println("Erro al cargar imagen");
        }
        try(FileInputStream in2 = new FileInputStream(ManejoArchivos.rutaArchivos+"money.gif")){
            Image image2=new Image(in2);
            imgvdeco.setImage(image2);
        }catch(Exception e){
            System.out.println("Erro al cargar imagen");
        }
        rbefectivo.setOnAction((t) -> {pagoEfectivo();
        });
        rbtarjeta.setOnAction((t) -> {pagoTarjeta();
        });
    }
    /**
     * Metodo accionado por el boton confirmar, que pasa a la seguiente ventana de la aplicacion.
     */
    @FXML
    public void confirmar() {
        
        RadioButton elegido=(RadioButton)tipopago.getSelectedToggle();
        try{
        if(elegido==null){
            throw new IncompleteStageException("Debe escoger un metodo de pago");
        }
        else if (elegido.getText().equals("Tarjeta de credito")){
            if(name.getText().equals("") || number.getText().equals("") || date.getText().equals("")|| cv.getText().equals("")){
                 throw new IncompleteStageException("Informacion de tarjeta incompleta");
            }
            else{
                clasep=TipoPago.C;
                App.pedidoactual.generarTransaccion();
                try{
                    App.setRoot("Orden");
                }catch(IOException ioe){
                     System.out.println("Error a cambiar de escena");
                    }
            }
        }
        else{
            clasep=TipoPago.E;
            App.pedidoactual.generarTransaccion();
            try{
                App.setRoot("Orden");
            }catch(IOException ioe){
            System.out.println("Error a cambiar de escena");
            }
        }
        }catch(IncompleteStageException i){
            Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Informacion de tarjeta incompleta o metodo de pago no seleccionado");
              alert.setHeaderText(null);
              alert.setContentText(i.getMessage());
              alert.showAndWait();
        }
        
        
    }
    /**
     * Metodo accionado por el boton cancelar, cierra la aplicacion
     */
    @FXML
    public void cancelar(){
        try{
            App.setRoot("VentanaInicio");
        }catch(IOException ioe){
            System.out.println("Error al cambiar ventana");
        }
    };
    /**
     * Metodo que se llama al momento de que el cliente elige la opcion de pago en efectivo
     */
    public void pagoEfectivo(){
     IVA=(total*0.132);
     totalIVA=total+IVA;
     txtadicional.clear();
     txtiva.clear();
     txttotal.clear();
     txtvalor.clear();
     txtvalor.setText(String.valueOf(total));
     txtvalor.setDisable(true);
     txtiva.setText(String.valueOf(IVA));
     txtiva.setDisable(true);
     txtadicional.setDisable(true);
     txttotal.setText(String.valueOf(totalIVA));
     txttotal.setDisable(true);
     lblmensaje.setText("Acerquese a la caja a pagar su pedido, por favor");
    }
    /**
     * Metodo que se llama cuando el cliente selecciona la opcion de pago con tarjeta de crédito
     */
    public void pagoTarjeta(){
     name.setMinHeight(20);
     name.setMinWidth(200);
     number.setMinHeight(20);
     number.setMinWidth(200);
     date.setMinHeight(20);
     date.setMinWidth(200);
     cv.setMinHeight(20);
     cv.setMinWidth(200);
     IVA=(total*0.132);
     AdicionalT=total*0.10;
     totalIVA=total+IVA;
     totalTarjeta=totalIVA+AdicionalT;
     txtadicional.clear();
     txtiva.clear();
     txttotal.clear();
     txtvalor.clear();
     txtvalor.setText(String.valueOf(total));
     txtvalor.setDisable(true);
     txtiva.setText(String.valueOf(IVA));
     txtiva.setDisable(true);
     txtadicional.setText(String.valueOf(AdicionalT));
     txtadicional.setDisable(true);
     txttotal.setText(String.valueOf(totalTarjeta));
     txttotal.setDisable(true);
     hbtipopago.setAlignment(Pos.TOP_LEFT);
     VBox infoT=new VBox();
     VBox txts=new VBox();
     infoT.setSpacing(10);
     txts.setSpacing(8);
     hbtipopago.setPadding(new Insets(10, 0, 0, 40));
     lblmensaje.setText("Ingrese los datos de su tarjeta:");
     lblmensaje.setStyle("-fx-font-weight: bold");
     Label nombre=new Label("Nombre:");
     Label numero=new Label("Numero:");
     Label fechac=new Label("Fecha de caducidad:");
     Label cvv=new Label("CVV:");
     infoT.getChildren().addAll(lblmensaje,nombre,numero,fechac,cvv);
     txts.setPadding(new Insets(25,0,0,0));
     txts.getChildren().addAll(name,number,date,cv);
     hbtipopago.getChildren().addAll(infoT,txts);
    }
}
