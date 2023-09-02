/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import proyectopoo.heladeria.App;
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
    int n = 9999;

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

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }



    @Override
    public void generarTransaccion() {
        try(BufferedWriter bf= new BufferedWriter(new FileWriter("pagos.txt"))){
            String line=n+","+VentanaToppingsController.numPedido+","+VentanaInicioController.clienteActual.getUsuario()+",";
            bf.write(line);
            n--;
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
