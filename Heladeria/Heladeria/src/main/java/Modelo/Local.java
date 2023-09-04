/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
//Guillermo
/**
 *
 * @author User
 */
public class Local {

    private Double ejex;
    private Double ejey;
    private String lugar;
    private String horario;
    /**
     * 
     * @param ejex Posicion x del local
     * @param ejey Posicion y del local
     * @param lugar Lugar del local
     * @param horario Horario de atención del local
     */
    public Local(Double ejex, Double ejey, String lugar, String horario) {
        this.ejex = ejex;
        this.ejey = ejey;
        this.lugar = lugar;
        this.horario = horario;
    }
    /**
     * 
     * @return Forma en la que se muestra la informacion de un local
     */
    @Override
    public String toString() {
        return "Local{" + "ejex=" + ejex + ", ejey=" + ejey + ", lugar=" + lugar + ", horario=" + horario + '}';
    }
    /**
     * 
     * @return Posicion X del local
     */
    public Double getEjex() {
        return ejex;
    }
    /**
     * 
     * @param ejex Posicion nueva del local en X
     * 
     */
    public void setEjex(Double ejex) {
        this.ejex = ejex;
    }
    /**
     * 
     * @return Posicion Y del local
     */
    public Double getEjey() {
        return ejey;
    }
    /**
     * 
     * @param ejey Posicion nueva del local en Y
     */
    public void setEjey(Double ejey) {
        this.ejey = ejey;
    }
    /**
     * 
     * @return Lugar del local
     */
    public String getLugar() {
        return lugar;
    }
    /**
     * 
     * @param lugar Nuevo lugar del local
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    /**
     * 
     * @return Horario de atencion del local
     */
    public String getHorario() {
        return horario;
    }
    /**
     * 
     * @param horario Cambia el horaio de atencion del local
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }
    
}
