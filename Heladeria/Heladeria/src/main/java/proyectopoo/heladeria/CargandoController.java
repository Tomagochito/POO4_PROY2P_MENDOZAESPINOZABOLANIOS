/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.ManejoArchivos;
import Modelo.Pedido;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CargandoController implements Initializable {
    @FXML
    private Label Lblcargando;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Borra la informacion de pedidos y pagos simulando un nuevo dia de trabajo
        try(BufferedWriter bf=new BufferedWriter(new FileWriter(ManejoArchivos.rutaArchivos+"pedido.txt"))){
            bf.write(" ");
        }catch(IOException ioe){
            System.out.println("Error al actualizar informacion");
        }
        try(BufferedWriter bf=new BufferedWriter(new FileWriter(ManejoArchivos.rutaArchivos+"pagos.txt"))){
            bf.write(" ");
        }catch(IOException ioe){
            System.out.println("Error al actualizar informacion");
        }
        task();
    }    
    public void task(){
        Thread hilo=new Thread(new Runnable() {
            @Override
            public void run() {
                contador();
            }
        });
        hilo.setDaemon(true);
        hilo.start();
    }
    /**
     * Metodo utilizado para actualizar el label del contador y este cumpla su correcta funcion en javafx
     */
    public void contador(){
        for(int i=5;i>=0;i--){
            
            String contador="Cargando aplicacion  "+i+" ...";
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Lblcargando.setText(contador);
                }
            });
            try{
                Thread.sleep(1000);
            }catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        try{
            App.setRoot("VentanaInicio");
        }catch(IOException ioe){
            System.out.println("Error al cambiar ventana");
        }
    }
    
}
