package E01_Ficheros_Inmobiliaria;

import java.io.BufferedReader;
import java.io.FileReader;

public class codigoficherosInmobiliaria{
 Inmobiliaria miInmobiliaria = new Inmobiliaria("Velázquez", "89-123456", "Luis Montoto", "Mario", 1000.0);
    public void leerLocales() {
        try {
            double sup = 0, precio = 0;
            String dir = "";
            int i = 0;
            FileReader miFichero = new FileReader("Locales.txt");
            BufferedReader bf = new BufferedReader(miFichero);
            String linea = bf.readLine();
            while (linea != null) {
                switch (i) {
                    case 0:
                        sup = Double.parseDouble(linea);
                        i++;
                        break;
                    case 1:
                        dir = linea;
                        i++;
                        break;
                    case 2:
                        precio = Double.parseDouble(linea);
                        Local miLocal = new Local(sup, dir, precio);
                        miInmobiliaria.altaInmueble(miLocal);
                        i = 0;
                        break;
                    default:
                }
                linea = bf.readLine();
            }
            bf.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void leerViviendas() {
        try {
            double sup = 0, precio = 0;
            int numHabitaciones = 0, numBaños = 0, plazasGarajes = 0, planta = 0, i = 0;
            boolean ascensor = false;
            String dir = "";
            FileReader miFichero = new FileReader("Viviendas.txt");
            BufferedReader bf = new BufferedReader(miFichero);
            String linea = bf.readLine();
            while (linea != null) {
                switch (i) {
                    case 0:
                        sup = Double.parseDouble(linea);
                        i++;
                        break;
                    case 1:
                        dir = linea;
                        i++;
                        break;
                    case 2:
                        precio = Double.parseDouble(linea);
                        i++;
                        break;
                    case 3:
                        numHabitaciones = Integer.parseInt(linea);
                        i++;
                        break;
                    case 4:
                        numBaños = Integer.parseInt(linea);
                        i++;
                        break;
                    case 5:
                        plazasGarajes = Integer.parseInt(linea);
                        i++;
                        break;
                    case 6:
                        ascensor = false;
                        switch (linea.charAt(0)) {
                            case 's':
                            case 'S':
                                ascensor = true;
                        }
                        i++;
                        break;
                    case 7:
                        planta = Integer.parseInt(linea);
                        Inmueble miInmueble = new Vivienda(numHabitaciones, numBaños, plazasGarajes, ascensor, planta, sup, dir, precio);
                        miInmobiliaria.altaInmueble(miInmueble);
                        i = 0;
                        break;
                    default:
                }
                linea = bf.readLine();
            }
            bf.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void leerAticos() {
        try {
            double sup = 0, precio = 0, terraza = 0;
            int numHabitaciones = 0, numBaños = 0, plazasGarajes = 0, planta = 0, i = 0;
            boolean ascensor = false;
            String dir = "";
            FileReader miFichero = new FileReader("Aticos.txt");
            BufferedReader bf = new BufferedReader(miFichero);
            String linea = bf.readLine();
            while (linea != null) {
                switch (i) {
                    case 0:
                        sup = Double.parseDouble(linea);
                        i++;
                        break;
                    case 1:
                        dir = linea;
                        i++;
                        break;
                    case 2:
                        precio = Double.parseDouble(linea);
                        i++;
                        break;
                    case 3:
                        numHabitaciones = Integer.parseInt(linea);
                        i++;
                        break;
                    case 4:
                        numBaños = Integer.parseInt(linea);
                        i++;
                        break;
                    case 5:
                        plazasGarajes = Integer.parseInt(linea);
                        i++;
                        break;
                    case 6:
                        ascensor = false;
                        switch (linea.charAt(0)) {
                            case 's':
                            case 'S':
                                ascensor = true;
                        }
                        i++;
                        break;
                    case 7:
                        planta = Integer.parseInt(linea);
                        i++;
                        break;
                    case 8:
                        terraza = Double.parseDouble(linea);
                        Inmueble miInmueble = new Atico(terraza, numHabitaciones, numBaños, plazasGarajes, ascensor, planta, sup, dir, precio);
                        miInmobiliaria.altaInmueble(miInmueble);
                        i = 0;
                        break;
                    default:
                }
                linea = bf.readLine();
            }
            bf.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
