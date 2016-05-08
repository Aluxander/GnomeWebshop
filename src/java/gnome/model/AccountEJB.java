/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.model;
import gnome.dto.AccountDTO;
import gnome.integration.entities.Account;
import gnome.integration.dao.GnomeshopDAO;
import gnome.utils.SessionUtil;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */
@Stateless
public class AccountEJB {
    @EJB
    GnomeshopDAO dao;
    
    public boolean login(String id, String password){
        AccountDTO account = dao.findAccount(id);
        if(account == null){
            return false;
        }
        if(account.getPassword().equals(password) && account.getBanned().equals(0)){
            SessionUtil.login(id);
            return true;
        }
        return false;
    }
    public boolean createAccount(String id, String password){
        
        AccountDTO account = new Account(id, password);
        AccountDTO checkAccount = dao.findAccount(id);
        if(checkAccount == null){
            dao.insertNewAccount(account);
            return true;
        }else{
            return false;
        }    
    }
    
    public void banAccount(String id){
        AccountDTO account = dao.findAccount(id);
        account.setBanned(1);
        dao.updateAccount(account);
        dao.refreshAllTables();
    }
    
    public void unbanAccount(String id){
        AccountDTO account = dao.findAccount(id);
        account.setBanned(0);
        dao.updateAccount(account);
        dao.refreshAllTables();
    }
    
}
