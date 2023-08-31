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
public class Sabor {
    public static ArrayList<Sabor> sabores;
    private String nombreSabor;
    private double precioSabor;

    public Sabor(String nombreSabor, double precioSabor) {
        this.nombreSabor = nombreSabor;
        this.precioSabor = precioSabor;
    }


    public String getNombreSabor() {
        return nombreSabor;
    }

    public void setNombreSabor(String nombreSabor) {
        this.nombreSabor = nombreSabor;
    }

    public double getPrecioSabor() {
        return precioSabor;
    }

    public void setPrecioSabor(double precioSabor) {
        this.precioSabor = precioSabor;
    }


    @Override
    public String toString(){
        return this.getNombreSabor()+" - "+ String.valueOf(this.precioSabor);
    } 

    
}
