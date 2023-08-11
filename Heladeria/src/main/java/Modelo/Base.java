/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author tomas
 */
public class Base implements Comparable<Base> {
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
    
    
}
