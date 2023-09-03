/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *Clase para la excepcion IncompleteStageException
 * @author tomas
 */
public class IncompleteStageException extends Exception{
    
    /**
     * Implementacion del metodo IncompleteStageException
     * @param message Sera el mensaje que se lance cuando ocurra la excepcion 
     */
    public IncompleteStageException(String message){
        super(message);
    }
}
