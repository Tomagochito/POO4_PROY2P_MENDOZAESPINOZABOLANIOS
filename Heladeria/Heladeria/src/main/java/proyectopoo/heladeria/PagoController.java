/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.IncompleteStageException;
import com.sun.javafx.font.LogicalFont;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class PagoController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    
    TextField name=new TextField();
    TextField number=new TextField();
    TextField date=new TextField();
    TextField cv=new TextField();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rbefectivo.setOnAction((t) -> {pagoEfectivo();
        });
        rbtarjeta.setOnAction((t) -> {pagoTarjeta();
        });
    }
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
                //llama generar transaccion
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
    @FXML
    public void cancelar(){};
    public void pagoEfectivo(){
     txtadicional.clear();
     txtiva.clear();
     txttotal.clear();
     txtvalor.clear();
     txtvalor.setText("0.00");
     txtvalor.setDisable(true);
     lblmensaje.setText("Acerquese a la caja a pagar su pedido, por favor");
    }
    public void pagoTarjeta(){ 
     name.setMinHeight(20);
     name.setMinWidth(200);
     number.setMinHeight(20);
     number.setMinWidth(200);
     date.setMinHeight(20);
     date.setMinWidth(200);
     cv.setMinHeight(20);
     cv.setMinWidth(200);
     hbtipopago.setAlignment(Pos.TOP_LEFT);
     VBox infoT=new VBox();
     VBox txts=new VBox();
     infoT.setSpacing(10);
     txts.setSpacing(8);
     hbtipopago.setPadding(new Insets(10, 0, 0, 40));
     lblmensaje.setText("Ingrese los datos de su tarjeta:");
     lblmensaje.setStyle("-fx-font-weight: bold");
     txtadicional.clear();
     txtiva.clear();
     txttotal.clear();
     txtvalor.clear();
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
