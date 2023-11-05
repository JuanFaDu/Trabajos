/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos;

/**
 *
 * @author monke
 */
public class ropa extends producto {
    
    private final String talla;
    
    //-------------------------------
    //        CONSTRUCTORES
    //-------------------------------
    
    //Con oferta
    public ropa(int codigo, String nombre, double precio, boolean enOferta, int oferta, int stock, String talla) {
        super(codigo, nombre, precio, enOferta, oferta, stock);
        this.talla = talla;
    }
    
    //Sin oferta
    public ropa(int codigo, String nombre, double precio, int stock, String talla) {
        super(codigo, nombre, precio, stock);
        this.talla = talla;
    }
    
    //-------------------------------
    //              GET
    //-------------------------------
    
    public String gettalla() {
        return talla;
    }
}
