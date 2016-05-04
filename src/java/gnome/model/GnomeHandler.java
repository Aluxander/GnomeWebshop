/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.model;

/**
 *
 * @author Alex
 */
public class GnomeHandler {
    
    public static boolean vaildQuantity(Gnome currGnome, Integer quantity){
        Integer currQuant = currGnome.getQuantity();
        currQuant = currQuant - quantity;
        
        if(currQuant >= 0){
            currGnome.setQuantity(currQuant);
            return true;
        }else{
            return false;
        }
    }
}
