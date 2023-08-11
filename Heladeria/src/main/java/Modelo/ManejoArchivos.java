/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import static Modelo.Cliente.clientes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.concurrent.Task;
import proyectopoo.heladeria.App;

/**
 *
 * @author tomas
 */
public class ManejoArchivos {
    
    public static final String rutaArchivos="src/main/resources/proyectopoo/heladeria/archivos/";
        
    
    public static ArrayList<String> leerArchivoTexto(String nombre_archivo){

        ArrayList<String> informacion = new ArrayList<>();

        try(BufferedReader br = new BufferedReader ( new FileReader  (new File (rutaArchivos + nombre_archivo)) ) ){
            String datos = br.readLine();
            while(datos!=null){
 
                informacion.add(datos);
                datos=br.readLine();
            }

        }
        catch(FileNotFoundException fnfe){
            System.out.println("Archivo no encontrado");
        }
        catch(IOException ioe){
            System.out.println("Ha ocurrido una excepcion de tipo IOException");
        }

       return informacion;
    }//cierre del metodo
    
    
    
    
       
    public static ArrayList<Cliente> listaClientes(){
        ArrayList<String> clientestxt = ManejoArchivos.leerArchivoTexto("clientes.txt");
         clientes=new ArrayList<>();
         for (String cliente : clientestxt) {
            String[] c = cliente.split(",");
            clientes.add(new Cliente (c[0],c[1]));
         }
         return clientes;
    }
    
        
        
    public static void escribirArchivoTextoPedido(String nombre_archivo, boolean agregar, String contenido){
        try(BufferedWriter bw = new BufferedWriter (new FileWriter (nombre_archivo, agregar) ) ){
            // escritura
            bw.write(contenido/*+"\n"*/);//si el contenido es una string debe tener salto de linea antes , en caso de añadir info
        }// se cierra el try
        catch(FileNotFoundException fnfe){
            System.out.println("El archivo al que le deseaba agregar informacion no fue encontrado en la ruta especificada");
        }
        catch(IOException ioe){
            System.out.println("Ocurrio una excepcion de tipo IOException");
        }//se cierra el catch
    }// se cierra el metodo
    
    
    
    

        
}
