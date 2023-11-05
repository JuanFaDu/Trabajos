/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lhopital;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;

/**
 *
 * @author monke
 */
public class ListadeEspera {
    private LinkedList<Paciente> colaTriaje = new LinkedList<>();
    private PriorityQueue<Paciente> colaMedico = new PriorityQueue<>();
    private LinkedList<Paciente> colaAtendido = new LinkedList<>();
    
    public ListadeEspera() {
        
    }
    
    public boolean creaPaciente(String nombre, String apellidos, String num_tarjeta) {
        return colaTriaje.offer(new Paciente(nombre, apellidos, num_tarjeta));
    }
    public boolean triajePaciente(int prioridad, double temperatura, double peso, String reporte_paciente) {
        Paciente a = colaTriaje.poll();
        a.setTemperatura(temperatura);
        a.setPeso(peso);
        a.setReporte_paciente(reporte_paciente);
        return colaMedico.offer(a);
    }
    
    public void muestraPacientes() {
        LinkedList<Paciente> prueba = new LinkedList<>();
        prueba.addAll(colaTriaje);
        while (prueba.size() > 6) {
            prueba.remove();
        }
        ListIterator<Paciente> iterator = prueba.listIterator(prueba.size());
        while (iterator.hasPrevious()) {
            Paciente a = iterator.previous();
            colaAtendido.add(a);
        }
        for (Paciente recorrer : colaAtendido) {
            System.err.println(recorrer);
        }
    }
}
