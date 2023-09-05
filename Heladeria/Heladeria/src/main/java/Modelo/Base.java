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
    * Una lista estática de objetos `Base`.
    */
    public static ArrayList<Base> bases;
    
    private String nombreBase;
    private double precioBase; 
    /**
     * 
     * @param nombreBase el nombre de la base
     * @param precioBase el precio de la base
     */
    public Base(String nombreBase, double precioBase) {
        this.nombreBase = nombreBase;
        this.precioBase = precioBase;
    }

    /**
     * Obtiene el nombre de la base.
     * 
     * @return nombreBase de la base
     */
    public String getNombreBase() {
        return nombreBase;
    }
    
    /**
     * Establece el nombre de la base.
     *
     * @param nombreBase El nuevo nombre de la base.
     */
    public void setNombreBase(String nombreBase) {
        this.nombreBase = nombreBase;
    }
    
    /**
     * Obtiene el precio de la base
     * 
     * @return precioBase de la base
     */
    public double getPrecioBase() {
        return precioBase;
    }
    
    /**
     * Establece el precio de la base.
     *
     * @param precioBase El nuevo precio de la base.
     */
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
    
    /**
     * Compara esta base con otra base para determinar su orden acorde al orden alfabetico.
     *
     * @param b La base con la que se compara.
     * @return Un valor negativo si esta base es menor que la base proporcionada,
     *         un valor positivo si es mayor y 0 si son iguales.
     */    
    @Override
    public int compareTo(Base b){
        return this.getNombreBase().compareTo(b.getNombreBase());
    }

    /**
     * Compara esta base con otro objeto para determinar si son iguales por su nombre ignorando mayusculas y minusculas.
     *
     * @param obj El objeto con el que se compara.
     * @return `true` si son iguales, `false` en caso contrario.
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