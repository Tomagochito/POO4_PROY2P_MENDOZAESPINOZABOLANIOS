/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
//Guillermo
import java.util.ArrayList;

/**
 *
 * @author tomas
 */
public class Cliente {
    private String usuario;
    private String clave;
    /**
     * Lista de los clientes estatica para que sea posible accder a ella desde todo el proyecto.
     */
    public static ArrayList<Cliente> clientes;
    /**
     * 
     * @param usuario Usuario del cliente
     * @param clave Clave del cliente
     */
    public Cliente(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
    /**
     * 
     * @return Usuario del cliente
     */
    public String getUsuario() {
        return usuario;
    }
    /**
     * 
     * @param usuario Nuevo usuario del cliente
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    /**
     * 
     * @return Clave del cliente
     */
    public String getClave() {
        return clave;
    }
    /**
     * 
     * @param clave Nueva clave del cliente 
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
    


    /**
     * 
     * @param obj Obejto a comparar
     * @return Booleano que afirma si la comparación es acertada o no
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cliente clientedc = (Cliente) obj;
        return this.usuario.equals(clientedc.getUsuario()) && this.clave.equals(clientedc.getClave());

    }
    
    
    
}
