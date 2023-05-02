/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package E02_Inmobiliaria_DTD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author JMRivera
 */
public class GestionarInmobiliaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Inmobiliaria miInmobiliaria = new Inmobiliaria("Velázquez", "41-123456", "Feria 100", "Mario Romero", 1000.0);
        int opcion, modificar, posicion;
        Inmueble unInmueble;
        do {
            opcion = menu();
            switch (opcion) {
                case 1:
                    miInmobiliaria.verDatos();
                    modificar = Entrada.leerCaracter("¿Quiere modificar los datos (s/n)?:");
                    if (modificar == 's') {
                        miInmobiliaria.setPropietario(Entrada.leerCadena("Nuevo propietario:"));
                        miInmobiliaria.setDireccion(Entrada.leerCadena("Nuevo dirección:"));
                    }
                    break;
                case 2:
                    unInmueble = miInmobiliaria.pedirDatos();
                    miInmobiliaria.altaInmueble(unInmueble);
                    break;
                case 3:
                    posicion = Entrada.leerEntero("Posición del inmueble a dar de baja");
                    if (miInmobiliaria.bajaInmueble(posicion)) {
                        System.out.println("Inmueble dado de baja.");
                    } else {
                        System.out.println("Error: dando de baja el inmueble: " + posicion);
                    }
                    break;
                case 4:
                    posicion = Entrada.leerEntero("Posición del inmueble a alquilar");
                    if (!miInmobiliaria.alquilar(posicion)) {
                        System.out.println("Error: alquilando el inmueble: " + posicion);
                    }
                    break;
                case 5:
                    posicion = Entrada.leerEntero("Posición del inmueble");
                    unInmueble = miInmobiliaria.devolverInmueble(posicion);
                    if (unInmueble != null) {
                        unInmueble.verDatos();
                    } else {
                        System.out.println("Error: mostrando datos del inmueble: " + posicion);
                    }
                    break;
                case 6:
                    miInmobiliaria.verDatosInmuebles();
                    break;
                case 7:
                    guardarFichero(miInmobiliaria);
                    break;
                case 8:
                    leerFichero();
                    break;
                case 9:
                    miInmobiliaria.GenerarObjetos();
                    break;
                default:
            }
            Entrada.leerCadena("Pulse intro para continuar...");
        } while (opcion != 0);
        System.out.println("Fin de la ejecución.");
    }

    public static void guardarFichero(Inmobiliaria miInmobiliaria) throws IOException {
        FileOutputStream fichero = null;
        ObjectOutputStream salida = null;
        try {
            fichero = new FileOutputStream("Inmobiliaria.txt");
            salida = new ObjectOutputStream(fichero);
            salida.writeObject(miInmobiliaria);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fichero.close();
                salida.close();
            } catch (IOException e) {
            }
        }
    }

    public static Inmobiliaria leerFichero() {
        FileInputStream fichero = null;
        ObjectInputStream entrada = null;
        Inmobiliaria miInmobiliaria = null;
        try {
            fichero = new FileInputStream("Inmobiliaria.dat");
            entrada = new ObjectInputStream(fichero);
            miInmobiliaria = (Inmobiliaria) entrada.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
            }
        }
        return miInmobiliaria;
    }

    public static int menu() {
        int op;
        System.out.println("Menu");
        System.out.println("");
        System.out.println("1.- Ver/modificar los datos de la inmobiliaria.");
        System.out.println("2.- Dar de alta un inmueble.");
        System.out.println("3.- Dar de baja un inmueble.");
        System.out.println("4.- Alquilar un inmueble.");
        System.out.println("5.- Ver los datos de un inmueble.");
        System.out.println("6.- Ver los datos de todos los inmuebles.");
        System.out.println("0.- Salir.");
        op = Entrada.leerEntero("\nElija opción:");

        return op;
    }
    

}
