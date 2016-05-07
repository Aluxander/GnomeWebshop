
package gnome.controller;

import gnome.model.Gnome;
import gnome.model.GnomeDTO;
import gnome.model.GnomeHandler;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class GnomeFacade {
    @PersistenceContext(unitName = "CurrencyPU")
    private EntityManager entityManager;
   
    public void addExistingGnome(String id, Integer quantity){
        Integer newQuant;
        Gnome currGnome = entityManager.find(Gnome.class, id);
        newQuant = currGnome.getQuantity();
        newQuant = quantity+newQuant;
        currGnome.setQuantity(newQuant);
        entityManager.merge(currGnome);
        entityManager.flush();
        
        
    }
    
    public void removeGnome(String id, Integer quantity){
        Gnome currGnome = entityManager.find(Gnome.class, id);
        
        if(GnomeHandler.vaildQuantity(currGnome, quantity)){
            entityManager.merge(currGnome);
            entityManager.flush();
        }   
        
        
        
    }
    
    public List<GnomeDTO> findGnomes(){
        Query query = entityManager.createQuery("SELECT e FROM Gnome e");
        return (List<GnomeDTO>) query.getResultList();
    }
   
    
    
    public boolean createGnome(String color, Integer quantity, double price){
        GnomeDTO gnome = new Gnome(color, quantity, price);
        GnomeDTO check = entityManager.find(Gnome.class, color);
        if(check == null){
            entityManager.persist(gnome);
            return true;
        }else{
            return false;
        }
    }
}
