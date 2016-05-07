/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.model;

import gnome.dao.GnomeshopDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */

@Stateless
public class GnomeHandler {
    
    @EJB
    GnomeshopDAO dao;
    
    public boolean createGnome(String gnomeID, Integer quantity, double price){
        GnomeDTO gnome = new Gnome(gnomeID, quantity, price);
        GnomeDTO checkGnome = dao.findGnome(gnomeID);
        if(checkGnome == null){
            dao.updateGnome(gnome);
            return true;
        }else{
            return false;
        }
    }
    public void addExistingGnome(String gnomeID, Integer quantity){
        Integer newQuant;
        GnomeDTO currGnome = dao.findGnome(gnomeID);
        newQuant = currGnome.getQuantity();
        newQuant = quantity+newQuant;
        currGnome.setQuantity(newQuant);
        dao.updateGnome(currGnome);
        dao.refreshAllTables();
    }
    
    public void removeGnome(String gnomeID, Integer quantity){
        GnomeDTO currGnome = dao.findGnome(gnomeID);
        if(GnomeHandler.vaildQuantity(currGnome, quantity)){
            dao.updateGnome(currGnome);
            dao.refreshAllTables();
        }   
    }
    
   
    public static boolean vaildQuantity(GnomeDTO currGnome, Integer quantity){
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
