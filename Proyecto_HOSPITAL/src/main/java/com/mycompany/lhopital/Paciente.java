/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lhopital;
import java.time.LocalDateTime;
import java.util.Random;
/**
 *
 * @author monke
 */
public class Paciente implements Comparable<Paciente> {
    private Integer prioridad;
    private LocalDateTime fecha_entrada;
    private double temperatura, peso;
    private String num_tarjeta, cod_paciente, nombre, apellidos, reporte_paciente, juicio_clinico, medico;
    
    public Paciente (String nombre, String apellidos, String num_tarjeta) {
        Random random = new Random();
        cod_paciente = nombre.charAt(1) + apellidos.charAt(1) + String.valueOf(random.nextInt()) + String.valueOf(random.nextInt()) + String.valueOf(random.nextInt());
        fecha_entrada = LocalDateTime.now();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.num_tarjeta = num_tarjeta;
        medico = "Triaje";
        prioridad = 0;
        temperatura = 0;
        peso = 0;
        reporte_paciente = null;
        juicio_clinico = null;
        
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public LocalDateTime getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(LocalDateTime fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCod_paciente() {
        return cod_paciente;
    }

    public void setCod_paciente(String cod_paciente) {
        this.cod_paciente = cod_paciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getReporte_paciente() {
        return reporte_paciente;
    }

    public void setReporte_paciente(String reporte_paciente) {
        this.reporte_paciente = reporte_paciente;
    }

    public String getJuicio_clinico() {
        return juicio_clinico;
    }

    public void setJuicio_clinico(String juicio_clinico) {
        this.juicio_clinico = juicio_clinico;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "Paciente{" + "cod_paciente=" + cod_paciente + ", medico=" + medico + '}';
    }

    @Override
    public int compareTo(Paciente o) {
        return this.prioridad.compareTo(o.prioridad);
    }
}
