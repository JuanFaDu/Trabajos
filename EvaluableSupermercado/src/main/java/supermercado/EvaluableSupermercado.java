/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package supermercado;


//Importamos el resto de clases a la clase main
import productos.limpieza;
import productos.perecederos;
import productos.producto;
import productos.ropa;
import clientes.clientes;
import java.util.Scanner;
/**
 *
 * @author monke
 */
public class EvaluableSupermercado {

    public static void main(String[] args) {
        //Creamos las variables y herramientas necesarias para nuestro programa
        Scanner sc = new Scanner (System.in);
        String nombre;
        int i=0, z=0, cod, cant;
        double preciototal=0;
        //Este array va a almacenar el precio de los productos que vayamos añadiendo al carro
        double array[][] = new double [10][2];
        //Aquí creamos el objeto de cada producto
        perecederos naranja = new perecederos(1, "Naranja Valenciana", 1.98, 10000, "30/02/2023");
        perecederos carne = new perecederos(2, "Filete de primera", 5.75, 10000, "30/02/2023");
        perecederos yogurt = new perecederos(3, "Yogurt Natural", 0.90, true, 30, 20, "12/02/2023");
        perecederos pan = new perecederos(4, "Pan integral", 1.23, 25, "09/02/2023");
        perecederos huevos = new perecederos(5, "Docena huevos L", 1.25, 20, "28/02/2023");
        perecederos aceite = new perecederos(6, "1L AOVE", 4.95, true, 50, 50, "28/12/2023");
        producto papelhigienico = new producto(7, "3 capas plus", 3.50, 10);
        limpieza limpiavitro = new limpieza(8, "Limpieza Plus Eco", 1.50, 7, false);
        limpieza lejia = new limpieza(9, "Lejia con detergente", 1.00, 6, true);
        ropa camiseta = new ropa(10, "Camiseta", 9.50, 15, "L");
        //Pedimos el nombre
        System.out.println("Por favor ingrese su nombre");
        nombre = sc.next();
        //Creamos el objeto cliente
        clientes cliente = new clientes (nombre);
        //Mostramos por pantalla todos los productos disponibles
        System.err.println("");
        System.out.println("Actualmente tenemos los siguientes productos en el supermercado");
        System.out.println("Código Nombre               Precio     Stock     Precio en oferta");
        System.out.println(naranja.getcodigo() + "      " + naranja.getnombre() + "       "  + naranja.getprecio() + "€/kg " + naranja.getstock() + "g       " + naranja.ofertiña());
        System.out.println(carne.getcodigo() + "      " + carne.getnombre() + "        " + carne.getprecio() + "€/kg " + carne.getstock() + "g       " + carne.ofertiña());
        System.out.println(yogurt.getcodigo() + "      " + yogurt.getnombre() + "            " + yogurt.getprecio() + "€    " + yogurt.getstock() + "           " + yogurt.ofertiña() + "€");
        System.out.println(pan.getcodigo() + "      " + pan.getnombre() + "             " + pan.getprecio() + "€    " + pan.getstock() + "           " + pan.ofertiña());
        System.out.println(huevos.getcodigo() + "      " + huevos.getnombre() + "          " + huevos.getprecio() + "€    " + huevos.getstock() + "           " + huevos.ofertiña());
        System.out.println(aceite.getcodigo() + "      " + aceite.getnombre() + "                  " + aceite.getprecio() + "€    " + aceite.getstock() + "           " + aceite.ofertiña() + "€");
        System.out.println(papelhigienico.getcodigo() + "      " + papelhigienico.getnombre() + "              " + papelhigienico.getprecio() + "€    " + papelhigienico.getstock() + "           " + papelhigienico.ofertiña());
        System.out.println(limpiavitro.getcodigo() + "      " + limpiavitro.getnombre() + "         " + limpiavitro.getprecio() + "€     " + limpiavitro.getstock() + "           " + limpiavitro.ofertiña());
        System.out.println(lejia.getcodigo() + "      " + lejia.getnombre() + "      " + lejia.getprecio() + "€     " + lejia.getstock() + "           " + lejia.ofertiña());
        System.out.println(camiseta.getcodigo() + "     " + camiseta.getnombre() + "                  " + camiseta.getprecio() + "€    " + camiseta.getstock() + "           " + camiseta.ofertiña());
        System.err.println("");
        //Bucle while para que el usuario pueda repetir las acciones mientras que no pulse 7
        while (i!=7) {
            //Mostramos las opciones disponibles por pantalla
            System.err.println("");
            System.err.println("Elija que desea hacer a continuación");
            System.err.println("Pulse 1 para añadir artículo");
            System.err.println("Pulse 2 para borrar artículo");
            System.err.println("Pulse 3 para modificar cantidad de un artículo");
            System.err.println("Pulse 4 para mostrar carrito");
            System.err.println("Pulse 5 para listar productos disponibles");
            System.err.println("Pulse 6 para ver el precio total");
            System.err.println("Pulse 7 para efectuar compra");
            System.err.println("");
            i=sc.nextInt();
            //Creamos un swithc en función de la opción que haya introducido el usuario
            switch (i) {
                //En caso de pulsar 1, se pedirá el código y la cantidad del producto, y se llamará al método para
                //introducir el producto en el carrito
                case 1:
                    System.out.println("Introduzca el código del artículo");
                    cod = sc.nextInt();
                    System.out.println("Introduzca la cantidad desea, en productos a granel introduzca los gramos");
                    cant = sc.nextInt();
                    cliente.introduceproducto(cod, cant);
                    z=0;
                    //Switch para calcular el precio de lo que el usuario haya guardado en su carrito.
                    //Si el producto es agranel, la operación para calcular el precio será distinta.
                    //El switch va en función al código del producto.
                    switch (cod) {
                        case 1:
                            //Bucle while para recorrer el array de precios en busca de un hueco libre
                            while (z<array.length-1 && array[z][0]!=0) {
                                z++;
                            }
                            //Se almacena el código y el precio del producto. El resto de casos del switch son iguales
                            array[z][0] = cod;
                            array[z][1] = (naranja.preciofinal()/1000)*cant;
                            break;
                        case 2:
                            while (z<array.length-1 && array[z][0]!=0) {
                                z++;
                            }
                            array[z][0] = cod;
                            array[z][1] = (carne.preciofinal()/1000)*cant;
                            
                            break;
                        case 3:
                            while (z<array.length-1 && array[z][0]!=0) {
                                z++;
                            }
                            array[z][0] = cod;
                            array[z][1] = yogurt.preciofinal()*cant;
                            break;
                        case 4:
                            while (z<array.length-1 && array[z][0]!=0) {
                                z++;
                            }
                            array[z][0] = cod;
                            array[z][1] = pan.preciofinal()*cant;
                            break;
                        case 5:
                            while (z<array.length-1 && array[z][0]!=0) {
                                z++;
                            }
                            array[z][0] = cod;
                            array[z][1] = huevos.preciofinal()*cant;
                            break;
                        case 6:
                            while (z<array.length-1 && array[z][0]!=0) {
                                z++;
                            }
                            array[z][0] = cod;
                            array[z][1] = aceite.preciofinal()*cant;
                            break;
                        case 7:
                            while (z<array.length-1 && array[z][0]!=0) {
                                z++;
                            }
                            array[z][0] = cod;
                            array[z][1] = papelhigienico.preciofinal()*cant;
                            break;
                        case 8:
                            while (z<array.length-1 && array[z][0]!=0) {
                                z++;
                            }
                            array[z][0] = cod;
                            array[z][1] = limpiavitro.preciofinal()*cant;
                            break;
                        case 9:
                            while (z<array.length-1 && array[z][0]!=0) {
                                z++;
                            }
                            array[z][0] = cod;
                            array[z][1] = lejia.preciofinal()*cant;
                            break;
                        case 10:
                            while (z<array.length-1 && array[z][0]!=0) {
                                z++;
                            }
                            array[z][0] = cod;
                            array[z][1] = camiseta.preciofinal()*cant;
                            break;
                    }
                    break;
                    //Si el usuario pulsa 2, se le pedirá el código y se llamará al método que borra el producto
                case 2:
                    System.out.println("Introduzca el código del artículo");
                    cod = sc.nextInt();
                    cliente.borraproducto(cod);
                    z=0;
                    //Este while recorre el array buscando el código introducido
                    while (array[z][0] != cod && z<array.length-1) {
                        z++;
                    }
                    //Si el codigo coincide, se borran este y el precio del array
                    if (cod == array[z][0]) {
                        array[z][0]=0;
                        array[z][1]=0;
                    }
                    break;
                    //Si se pulsa 3, se pide un codigo y una cantidad para modificar
                case 3:
                    System.out.println("Introduzca el código del artículo a modificar");
                    cod = sc.nextInt();
                    System.out.println("Introduzca la nueva cantidad desea, en productos a granel introduzca los gramos");
                    cant = sc.nextInt();
                    //Se llama al método que modifica los productos del carrito
                    cliente.modificaproducto(cod, cant);
                    //Un while que busca el código en el array de precios
                    while (array[z][0] != cod && z<array.length-1) {
                        z++;
                    }
                    //Si el código coincide, el precio se establece en 0 y se vuelve a calcular
                    //con el mismo switch que en el caso 1
                    if (cod == array[z][0]) {
                        array[z][1] = 0;
                        z=0;
                        switch (cod) {
                            case 1:
                                while (z<array.length-1 && array[z][0]!=0) {
                                    z++;
                                }
                                array[z][0] = cod;
                                array[z][1] = (naranja.preciofinal()/1000)*cant;
                                break;
                            case 2:
                                while (z<array.length-1 && array[z][0]!=0) {
                                    z++;
                                }
                                array[z][0] = cod;
                                array[z][1] = (carne.preciofinal()/1000)*cant;
                                break;
                            case 3:
                                while (z<array.length-1 && array[z][0]!=0) {
                                    z++;
                                }
                                array[z][0] = cod;
                                array[z][1] = yogurt.preciofinal()*cant;
                                break;
                            case 4:
                                while (z<array.length-1 && array[z][0]!=0) {
                                    z++;
                                }
                                array[z][0] = cod;
                                array[z][1] = pan.preciofinal()*cant;
                                break;
                            case 5:
                                while (z<array.length-1 && array[z][0]!=0) {
                                    z++;
                                }
                                array[z][0] = cod;
                                array[z][1] = huevos.preciofinal()*cant;
                                break;
                            case 6:
                                while (z<array.length-1 && array[z][0]!=0) {
                                    z++;
                                }
                                array[z][0] = cod;
                                array[z][1] = aceite.preciofinal()*cant;
                                break;
                            case 7:
                                while (z<array.length-1 && array[z][0]!=0) {
                                    z++;
                                }
                                array[z][0] = cod;
                                array[z][1] = papelhigienico.preciofinal()*cant;
                                break;
                            case 8:
                                while (z<array.length-1 && array[z][0]!=0) {
                                    z++;
                                }
                                array[z][0] = cod;
                                array[z][1] = limpiavitro.preciofinal()*cant;
                                break;
                            case 9:
                                while (z<array.length-1 && array[z][0]!=0) {
                                    z++;
                                }
                                array[z][0] = cod;
                                array[z][1] = lejia.preciofinal()*cant;
                                break;
                            case 10:
                                while (z<array.length-1 && array[z][0]!=0) {
                                    z++;
                                }
                                array[z][0] = cod;
                                array[z][1] = camiseta.preciofinal()*cant;
                                break;
                        }
                    }
                    break;
                    //Si se pulsa 4, se muestra la lista de productos que se han añadido al carrito
                case 4:
                    System.out.println("Su carrito lleva los siguientes productos");
                    System.out.println("Código     Cantidad");
                    cliente.getproducto();
                    System.out.println("");
                    break;
                    //Si se pulsa 5, se vuelven a mostrar los productos disponibles
                case 5:
                    System.out.println("Actualmente tenemos los siguientes productos en el supermercado");
                    System.out.println("Código Nombre               Precio     Stock     Precio en oferta");
                    System.out.println(naranja.getcodigo() + "      " + naranja.getnombre() + "       "  + naranja.getprecio() + "€/kg " + naranja.getstock() + "g       " + naranja.ofertiña());
                    System.out.println(carne.getcodigo() + "      " + carne.getnombre() + "        " + carne.getprecio() + "€/kg " + carne.getstock() + "g       " + carne.ofertiña());
                    System.out.println(yogurt.getcodigo() + "      " + yogurt.getnombre() + "            " + yogurt.getprecio() + "€    " + yogurt.getstock() + "           " + yogurt.ofertiña() + "€");
                    System.out.println(pan.getcodigo() + "      " + pan.getnombre() + "             " + pan.getprecio() + "€    " + pan.getstock() + "           " + pan.ofertiña());
                    System.out.println(huevos.getcodigo() + "      " + huevos.getnombre() + "          " + huevos.getprecio() + "€    " + huevos.getstock() + "           " + huevos.ofertiña());
                    System.out.println(aceite.getcodigo() + "      " + aceite.getnombre() + "                  " + aceite.getprecio() + "€    " + aceite.getstock() + "           " + aceite.ofertiña() + "€");
                    System.out.println(papelhigienico.getcodigo() + "      " + papelhigienico.getnombre() + "              " + papelhigienico.getprecio() + "€    " + papelhigienico.getstock() + "           " + papelhigienico.ofertiña());
                    System.out.println(limpiavitro.getcodigo() + "      " + limpiavitro.getnombre() + "         " + limpiavitro.getprecio() + "€     " + limpiavitro.getstock() + "           " + limpiavitro.ofertiña());
                    System.out.println(lejia.getcodigo() + "      " + lejia.getnombre() + "      " + lejia.getprecio() + "€     " + lejia.getstock() + "           " + lejia.ofertiña());
                    System.out.println(camiseta.getcodigo() + "     " + camiseta.getnombre() + "                  " + camiseta.getprecio() + "€    " + camiseta.getstock() + "           " + camiseta.ofertiña());
                    break;
                    //Si se pulsa 6, se calcula el precio total sumando todos los precios registrados en el array
                case 6:
                    preciototal = 0;
                    for (double array1[] : array) {
                        preciototal+= array1[1];
                    }
                    //Se muestra el precio por pantalla
                    System.out.println("El precio total es " + preciototal + "€");
                    break;
            }
        }
        //Si se pulsa 7, el programa sale del bucle while y muestra por pantalla
        //los siguientes mensajes más el precio total
        System.out.println("Gracias por elegirnos, " + cliente.getnombre());
        System.out.println("El precio total es");
        System.out.println(preciototal + "€");
        System.out.println("Gracias por elegirnos, " + cliente.getnombre());
    }
}