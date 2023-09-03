/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
//Guillermo
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import proyectopoo.heladeria.App;
import proyectopoo.heladeria.PagoController;
import proyectopoo.heladeria.VentanaBasesController;
import proyectopoo.heladeria.VentanaInicioController;
import proyectopoo.heladeria.VentanaToppingsController;

/**
 *
 * @author tomas
 */
public class Pedido implements Serializable, Pagable {

    /**
     * Base del helado
     */
    private Base base1;
    /**
     * ArrayList de sabores del helado
     */
    private ArrayList<Sabor> listasabores;
    /**
     * ArrayList de topping del helado
     */
    private ArrayList<Topping> listatopping;
    /**
     * ID de pago usado en el método generar transaccion
     */
    private int numPago = 9999;
    
    /**
     * 
     * @param base1 Base de helado escogida por el cliente
     * @param listasabores Lista de sabores que el cliente escogió para su helado
     * @param listatopping Lista de toppings que el cliente eligió para su helado
     */
    public Pedido(Base base1, ArrayList<Sabor> listasabores, ArrayList<Topping> listatopping) {
        this.base1 = base1;
        this.listasabores = listasabores;
        this.listatopping = listatopping;
    }

    /**
     * 
     * @return Base del helado escogida por el cliente
     */
    public Base getBase1() {
        return base1;
    }
    /**
     * 
     * @param base1 Cambia la base por base1 del helado de ser necesario 
     */
    public void setBase1(Base base1) {
        this.base1 = base1;
    }
    /**
     * 
     * @return Lista de los sabores escogidos por el cliente 
     */
    public ArrayList<Sabor> getListasabores() {
        return listasabores;
    }
    /**
     * 
     * @param listasabores Cambia la lista de sabores del cliente por la listasabores de ser necesario
     */
    public void setListasabores(ArrayList<Sabor> listasabores) {
        this.listasabores = listasabores;
    }
    /**
     * 
     * @return Lista de los toppings escogidos por el cliente 
     */
    public ArrayList<Topping> getListatopping() {
        return listatopping;
    }
    /**
     * 
     * @param listatopping Cambia la lista de toppings del cliente por la listatopping de ser necesario
     */
    public void setListatopping(ArrayList<Topping> listatopping) {
        this.listatopping = listatopping;
    }
    /**
     * 
     * @return Codigo del pago generado
     */
    public int getNumPago() {
        return numPago;
    }



    /**
     * Genera la transaccion que se ha generado al pagar el pedido, y escribe la 
     * informacion del pago en un archivo pago.txt
     */
    @Override
    public void generarTransaccion() {
        Date fecha=new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        double totalPago=0;
        if (PagoController.clasep.equals(TipoPago.C)){
            totalPago=PagoController.totalTarjeta;
        }
        else{
            totalPago=PagoController.totalIVA;
        }
        try(BufferedWriter bf= new BufferedWriter(new FileWriter(ManejoArchivos.rutaArchivos+"pagos.txt"))){
            String line=numPago+","+VentanaToppingsController.numPedido+","+VentanaInicioController.clienteActual.getUsuario()+
                    ","+totalPago+","+sd.format(fecha)+","+String.valueOf(PagoController.clasep);
            bf.write(line+"\n");
            numPago--;
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
        
    }
    /**
     * 
     * @param objeto Objeto a serializar
     * @param nombreArchivo Nombre que se le pondrá al objeto serializado
     */
    public static void serializarPedido(Pedido objeto, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ManejoArchivos.rutaArchivos + nombreArchivo))) {
            oos.writeObject(objeto);
            System.out.println("¡El objeto se serializó con éxito!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }// se cierra el metodo

}
