/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.Base;
import static Modelo.Base.bases;
import static Modelo.Base.basesEscogidas;
import Modelo.ManejoArchivos;
import Modelo.Pedido;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;

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
    
 
    int count = 0;
    static final int maxBasesSeleccionadas=1;
    @FXML
    private Button botonContinuar;
    /**
     * Initializes the controller class.
     *//*"heladoUpper2.png""heladoUpper1.png"*/

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        basesEscogidas=new ArrayList<>();
        GraphicsContext gc = nodoCanvas.getGraphicsContext2D();
        dibujarHeladoDerretido(gc);
        nodoLabeltotalAcumulandose.setText("0.00");
        Collections.sort(bases);

        
        // aqui he hecho este if para lo normal, es decir, para las 3 bases por defecto;
        //si se agregan mas bases deben crearse nuevos 
        //contenedores , botones, labels, e incluso la imagen de la base, de manera dinamica
     
       
        if(bases.size()<4){
            insertarDatosBoton_Precio("baseHelado.png",nodoBoton331,nodoLabelprecio1,0);
            insertarDatosBoton_Precio("baseVegano.png",nodoBoton332,nodoLabelprecio2,1);
            insertarDatosBoton_Precio("baseYogurt.png",nodoBoton333,nodoLabelprecio3,2);
  
        }
        // completar mas tarde xd
        else{}

    }


    private void cambiarColor(ActionEvent ae) {
      Button boton = (Button) ae.getSource();
      String nombreBase = boton.getText();

      if (esVerde(boton)) {
          boton.getStyleClass().remove("botonEstilo");
          boton.getStyleClass().add("botonEstiloVB");
          
          basesEscogidas.removeIf(base -> base.getNombreBase().equals(nombreBase));
          for (Base base : bases) {
                  if (nombreBase.equals(base.getNombreBase())) {
                      restar(base);
                      break;
                  }
              }
      } else {
          if (basesEscogidas.size() < maxBasesSeleccionadas) {
              boton.getStyleClass().remove("botonEstiloVB");
              boton.getStyleClass().add("botonEstilo");
              

              for (Base base : bases) {
                  if (nombreBase.equals(base.getNombreBase())) {
                      basesEscogidas.add(base);
                      acumular(base);
                      break;
                  }
              }
          } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Número de bases sobrepasadas");
              alert.setHeaderText(null);
              alert.setContentText("Solo puede seleccionar un máximo de una base");
              alert.showAndWait();
          }
      }
  }


    
    private boolean esVerde(Button bt){
        return bt.getStyleClass().contains("botonEstilo") ;
    }
    
    
    private void acumular(Base base){
        double total=Double.parseDouble( nodoLabeltotalAcumulandose.getText() );
        double preciobase = base.getPrecioBase();
        double acumulado = total+preciobase;
        nodoLabeltotalAcumulandose.setText( Double.toString(acumulado)+ "0" );
    }
    
    private void restar(Base base){
        double total=Double.parseDouble( nodoLabeltotalAcumulandose.getText() );
        double preciobase = base.getPrecioBase();
        double acumulado = total-preciobase;
        nodoLabeltotalAcumulandose.setText( Double.toString(acumulado) +"0");
    }
    
    @FXML
    private void cambiarColor1(ActionEvent event1) {
        cambiarColor(event1);
    }

    @FXML
    private void cambiarColor2(ActionEvent event2) {
        cambiarColor(event2);
    }

    @FXML
    private void cambiarColor3(ActionEvent event3) {
        cambiarColor(event3);
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
         
    
    // este metodo servira si se agregan mas bases al documento, el numero de botones aumentara;  aqui se 
    // verifica eso, el nodo a usar sera nodoHbox33
    private int countButtons(Node nodo) {

    if (nodo instanceof Button) {
        count++; // Si el nodo es un botón, incrementa el contador en uno
    }

    if (nodo instanceof javafx.scene.Parent) { // Verifica si el nodo es un contenedor
        for (Node child : ((javafx.scene.Parent) nodo).getChildrenUnmodifiable()) { // Itera sobre los hijos del contenedor
            count += countButtons(child); // Llama recursivamente al método countButtons para cada hijo y suma su resultado al contador
        }
    }

    return count; // Devuelve el contador final que indica la cantidad de botones encontrados
}

    @FXML
    private void continuar(ActionEvent event) {
        int nbases = basesEscogidas.size();
        switch(nbases){
            case 0:
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Número de bases faltantes");
              alert.setHeaderText(null);
              alert.setContentText("Debe escoger una base");
              alert.showAndWait();
               break; 
            case 1:
                Pedido p1 = new Pedido(basesEscogidas.get(0),null,null,null,null,null);
                try{
                App.setRoot("VentanaSabores");
                }
                catch(IOException ioe){}
                break;

        
        }
    }
    
    public String capitalizar(String texto) {
    if (texto == null || texto.isEmpty()) {
        return texto;
    }
    
    String primeraLetra = texto.substring(0, 1).toUpperCase();
    String restoDePalabra = texto.substring(1).toLowerCase();
    
    return primeraLetra + restoDePalabra;
}
    
    

    // dentro del boton se agrega una imagen, esta dificilisimo hacer que la calidad se mantenga,
    //no se porque luce tan punteada la imagen
    public void insertarDatosBoton_Precio(String archivo,Button boton,Label l,int indice){
       try(FileInputStream fis = new FileInputStream (new File(ManejoArchivos.rutaArchivos+archivo) )){    
       // Cargar imágenes para los botones
       
        Image imagen = new Image(fis);
        
        // Crear una vista de imagen para ajustarla al tamaño del botón
        ImageView imagenEnBoton = new ImageView(imagen);
        imagenEnBoton.setPreserveRatio(true); // Mantener la proporción de la imagen
        
        // Ajustar el tamaño de la imagen dentro del botón
        imagenEnBoton.fitWidthProperty().bind(boton.widthProperty().divide(2.5));
        imagenEnBoton.fitHeightProperty().bind(boton.heightProperty());

        // Configurar la imagen en el botón

        
        boton.setGraphic(imagenEnBoton);
        boton.setText( /*capitalizar*/ ( bases.get(indice).getNombreBase() ) );       
        boton.setGraphicTextGap(10);
        l.setText(  Double.toString ( bases.get(indice).getPrecioBase() )+"0" );
       }catch(IOException ioe){}
    }
            
    
}
