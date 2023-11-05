/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos;

/**
 *
 * @author monke
 */
public class producto {
    //Declaramos los atributos como protected para que se puedan heredar
    protected int codigo;
    protected String nombre;
    protected double precio;
    protected boolean enOferta;
    protected int oferta;
    public int stock;
    
    //-------------------------------
    //              GET
    //-------------------------------
    
    public int getcodigo() {
        return codigo;
    }
    public String getnombre() {
        return nombre;
    }
    public double getprecio() {
        return precio;
    }
    public boolean getenOferta() {
        return enOferta;
    }
    public int getoferta() {
        return oferta;
    }
    public int getstock() {
        return stock;
    }
    
    //-------------------------------
    //              SET
    //-------------------------------
    
    public void setstock (int stock) {
        this.stock = stock;
    }
    
    //-------------------------------
    //        CONSTRUCTORES
    //-------------------------------
    
    //Constructor para produtos en oferta
    public producto (int codigo, String nombre, double precio, boolean enOferta, int oferta, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.enOferta = enOferta;
        this.oferta = oferta;
        this.stock = stock;
    }
    
    //Constructor para productos sin oferta
    public producto (int codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.enOferta = false;
        this.oferta = 0;
        this.stock = stock;
    }
    
    //-------------------------------
    //           METODOS
    //-------------------------------
    
    //Método que calcula el precio final de los productos que cuenten con una oferta
    public double preciofinal() {
        return (precio - ((precio * oferta)/100));
    }
    
    
    //Método que nos devolverá el precio de la unidad rebajada en caso de que el producto tenga oferta
    //o "sin oferta" en caso de que no la tenga
    public String ofertiña() {
        double preciototal;
        if (enOferta) {
            preciototal = precio - ((precio * oferta)/100);
            String preciorebajado=String.valueOf(preciototal);
            return preciorebajado;
        }
        else {
            return ("Sin oferta");
        }
    } 
}
