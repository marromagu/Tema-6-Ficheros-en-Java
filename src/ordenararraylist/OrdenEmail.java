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
public class OrdenEmail implements Comparator<Persona>{

  @Override
  public int compare(Persona o1, Persona o2) {
    return o1.getEmail().compareTo(o2.getEmail());
  }
  
}
