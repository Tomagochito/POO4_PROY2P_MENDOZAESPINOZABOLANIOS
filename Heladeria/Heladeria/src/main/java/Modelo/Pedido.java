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


    Base base1;
    ArrayList<Sabor> listasabores;
    ArrayList<Topping> listatopping;
    int numPago = 9999;

    public Pedido(Base base1, ArrayList<Sabor> listasabores, ArrayList<Topping> listatopping) {
        this.base1 = base1;
        this.listasabores = listasabores;
        this.listatopping = listatopping;
    }

    
    public Base getBase1() {
        return base1;
    }

    public void setBase1(Base base1) {
        this.base1 = base1;
    }

    public ArrayList<Sabor> getListasabores() {
        return listasabores;
    }

    public void setListasabores(ArrayList<Sabor> listasabores) {
        this.listasabores = listasabores;
    }

    public ArrayList<Topping> getListatopping() {
        return listatopping;
    }

    public void setListatopping(ArrayList<Topping> listatopping) {
        this.listatopping = listatopping;
    }

    public int getNumPago() {
        return numPago;
    }




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

    public static void serializarPedido(Pedido objeto, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ManejoArchivos.rutaArchivos + nombreArchivo))) {
            oos.writeObject(objeto);
            System.out.println("¡El objeto se serializó con éxito!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }// se cierra el metodo

}
