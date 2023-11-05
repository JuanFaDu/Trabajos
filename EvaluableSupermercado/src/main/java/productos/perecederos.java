/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos;

/**
 *
 * @author monke
 */
public class perecederos extends producto {
    private String fecha;
    
    //-------------------------------
    //        CONSTRUCTORES
    //-------------------------------
    
    //Con oferta
    public perecederos(int codigo, String nombre, double precio, boolean enOferta, int oferta, int stock, String fecha) {
        super(codigo, nombre, precio, enOferta, oferta, stock);
        this.fecha = fecha;
    }
    
    //Sin oferta
    public perecederos(int codigo, String nombre, double precio, int stock, String fecha) {
        super(codigo, nombre, precio, stock);
        this.fecha = fecha;
    }
    
    //-------------------------------
    //              GET
    //-------------------------------
    
    public String getfecha() {
        return fecha;
    }
    
    //-------------------------------
    //              SET
    //-------------------------------
    
    public void setfecha(String fecha) {
        this.fecha = fecha;
    }
    
}
