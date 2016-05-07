/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.model;

import java.util.List;

/**
 *
 * @author Alex
 */
public class BasketHandler {
    
    public static boolean addBasket(String id, Integer quantity, double price, List<GnomeDTO> tempBasket){
    
    boolean success = true;
    boolean check = true;
    for(int i = 0; i < tempBasket.size(); i++){
            
            GnomeDTO addGnome = tempBasket.get(i);
            if(addGnome.getId().equals(id)){
                
                int quant = addGnome.getQuantity();
                if(quantity > quant){
                    addGnome.setQuantity(quant + 1);
                }else{
                    success = false;
                }
                check = false;
                break;               
            }          
        }
        if(check){
            tempBasket.add(new Gnome(id, 1, price));
        }
        return success;
        
        }
}
