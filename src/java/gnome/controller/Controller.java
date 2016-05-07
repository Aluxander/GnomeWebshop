/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.controller;

import gnome.dao.GnomeshopDAO;
import gnome.model.AccountDTO;
import gnome.model.AccountHandler;
import gnome.model.GnomeDTO;
import gnome.model.GnomeHandler;
import gnome.utils.SessionUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 * EJB Controller.
 * 
 * Controller that handles all the actions that the client decides to execute.
 */
@Stateless
public class Controller {
    @EJB
    GnomeshopDAO dao;
    @EJB
    AccountHandler ah;
    @EJB
    GnomeHandler gh;
 
    //Calls regarding Accounts #####################################################################
    public boolean login(String id, String password){
        return ah.login(id, password);
    }
    
    public void logout(){
        SessionUtil.logout();
    }
    
    public boolean createAccount(String id, String password){
        return ah.createAccount(id, password);
    }
    
    public void banAccount(String id){
        ah.banAccount(id);
    }
    
    public void unbanAccount(String id){
        ah.unbanAccount(id);
    }
    public List<AccountDTO> findAccounts(){
        return dao.findAccounts();
    }
    
    //Calls regarding Gnomes #####################################################################
    public boolean createGnome(String gnomeID, Integer quantity, double price){
        return gh.createGnome(gnomeID, quantity, price);
    }
    
    public void addExistingGnome(String gnomeID, Integer quantity){
        gh.addExistingGnome(gnomeID, quantity);
    }
    
    public void removeGnome(String gnomeID, Integer quantity){
        gh.removeGnome(gnomeID, quantity);
    }
    
    public List<GnomeDTO> findGnomes(){
        return dao.findGnomes();
    }
}
