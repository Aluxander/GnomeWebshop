/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.controller;

import gnome.model.account.Account;
import gnome.model.account.AccountDTO;
import gnome.utils.SessionUtil;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * EJB Controller.
 * 
 * Controller that handles all the actions that the client decides to execute.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class AccountFacade {
    @PersistenceContext(unitName = "CurrencyPU")
    private EntityManager entityManager;
    
   public List<AccountDTO> findAccounts(){
        Query query = entityManager.createQuery("SELECT e FROM Account e");
        return (List<AccountDTO>) query.getResultList();
    }
    
    public boolean createAccount(String id, String password){
        
        AccountDTO account = new Account(id, password);
        AccountDTO check = entityManager.find(Account.class, id);
        if(check == null){
            entityManager.persist(account);
            return true;
        }else{
            return false;
        }    
    }
    
    public boolean login(String id, String password){
        AccountDTO found = entityManager.find(Account.class, id);
        
        if(found == null){
            return false;
        }
        
        if(found.getPassword().equals(password) && found.getBanned().equals(0)){
            SessionUtil.login(id);
            return true;
        }
        
        return false;
    }
    
    public void logout(){
        SessionUtil.logout();
    }
    
    public void banAccount(String id){
        Account currAcc = entityManager.find(Account.class, id);
        currAcc.setBanned(1);
        entityManager.merge(currAcc);
        entityManager.flush();
    }
    
    public void unbanAccount(String id){
        Account currAcc = entityManager.find(Account.class, id);
        currAcc.setBanned(0);
        entityManager.merge(currAcc);
        entityManager.flush();
    }
  
}
