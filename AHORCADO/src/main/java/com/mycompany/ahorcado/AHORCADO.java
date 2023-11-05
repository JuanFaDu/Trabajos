/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ahorcado;
import java.util.Scanner;
/**
 *
 * @author Equipo
 */
public class AHORCADO {

    public static void main(String[] args) {
        String frase, letra, adivina="";
        char x;
        int y, z, i, vidas=6;
        Scanner sc = new Scanner (System.in);
        //El programa pide al jugador 1 una palabra y la guarda en minúsculas
        System.out.println("Jugador 1: Introduzca la palabra a adivinar: ");
        frase = sc.nextLine();
        frase = frase.toLowerCase();
        /*El programa genera una palabra hecha de guiones medios con la longitud
        de la palabra introducida por el jugador 1
        */
        for (i=0; i<frase.length(); i++) {
            adivina = adivina + "-";
        }
        System.out.println(adivina);
        StringBuilder construir = new StringBuilder(adivina);
        //El programa se refiere al jugador 2
        System.out.println("Jugador 2: ");
        /*Bucle while para continuar la partida. Esta continuará si al jugador 2
        tiene al menos 1 vida y no ha adivinado la palabra
        */
        while (vidas > 0 && !adivina.equalsIgnoreCase(frase)) {
            /*Se pide una letra al jugador 2, la cual será transformada a minúscula
            en caso de que se introduzca como mayúscula. Si introduce más de una,
            el programa solo leerá la primera
            */
            System.out.println("Introduzca una letra para adivinar la palabra. Tiene " + vidas + " vidas restantes");
            letra = sc.nextLine();
            letra = letra.toLowerCase();
            letra = letra.substring(0,1);
            y = frase.indexOf(letra);
            /*Se comprueba si la letra introducida por el jugador 2 está en la 
            palabra introducida por el jugador 1
            */
            if (y == -1) {
                vidas--;
                /*Switch para devolver por pantalla la figurita del ahorcado en
                función de las vidas restantes
                */
                switch (vidas) {
                    case 5:
                        System.out.println(" o");
                    break;
                    case 4:
                        System.out.println(" o");
                        System.out.println("/");
                    break;
                    case 3:
                        System.out.println(" o");
                        System.out.println("/|");
                    break;
                    case 2:
                        System.out.println(" o");
                        System.out.println("/|\\");
                    break;
                    case 1:
                        System.out.println(" o");
                        System.out.println("/|\\");
                        System.out.println("/");
                    break;
                    case 0:
                        System.out.println(" o");
                        System.out.println("/|\\");
                        System.out.println("/\\");
                    break;
                }
            }
            else {
                /*Se lee la letra introducida y su posición en la palabra del jugador 1.
                Con el StringBuilder, se modifica la palabra formada por guiones
                para sustituir dicha posición con dicha letra
                */
                x = frase.charAt(y);
                construir.setCharAt(y,x);
                y= y+1;
                z = frase.indexOf(letra, y);
                //Bucle para cuando la letra se repita en la palabra
                while (z!=-1) {
                    x = frase.charAt(z);
                    construir.setCharAt(z,x);
                    z=z+1;
                    z = frase.indexOf(letra, z);
                    }
                adivina = construir.toString();
                System.out.println(adivina);
            }
        }
        /*Al salir del bucle anterior, hay dos opciones: haber ganado o 
        haber perdido todas las vidas
        */
        if (vidas ==0) {
            System.out.println("HAS PERDIDO");
        }
        else {
            System.out.println("HAS GANADO");
        }
    }
}