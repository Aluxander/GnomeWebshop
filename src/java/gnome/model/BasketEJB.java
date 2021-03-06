package gnome.model;

import gnome.dto.GnomeDTO;
import gnome.integration.entities.Gnome;
import gnome.integration.dao.GnomeshopDAO;
import gnome.utils.SessionUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */
@Stateless
public class BasketEJB {

    @EJB
    GnomeshopDAO dao;

    public boolean addBasket(String id) {
        GnomeDTO currGnome = dao.findGnome(id);
        Integer quant = currGnome.getQuantity();
        double price = currGnome.getPrice();
        return SessionUtil.addBasket(id, quant, price);
    }

    public List<GnomeDTO> findBasket() {
        return SessionUtil.findBasket();
    }

    //Buy the basket (deleting from DB)
    public void buyBasket(List<GnomeDTO> basketList) {
        String id;
        Integer quant;

        for (int i = 0; i < basketList.size(); i++) {
            id = basketList.get(i).getId();
            quant = basketList.get(i).getQuantity();
            GnomeDTO currGnome = dao.findGnome(id);
            currGnome.setQuantity(currGnome.getQuantity() - quant);
            dao.updateGnome(currGnome);
            dao.refreshAllTables();
        }
    }

    public static boolean checkBasket(String id, Integer quantity, double price, List<GnomeDTO> basketList) {
        boolean success = true;
        boolean check = true;
        for (int i = 0; i < basketList.size(); i++) {

            GnomeDTO addGnome = basketList.get(i);
            if (addGnome.getId().equals(id)) {
                int quant = addGnome.getQuantity();
                if (quantity > quant) {
                    addGnome.setQuantity(quant + 1);
                } else {
                    success = false;
                }
                check = false;
                break;
            }
        }
        if (check) {
            basketList.add(new Gnome(id, 1, price));
        }
        return success;
    }
    public double totalPrice(List<GnomeDTO> basketList){
        double totalPrice = 0;
        
        for (int i = 0; i < basketList.size(); i++) {
            Integer quantity = basketList.get(i).getQuantity();
            if(quantity > 1){
                totalPrice += basketList.get(i).getPrice() * quantity;
            } else {
                totalPrice += basketList.get(i).getPrice();
            }
        }
        return totalPrice;
    }
}
