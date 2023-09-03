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
public class Base implements Comparable<Base> {
    public static ArrayList<Base> bases;
    
    private String nombreBase;
    private double precioBase; 

    public Base(String nombreBase, double precioBase) {
        this.nombreBase = nombreBase;
        this.precioBase = precioBase;
    }


    public String getNombreBase() {
        return nombreBase;
    }

    public void setNombreBase(String nombreBase) {
        this.nombreBase = nombreBase;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
    
    
    @Override
    public int compareTo(Base b){
        return this.getNombreBase().compareTo(b.getNombreBase());
    }

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
