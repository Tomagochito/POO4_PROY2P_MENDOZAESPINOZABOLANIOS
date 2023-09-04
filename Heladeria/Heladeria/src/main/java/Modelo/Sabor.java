/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Clase de Sabores
 * @author tomas
 */

public class Sabor implements Serializable, Comparable<Sabor> {

    /**
     * Lista estatica de sabores
     */
    public static ArrayList<Sabor> sabores;
    /**
     * Nombre del sabor
     */
    private String nombreSabor;
    /**
     * Precio del sabor
     */
    private double precioSabor;
    
    /**
     * Constructor de la clase Sabor
     * @param nombreSabor El nombre del sabor
     * @param precioSabor El precio del sabor
     */
    public Sabor(String nombreSabor, double precioSabor) {
        this.nombreSabor = nombreSabor;
        this.precioSabor = precioSabor;
    }

    /**
     * Obtener el nombre del Sabor
     * @return El nombre del sabor
     */
    public String getNombreSabor() {
        return nombreSabor;
    }
/**
 * Cambiar el nombre del Sabor
 * @param nombreSabor El nombre del nuevo Sabor
 */
    public void setNombreSabor(String nombreSabor) {
        this.nombreSabor = nombreSabor;
    }
/**
 * Obtiene el precio del Sabor
 * @return el precio del sabor
 */
    public double getPrecioSabor() {
        return precioSabor;
    }

    /**
     * Cambiar el precio del sabor
     * @param precioSabor El nuevo precio del Sabor
     */
    public void setPrecioSabor(double precioSabor) {
        this.precioSabor = precioSabor;
    }

/**
 * Implementacion propia del metodo ToString
 * @return los datos del Sabor
 */
    @Override
    public String toString(){
        return this.getNombreSabor()+" - "+ String.valueOf(this.precioSabor);
    } 

    /**
     * Implementación de compareTo para ordenar por nombre de sabor
     * @param otroSabor El otro sabor con el que se compara
     * @return Un valor negativo si este sabor es menor, cero si son iguales,
     *         o un valor positivo si este sabor es mayor en orden alfabético.
     */
    @Override
    public int compareTo(Sabor otroSabor) {
        return this.nombreSabor.compareTo(otroSabor.nombreSabor);
    }

}
