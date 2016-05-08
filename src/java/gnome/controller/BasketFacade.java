/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.controller;

import gnome.integration.entities.Gnome;
import gnome.dto.GnomeDTO;
import gnome.utils.SessionUtil;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alex
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class BasketFacade {
    @PersistenceContext(unitName = "CurrencyPU")
    private EntityManager entityManager;

    public boolean addBasket(String id){
        GnomeDTO currGnome = entityManager.find(Gnome.class, id);
        Integer quant = currGnome.getQuantity();
        double price = currGnome.getPrice();
        return SessionUtil.addBasket(id, quant, price);
    }
    
    public List<GnomeDTO> findBasket(){
        return SessionUtil.findBasket();
    }
    
    public void buyBasket(List<GnomeDTO> basketList){
        String id;
        Integer quant;
        
        for(int i=0; i < basketList.size(); i++){
            id = basketList.get(i).getId();
            quant = basketList.get(i).getQuantity();
            Gnome currGnome = entityManager.find(Gnome.class, id);
            currGnome.setQuantity(currGnome.getQuantity() - quant);
            entityManager.merge(currGnome);
            entityManager.flush();    
        }
    }
    
}
