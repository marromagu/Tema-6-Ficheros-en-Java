/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E01_Ficheros_Inmobiliaria;

import java.util.ArrayList;

/**
 *
 * @author JMRivera
 */
public class Inmobiliaria{
  private String nombre;
  private String cif;
  private String direccion;
  private String propietario;
  private ArrayList<Inmueble> inmuebles;
  private Cuenta cuenta;
  private int numeroInmuebles;
  
  public Inmobiliaria(String nombre, String cif, String direccion, String propietario, double saldo) {
    this.nombre = nombre;
    this.cif = cif;
    this.direccion = direccion;
    this.propietario = propietario;
    this.inmuebles = new ArrayList<Inmueble>();
    this.cuenta = new Cuenta(nombre, cif, 10, saldo);
    this.numeroInmuebles = 0;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    if(direccion != "")
      this.direccion = direccion;
  }

  public String getPropietario() {
    return propietario;
  }

  public void setPropietario(String propietario) {
    if(propietario != "")
      this.propietario = propietario;
  }

  public ArrayList<Inmueble> getInmuebles() {
    return inmuebles;
  }

  public void setInmuebles(ArrayList<Inmueble> inmuebles) {
    this.inmuebles = inmuebles;
  }

  public Cuenta getCuenta() {
    return cuenta;
  }

  public void setCuenta(Cuenta cuenta) {
    this.cuenta = cuenta;
  }

  public int getNumeroInmuebles() {
    return numeroInmuebles;
  }

  public void setNumeroInmuebles(int numeroInmuebles) {
    this.numeroInmuebles = numeroInmuebles;
  }

  public boolean altaInmueble(Inmueble nuevoInmueble){
    if (numeroInmuebles < inmuebles.size() && nuevoInmueble != null){
      inmuebles.set(numeroInmuebles, nuevoInmueble);
      numeroInmuebles++;
      return true;
    }
    return false;
  }
  
  public Inmueble devolverInmueble(int posicion){
    Inmueble miInmueble = null;
    if (posicion >= 0 && posicion < numeroInmuebles){
      inmuebles.set(posicion, inmuebles.get(posicion));
    }
    return miInmueble;
  }
  
  public boolean bajaInmueble(int posicion){
    if(devolverInmueble(posicion) != null){
      numeroInmuebles--;
      inmuebles.set(posicion, inmuebles.get(numeroInmuebles));
      inmuebles.set(numeroInmuebles, null);
      return true;
    }
    return false;
  }
  
  public boolean alquilar(int posicion){
    Inmueble miInmueble = devolverInmueble(posicion);
    if (miInmueble != null){
      double importe = miInmueble.precioAlquiler()+miInmueble.comision();
      cuenta.ingreso(importe);
      System.out.println("Ingresado: "+importe+" por el alquiler de:");
      miInmueble.verDatos();
      bajaInmueble(posicion);
      return true;
    }
    return false;
  }
  
  public Inmueble pedirDatos(){
    Inmueble miInmueble = null;
    int tipo;
    tipo = Entrada.leerEntero("Tipo\n 1.- Local \n 2.- Vivienda");
    if (tipo == 1 || tipo == 2){
      double superficie = Entrada.leerDouble("Superficie: ");
      String direccion  = Entrada.leerCadena("Dirección: ");
      double precio = Entrada.leerDouble("Precio: ");
      switch (tipo) {
        case 1 -> miInmueble = new Local(superficie, direccion, precio);
        case 2 -> {
            int tipoV = Entrada.leerEntero("Tipo\n 1.- Vivienda \n 2.- Ático");
            int numHabitaciones = Entrada.leerEntero("Número de habitaciones: ");
            int numBaños = Entrada.leerEntero("Número de baños: ");
            int plazasGaraje = Entrada.leerEntero("Número de plazas de garaje: ");
            int planta = Entrada.leerEntero("Planta: ");
            boolean ascensor = Entrada.leerBoolean("¿Tiene ascensor?");
            switch (tipoV) {
                case 1 -> miInmueble = new Vivienda(numHabitaciones, numBaños, plazasGaraje, ascensor, planta, superficie, direccion, precio);
                case 2 -> {
                    double terraza = Entrada.leerDouble("Supercie de la terraza");
                    miInmueble = new Atico(terraza, numHabitaciones, numBaños, plazasGaraje, ascensor, planta, superficie, direccion, precio);
              }
            } }
      }
    }
    return miInmueble;
  }

  public void verDatos(){
    System.out.println("Inmobiliaria "+nombre);
    System.out.println("CIF: "+cif);
    System.out.println("Dirección: "+direccion);
    System.out.println("Propietario: "+propietario);
    System.out.println("Número de inmuebles: "+numeroInmuebles);
    System.out.println("Saldo en cuenta: "+cuenta.getSaldo());
  }
  
  public void verDatosInmuebles(){
    System.out.println("Listado de inmuebles:\n");
    for (int i = 0; i < numeroInmuebles ;i++ ){
      inmuebles.get(i).verDatos();
      System.out.println("");  
    }
  }
  
}
