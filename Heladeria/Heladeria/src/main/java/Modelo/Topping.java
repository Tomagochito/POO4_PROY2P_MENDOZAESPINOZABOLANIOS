/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author tomas
 */
public class Topping {
    public static ArrayList<Topping> toppings;
    private String nombreTopping;
    private double precioTopping;

    @Override
    public String toString() {
        return nombreTopping+" - "+precioTopping;
    }

    public Topping(String nombreTopping, double precioTopping) {
        this.nombreTopping = nombreTopping;
        this.precioTopping = precioTopping;
    }


    public String getNombreTopping() {
        return nombreTopping;
    }

    public void setNombreTopping(String nombreTopping) {
        this.nombreTopping = nombreTopping;
    }

    public double getPrecioTopping() {
        return precioTopping;
    }

    public void setPrecioTopping(double precioTopping) {
        this.precioTopping = precioTopping;
    }
    
    
}
