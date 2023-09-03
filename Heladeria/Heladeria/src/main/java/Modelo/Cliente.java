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
    public static ArrayList<Cliente> clientes;

    public Cliente(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    



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
/*
Generar clientes, no pude hacer mas porque al hacer commit cada archivo individual no puede exceder 100 mb
asi que le reduje a 50000

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomDataGenerator {

    private static final String[] NAMES = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Helen", "Ivy", "Jack"};

    private static String generateRandomName() {
        return NAMES[new Random().nextInt(NAMES.length)];
    }

    public static void main(String[] args) {
        long numLines = 50000;
        String fileName = "datos_aleatorios.txt";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/randomdatagenerator/clientesArchivoGrande.txt"))) {
            for (long i = 1; i <= numLines; i++) {
                String name = generateRandomName();
                String line = name + "," + i;

                bufferedWriter.write(line);
                bufferedWriter.newLine(); // Agrega un salto de línea según el sistema operativo
            }
            System.out.println("Archivo generado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

*/