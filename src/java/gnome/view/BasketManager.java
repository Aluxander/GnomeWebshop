/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.view;

import gnome.controller.Controller;
import gnome.model.gnome.GnomeDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alex
 */

@Named("basket")
@ConversationScoped
public class BasketManager implements Serializable {

    private static final long serialVersionUID = 1337840851L;
    @EJB
    private Controller controller;
    @Inject
    private Conversation conversation;
    private List<GnomeDTO> basketList = new ArrayList();
    private Exception transactionFailure;
    private double totalPrice;
    private boolean success = true;
    

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }

    public boolean getSuccess() {
        return transactionFailure == null;
    }

    public Exception getException() {
        return transactionFailure;
    }

    private String jsf22Bugfix() {
        return "";
    }
    
    //Basket Methods ##############################################################################
    public String addBasket(String id) {
        try {
            startConversation();
            transactionFailure = null;
            success = controller.addBasket(id);

        } catch (Exception e) {

            handleException(e);
        }
        return jsf22Bugfix();
    }

    public String findBasket() {
        try {
            startConversation();
            transactionFailure = null;
            basketList = controller.findBasket();
            totalPrice = controller.totalPrice(basketList);

        } catch (Exception e) {

            handleException(e);
        }
        return jsf22Bugfix();

    }
    
    public String buyBasket() {
        try {
            startConversation();
            transactionFailure = null;
            controller.buyBasket(basketList);
            basketList.clear();
            return "gnome?faces-redirect=true";
            

        } catch (Exception e) {

            handleException(e);
        }
        return jsf22Bugfix();

    }
    
    public String checkout(){
        try {
            startConversation();
            transactionFailure = null;
            return "basket?faces-redirect=true";

        } catch (Exception e) {

            handleException(e);
        }
        return jsf22Bugfix();
    }
    
    //Getters and setters #########################################################################
    public List<GnomeDTO> getBasketList() {
        return basketList;
    }

    public void setBasketList(List<GnomeDTO> basketList) {
        this.basketList = basketList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
