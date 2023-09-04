/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tomas
 */
public class Base implements Comparable<Base>,Serializable {
    /**
     * Lista de bases, capaz de ser llamada desde otras clases
     */
    public static ArrayList<Base> bases;
    /**
     * Nombre base
     */
    private String nombreBase;
    /**
     * Precio base
     */
    private double precioBase; 
    /**
     * 
     * @param nombreBase Nombre de la base del helado
     * @param precioBase Precio de la base del helado
     */
    public Base(String nombreBase, double precioBase) {
        this.nombreBase = nombreBase;
        this.precioBase = precioBase;
    }

    /**
     * 
     * @return Nombre de la base del helado 
     */
    public String getNombreBase() {
        return nombreBase;
    }
    /**
     * Cammbia el nombre de la base por nombreBase de ser necesario
     * @param nombreBase Nuevo nombre de la base
     */
    public void setNombreBase(String nombreBase) {
        this.nombreBase = nombreBase;
    }
    /**
     * 
     * @return Precio de la base 
     */
    public double getPrecioBase() {
        return precioBase;
    }
    /**
     * Cammbia el precio de la base por precioBase de ser necesario
     * @param precioBase Nuevo precio de la base
     */
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
    
    /**
     * Metodo para poder ordenar las bases por su nombre
     * @param b La basecon la que se compara
     * @return Orden de la base por su nombre 
     */
    @Override
    public int compareTo(Base b){
        return this.getNombreBase().compareTo(b.getNombreBase());
    }
    
    /**
     * 
     * @param obj Base con la que se compara
     * @return Booleano que confirma si las bases son iguales o no
     */
    @Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    Base base = (Base) obj;
    String nombreBase1 = this.getNombreBase().trim();
    String nombreBase2 = base.getNombreBase().trim();
    return nombreBase1.equalsIgnoreCase(nombreBase2);
    } 
}
