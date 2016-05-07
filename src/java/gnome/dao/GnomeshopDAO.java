/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.dao;

import gnome.model.account.Account;
import gnome.model.account.AccountDTO;
import gnome.model.gnome.Gnome;
import gnome.model.gnome.GnomeDTO;
import gnome.model.gnome.GnomeHandler;
import gnome.utils.SessionUtil;
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
public class GnomeshopDAO {

    @PersistenceContext(unitName = "CurrencyPU")
    private EntityManager entityManager;

    public void refreshAllTables() {
        entityManager.flush();
    }

    //Access Account table in DB ###################################################################
    public AccountDTO findAccount(String id) {
        return entityManager.find(Account.class, id);
    }

    public void insertNewAccount(AccountDTO account) {
        entityManager.persist(account);
    }

    public void updateAccount(AccountDTO account) {
        entityManager.merge(account);
    }

    public List<AccountDTO> findAccounts() {
        Query query = entityManager.createQuery("SELECT e FROM Account e");
        return (List<AccountDTO>) query.getResultList();
    }

    //Access Gnome table in DB ###################################################################
    public GnomeDTO findGnome(String id) {
        return entityManager.find(Gnome.class, id);
    }

    public void insertNewGnome(AccountDTO account) {
        entityManager.persist(account);
    }

    public void updateGnome(GnomeDTO gnome) {
        entityManager.merge(gnome);
    }

    public List<GnomeDTO> findGnomes() {
        Query query = entityManager.createQuery("SELECT e FROM Gnome e");
        return (List<GnomeDTO>) query.getResultList();
    }
}
