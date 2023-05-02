/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ordenararraylist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author JMRivera
 */
public class OrdenarArrayList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Persona> agenda = leerFicheroT();
        //agenda = leerFicheroT();
        Persona p = null;
        int tlf, indice;
        int op;
        do {
            op = menu();
            switch (op) {
                case 1:
                    ver(agenda);
                    break;
                case 2:
                    p = new Persona();
                    agenda.add(p);
                    break;
                case 3:
                    tlf = Entrada.leerEntero("Telefono a borrar:");
                    p = new Persona("", "", tlf, "");
                    agenda.remove(p);
                    break;
                case 4:
                    tlf = Entrada.leerEntero("Telefono a buscar:");
                    p = new Persona("", "", tlf, "");
                    indice = agenda.indexOf(p);
                    p = agenda.get(indice);
                    System.out.println(p.toString());
                    break;
                case 5:
                    int orden = Entrada.leerEntero("\n 1.- Por telefonos.\n 2.- Por apellidos.\n 3.- Por email.\n ¿Que orden?");
                    switch (orden) {
                        case 1:
                            Collections.sort(agenda, new OrdenTelefono());
                            break;
                        case 2:
                            Collections.sort(agenda);
                            ver(agenda);
                            Comparator<Persona> comparador2 = Collections.reverseOrder();
                            Collections.sort(agenda, comparador2);
                            break;
                        case 3:
                            Collections.sort(agenda, new OrdenEmail().reversed());
                            ver(agenda);
                        /*                            Comparator<Persona> comparador3 = Collections.reverseOrder(new OrdenEmail());
                            Collections.sort(agenda, comparador3);
                            break;*/

                        default:
                    }
                    ver(agenda);
                    break;
                default:
            }
        } while (op != 0);
        guardarFichero(agenda);
        guardarFicheroT(agenda);
    }

    public static int menu() {
        int opcion;
        System.out.println("\n\tMENU");
        System.out.println("1.- Ver agenda");
        System.out.println("2.- Dar de alta");
        System.out.println("3.- Dar de baja");
        System.out.println("4.- Buscar persona");
        System.out.println("5.- Ordenar agenda");
        System.out.println("0.- Salir");
        opcion = Entrada.leerEntero("\nIntroduzca opcion:");
        return opcion;
    }

    public static void ver(ArrayList<Persona> agenda) {
        System.out.println("Telefono \tNombre  \t\temail");
        System.out.println("-------- \t------  \t\t-----");
        for (Persona p : agenda) {
            System.out.println(p.toString());
        }
    }

    public static ArrayList<Persona> leerFichero() {
        ArrayList<Persona> agenda = new ArrayList<Persona>();
        FileInputStream fichero = null;
        ObjectInputStream entrada = null;
        Persona p = null;
        try {
            fichero = new FileInputStream("Agenda.dat");
            entrada = new ObjectInputStream(fichero);
            p = (Persona) entrada.readObject();
            while (p != null) {
                agenda.add(p);
                p = (Persona) entrada.readObject();
            }
        } catch (Exception e) {
            System.out.println("Error abriendo|procesando fichero Agenda.dat.");
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
        return agenda;
    }

    public static void guardarFichero(ArrayList<Persona> agenda) {
        FileOutputStream fichero = null;
        ObjectOutputStream salida = null;
        try {
            fichero = new FileOutputStream("Agenda.dat");
            salida = new ObjectOutputStream(fichero);
            for (Persona p : agenda) {
                salida.writeObject(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fichero.close();
                salida.close();
            } catch (Exception e) {
            }
        }
    }

    public static int buscarPersona(int telefono, ArrayList<Persona> agenda) {
        for (int i = 0; i < agenda.size(); i++) {
            if (agenda.get(i).getTelefono() == telefono) {
                return i;
            }
        }
        return -1;
    }

    public static Persona buscarPersona2(int telefono, ArrayList<Persona> agenda) {
        for (Persona p : agenda) {
            if (p.getTelefono() == telefono) {
                return p;
            }
        }
        return null;
    }

    public static ArrayList<Persona> leerFicheroT() {
        ArrayList<Persona> agenda = new ArrayList<Persona>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("Agenda.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Atributo del objeto
            String nombre;
            String apellidos;
            int telefono;
            String email;
            // Lectura del fichero
            String linea;
            Persona nuevaP = null;
            while ((linea = br.readLine()) != null) {
                nombre = linea;
                linea = br.readLine();
                apellidos = linea;
                linea = br.readLine();
                telefono = Integer.parseInt(linea);
                linea = br.readLine();
                email = linea;
                nuevaP = new Persona(nombre, apellidos, telefono, email);
                agenda.add(nuevaP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return agenda;
    }
    
    
    public static void guardarFicheroT(ArrayList<Persona> agenda)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("Agenda.txt");
            pw = new PrintWriter(fichero);

            for (Persona p: agenda){
                pw.println(p.getNombre());
                pw.println(p.getApellidos());
                pw.println(p.getTelefono());
                pw.println(p.getEmail());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
}
