package gnome.view;

import gnome.controller.AccountFacade;
import gnome.controller.Controller;
import gnome.model.AccountDTO;
import gnome.utils.SessionUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("currAcc")
@ConversationScoped
public class AccountManager implements Serializable {

    private static final long serialVersionUID = 1337800851L;
    @EJB
    private AccountFacade controller;
    @EJB
    private Controller controllerr;
    private AccountDTO currUser;
    private String displayUser;
    private boolean uniqueId = true;
    private boolean loggedIn = true;
    private boolean displayLogin = false;
    private String newId;
    private String newPassword;
    private String enterUser;
    private String enterPassword;
    private List<AccountDTO> accountList = new ArrayList();
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;

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

    public String findAccounts() {
        try {
            startConversation();
            transactionFailure = null;
            accountList = controller.findAccounts();

        } catch (Exception e) {

            handleException(e);
        }

        return jsf22Bugfix();

    }

    public String login() {
        try {
            startConversation();
            transactionFailure = null;
            loggedIn = controllerr.login(enterUser, enterPassword);
            uniqueId = true;

            if (loggedIn) {
                displayLogin = true;
                if ("admin".equals(SessionUtil.getUserName())) {
                    return "admin?faces-redirect=true";
                } else {
                    return "gnome?faces-redirect=true";
                }
            }
            setEnterUser("");
            setEnterPassword("");
        } catch (Exception e) {

            handleException(e);
        }

        return jsf22Bugfix();
    }

    public String logout() {
        controllerr.logout();
        displayLogin = false;
        return "index?faces-redirect=true";

    }

    public String createAccount() {
        try {
            startConversation();
            transactionFailure = null;
            uniqueId = controllerr.createAccount(newId, newPassword);
            setNewId("");
            setNewPassword("");

        } catch (Exception e) {
            handleException(e);
        }

        return jsf22Bugfix();
    }

    public String banAccount(String id) {
        try {
            startConversation();
            transactionFailure = null;
            controllerr.banAccount(id);
        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    public String unbanAccount(String id) {
        try {
            startConversation();
            transactionFailure = null;
            controllerr.unbanAccount(id);

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    public AccountDTO getCurrUser() {
        return currUser;
    }

    public void setCurrUser(AccountDTO currUser) {
        this.currUser = currUser;
    }

    public String getDisplayUser() {
        return displayUser;
    }

    public void setDisplayUser(String displayUser) {
        this.displayUser = displayUser;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEnterUser() {
        return enterUser;
    }

    public void setEnterUser(String enterUser) {
        this.enterUser = enterUser;
    }

    public String getEnterPassword() {
        return enterPassword;
    }

    public void setEnterPassword(String enterPassword) {
        this.enterPassword = enterPassword;
    }

    public boolean isUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(boolean uniqueId) {
        this.uniqueId = uniqueId;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public List<AccountDTO> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountDTO> accountList) {
        this.accountList = accountList;
    }

    public boolean isDisplayLogin() {
        return displayLogin;
    }

    public void setDisplayLogin(boolean displayLogin) {
        this.displayLogin = displayLogin;
    }

}
