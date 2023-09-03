/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Clase de Toppings
 * @author tomas
 */

public class Topping implements Serializable {
    public static ArrayList<Topping> toppings;
    private String nombreTopping;
    private double precioTopping;
/**
 * Implementacion del toString
 * @return Datos del topping
 */
    @Override
    public String toString() {
        return nombreTopping+" - "+precioTopping;
    }
/**
 * Constructor del Topping
 * @param nombreTopping El nombre del Topping
 * @param precioTopping El precio del Topping
 */
    public Topping(String nombreTopping, double precioTopping) {
        this.nombreTopping = nombreTopping;
        this.precioTopping = precioTopping;
    }

/**
 * Acceder a el nombre del Topping
 * @return Nombre del Topping
 */
    public String getNombreTopping() {
        return nombreTopping;
    }
/**
 * Cambiar el nombre del Topping
 * @param nombreTopping Nombre del Topping
 */
    public void setNombreTopping(String nombreTopping) {
        this.nombreTopping = nombreTopping;
    }
/**
 * Acceder a el preico de Topping
 * @return Retorna el precio del topping 
 */
    public double getPrecioTopping() {
        return precioTopping;
    }
/**
 * Cambiar el precio del Topping
 * @param precioTopping El precio nuevo del topping
 */
    public void setPrecioTopping(double precioTopping) {
        this.precioTopping = precioTopping;
    }
    
    
}
