/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.lhopital;

import java.util.Scanner;

/**
 *
 * @author monke
 */
public class LHOPITAL {

    public static void main(String[] args) {
        ListadeEspera lista = new ListadeEspera();
        Scanner sc = new Scanner (System.in);
        lista.creaPaciente("Pepe", "Rodriguez", "AAA");
        lista.creaPaciente("Bebe", "Todriguez", "BBB");
        int opc=0;
        opc = sc.nextInt();
        switch (opc) {
            case 1:
                lista.muestraPacientes();
                break;
            case 2:
                break;
            default:
                break;
        }
    }
}
