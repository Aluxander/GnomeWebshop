package gnome.utils;
 

import gnome.model.BasketEJB;
import gnome.dto.GnomeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;


public class SessionUtil {
 
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
 
    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }
 
    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("userid");
        else
            return null;
    }
    
    public static void login(String user){
        List<GnomeDTO> basket = new ArrayList<>();
        HttpSession session = getSession();
        session.setAttribute("username", user);
        session.setAttribute("basket", basket);
    }
    
    public static void logout(){
        HttpSession session = getSession();
        session.invalidate();
    }
    
    public static boolean addBasket(String id, Integer quantity, double price){
        List<GnomeDTO> tempBasket = new ArrayList<>();         
        HttpSession session = getSession();
        tempBasket = (List<GnomeDTO>) session.getAttribute("basket");
        
        if(BasketEJB.checkBasket(id, quantity, price, tempBasket)){
            session.setAttribute("basket", tempBasket);
            return true;
        }else{
            return false;
        }
        
    }
    
    public static List<GnomeDTO> findBasket(){
        HttpSession session = getSession();
        return (List<GnomeDTO>) session.getAttribute("basket");
    }
}