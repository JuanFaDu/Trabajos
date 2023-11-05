/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientes;

/**
 *
 * @author monke
 */
public class clientes {
    private final String nombrecliente;
    private final int [][] productoscliente = new int [10] [2];
    
    //-------------------------------
    //        CONSTRUCTOR
    //-------------------------------
    
    public clientes (String nombre) {
        nombrecliente = nombre;
    }
        
    //-------------------------------
    //              GET
    //-------------------------------
    
    public String getnombre() {
        return nombrecliente;
    }
    public void getproducto() {
        for (int[] productoscliente1 : productoscliente) {
            if (productoscliente1[0] != 0) {
                System.out.println(productoscliente1[0] + "            " + productoscliente1[1]);
            }
        }
    }

    //-------------------------------
    //           METODOS
    //-------------------------------
    
    //Método que recorre el array de productos en busca de un hueco libre 
    //e introduce el código y la cantidad que se le pasan
    public boolean introduceproducto (int codigo, int stock) {
        int i=0;
        while (i<productoscliente.length-1 && productoscliente[i][0]!=0) {
            i++;
        }
        if (codigo <= 0 || codigo > 10) {
            System.out.println("Producto inexistente. Por favor, introduzca un código válido");
            return false;
        }
        else if (i<productoscliente.length && productoscliente[i][0]==0) {
            productoscliente [i][0] = codigo;
            productoscliente[i][1] = stock;  
            return true;
        }
        else {
            System.out.println ("Límite de productos alcanzado. Pruebe a borrar algún producto o a realizar la compra. Disculpe las molestias");                
            return false;
        }
    }
    
    //Método que recorre el array de productos en busca del codigo que se le pasa y borra
    //tanto el codigo como la cantidad de este
    public boolean borraproducto (int codigo) {
        int z=0;
        while (productoscliente[z][0] != codigo && z<productoscliente.length-1) {
             z++;
        }
            if (codigo == productoscliente[z][0]) {
                productoscliente[z][0]=0;
                productoscliente[z][1]=0;
                return true;
            }
            else {
                return false;
            }
    }
    
    //Método que recorre el array de productos en busca del codigo que se le pasa y
    //modifica la cantidad que había con el nuevo valor que se le pasa
    public boolean modificaproducto (int codigo, int cantidad) {
        int z=0;
        while (productoscliente[z][0] != codigo && z<productoscliente.length-1) {
             z++;
        }
            if (codigo == productoscliente[z][0]) {
                productoscliente[z][1]=cantidad;
                return true;
            }
            else {
                return false;
            }
    }
}
