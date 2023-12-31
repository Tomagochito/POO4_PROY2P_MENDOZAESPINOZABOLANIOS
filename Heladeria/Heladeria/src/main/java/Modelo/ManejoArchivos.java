/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
//Guillermo
import static Modelo.Cliente.clientes;
import static Modelo.Base.bases;
import static Modelo.Sabor.sabores;
import static Modelo.Topping.toppings;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author tomas
 */
public class ManejoArchivos {
    /**
     * Path que lleva a la localizacion de los archivos
     */
    /**
     * Ruta de los archivos del proyecto
     */
    public static final String rutaArchivos="archivos/";
    /**
     * Ruta de las imagenes del proyecto
     */
    public static final String rutaImagenes="imagenes/";

        
    /**
     * 
     * @param nombre_archivo se inserta la ruta del archivo que se va a leer.
     * @return  ArrayList de los datos del archivo
     */
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
    
    /**
     * 
     * @return ArrayList de objetos de la clase clientes, que se utiliza para la 
     * posterior validacion de cada cliente
     */   
    public static ArrayList<Cliente> listaClientes(){
        ArrayList<String> clientestxt = ManejoArchivos.leerArchivoTexto("clientes.txt");
         clientes=new ArrayList<>();
         for (String cliente : clientestxt) {
            String[] c = cliente.split(",");
            clientes.add(new Cliente (c[0],c[1]));
         }
         return clientes;
    }
    /**
     * 
     * @return ArrayList de objetos de la clase Base, la cual se utiliza en la ventana de Bases
     * para mostrar las bases de helados disponibles al cliente.
     */
    public static ArrayList<Base> listaBases(){
        ArrayList<String> basestxt = ManejoArchivos.leerArchivoTexto("bases.txt");
         bases=new ArrayList<>();
         for (String base : basestxt) {
            String[] c = base.split(",");
            bases.add(new Base ( c[0], Double.parseDouble( c[1]) ) );
         }
         return bases;
    }
    
    /**
     * 
     * @return ArrayList de objetos de la clase Sabor, la cual se utiliza en la ventana de Sabores
     * para mostrar los sabores de helados disponibles al cliente.
     */
    public static ArrayList<Sabor> listaSabores(){
        ArrayList<String> saborestxt = ManejoArchivos.leerArchivoTexto("sabores.txt");
         sabores=new ArrayList<>();
         for (String sabor : saborestxt) {
            String[] c = sabor.split(",");
            sabores.add(new Sabor ( c[0], Double.parseDouble( c[1]) ) );
         }
         return sabores;
    }
        
    /**
    * 
    * @return ArrayList de objetos de la clase Topping, la cual se utiliza en la ventana de Toppings
     * para mostrar los toppings de helados disponibles al cliente.
    */
    public static ArrayList<Topping> listaToppings(){
        ArrayList<String> toppingstxt = ManejoArchivos.leerArchivoTexto("toppings.txt");
         toppings=new ArrayList<>();
         for (String topping : toppingstxt) {
            String[] c = topping.split(",");
            toppings.add(new Topping ( c[0], Double.parseDouble( c[1]) ) );
         }
         return toppings;
    }
    
        
        
     /**
      * 
      * @param nombre_archivo Nombre del archivo que se va a escribir
      * @param agregar Booleano que permite agregar o no el archivo
      * @param contenido La linea que se va a escribir en el archivo
      */   
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
    /**
     * 
     * @param texto Texto al que se le va a aplicar la capitalizacion(Poner la primera letra en mayuscula)
     * @return Texto capitalizado
     */
    public static String capitalizar(String texto) {
    if (texto == null || texto.isEmpty()) {
        return texto;
    }   
    String primeraLetra = texto.substring(0, 1).toUpperCase();
    String restoDePalabra = texto.substring(1).toLowerCase();
    
    return primeraLetra + restoDePalabra;
}
        
}
