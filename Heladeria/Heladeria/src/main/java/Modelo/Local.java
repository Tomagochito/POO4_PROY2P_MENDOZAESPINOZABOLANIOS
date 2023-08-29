/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author User
 */
public class Local {

    private Double ejex;
    private Double ejey;
    private String lugar;

    public Local(Double ejex, Double ejey, String lugar, String horario) {
        this.ejex = ejex;
        this.ejey = ejey;
        this.lugar = lugar;
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Local{" + "ejex=" + ejex + ", ejey=" + ejey + ", lugar=" + lugar + ", horario=" + horario + '}';
    }

    public Double getEjex() {
        return ejex;
    }

    public void setEjex(Double ejex) {
        this.ejex = ejex;
    }

    public Double getEjey() {
        return ejey;
    }

    public void setEjey(Double ejey) {
        this.ejey = ejey;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    private String horario;
}
