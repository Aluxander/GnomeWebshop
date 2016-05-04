/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.view;

import gnome.controller.GnomeFacade;
import gnome.model.GnomeDTO;
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

@Named("gnome")
@ConversationScoped
public class GnomeManager implements Serializable {
    private static final long serialVersionUID = 1337880851L;
    @EJB
    private GnomeFacade controller;
    @Inject
    private Conversation conversation;
    private Exception transactionFailure;
    private String color;
    private Integer quantity;
    private boolean uniqueGnome = true;
    private boolean quant = true;
    private List<GnomeDTO> gnomeList = new ArrayList();

    
    private void startConversation(){
        if(conversation.isTransient()){
            conversation.begin();
        }
    }
    
    private void stopConversation(){
        if(!conversation.isTransient()){
            conversation.end();
        }
    }
    
    private void handleException(Exception e){
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }
    
    
    public boolean getSuccess(){
        return transactionFailure == null;
    }
    
    public Exception getException(){
        return transactionFailure;
    }
    
    private String jsf22Bugfix() {
        return "";
    }
    
    
    public String add(String color){
        try{
            startConversation();
            transactionFailure = null;
            controller.addGnome(color, 1);
            
        }catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
        
    }
    
    public String remove(String color){
        try{
            startConversation();
            transactionFailure = null;
            controller.removeGnome(color, 1);
            
        }catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
        
    }
    
    
    
    public String createGnome(){

        try{
            startConversation();
            transactionFailure = null;
            uniqueGnome = controller.createGnome(color, quantity);
            color = "";
            quantity = 0;
            
        } catch (Exception e) {
            handleException(e);
        }
        
        return jsf22Bugfix();
    }
    
    
    public String findGnomes(){
        
        try{
            startConversation();
            transactionFailure = null;
            gnomeList = controller.findGnomes();
            
        }catch (Exception e) {
            handleException(e);
        }
        
        return jsf22Bugfix();
    }

    public List<GnomeDTO> getGnomeList() {
        return gnomeList;
    }

    public void setGnomeList(List<GnomeDTO> gnomeList) {
        this.gnomeList = gnomeList;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isUniqueGnome() {
        return uniqueGnome;
    }

    public void setUniqueGnome(boolean uniqueGnome) {
        this.uniqueGnome = uniqueGnome;
    }

 

    
    
    
}
    