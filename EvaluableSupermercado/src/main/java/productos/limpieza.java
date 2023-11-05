/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos;

/**
 *
 * @author monke
 */
public class limpieza extends producto {
    
    private final boolean toxico;
    
    //-------------------------------
    //        CONSTRUCTORES
    //-------------------------------
    
    //Con oferta
    public limpieza(int codigo, String nombre, double precio, boolean enOferta, int oferta, int stock, boolean toxico) {
        super(codigo, nombre, precio, enOferta, oferta, stock);
        this.toxico = toxico;
    }
    
    //Sin oferta
    public limpieza(int codigo, String nombre, double precio, int stock, boolean toxico) {
        super(codigo, nombre, precio, stock);
        this.toxico = toxico;
    }
    
    //-------------------------------
    //              GET
    //-------------------------------
    
    public boolean gettoxico() {
        return toxico;
    }
}
