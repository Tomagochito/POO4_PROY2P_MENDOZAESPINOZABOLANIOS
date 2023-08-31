/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author tomas
 */
public class Pedido implements Serializable, Pagable {


    Base base1;
    Sabor sabor1;
    Sabor sabor2;
    Topping topping1;
    Topping topping2;
    Topping topping3;
    int n = 1;

    public Pedido(Base base1, Sabor sabor1, Sabor sabor2, Topping topping1, Topping topping2, Topping topping3) {
        this.base1 = base1;
        this.sabor1 = sabor1;
        this.sabor2 = sabor2;
        this.topping1 = topping1;
        this.topping2 = topping2;
        this.topping3 = topping3;
    }

    public Base getBase1() {
        return base1;
    }

    public void setBase1(Base base1) {
        this.base1 = base1;
    }

    public Sabor getSabor1() {
        return sabor1;
    }

    public void setSabor1(Sabor sabor1) {
        this.sabor1 = sabor1;
    }

    public Sabor getSabor2() {
        return sabor2;
    }

    public void setSabor2(Sabor sabor2) {
        this.sabor2 = sabor2;
    }

    public Topping getTopping1() {
        return topping1;
    }

    public void setTopping1(Topping topping1) {
        this.topping1 = topping1;
    }

    public Topping getTopping2() {
        return topping2;
    }

    public void setTopping2(Topping topping2) {
        this.topping2 = topping2;
    }

    public Topping getTopping3() {
        return topping3;
    }

    public void setTopping3(Topping topping3) {
        this.topping3 = topping3;
    }

    @Override
    public int generarTransaccion() {
        return this.n + 1;
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
