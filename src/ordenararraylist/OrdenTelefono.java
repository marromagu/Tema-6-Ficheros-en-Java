/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordenararraylist;

import java.util.Comparator;

/**
 *
 * @author JMRivera
 */
public class OrdenTelefono implements Comparator<Persona>{

    @Override
    public int compare(Persona o1, Persona o2) {
        if (o1.getTelefono() == o2.getTelefono()){
            return 0;
        } else{
            if (o1.getTelefono() < o2.getTelefono()){
                return -1;
            }else{
                return 1;
            }
        }
    }


  
}
