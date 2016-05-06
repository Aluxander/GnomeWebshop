package gnome.view;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("navigation")
@ConversationScoped
public class NavigationManager implements Serializable {

    private static final long serialVersionUID = 1337800851L;
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

    public String login() {
        try {
            startConversation();
            transactionFailure = null;
            return "login?faces-redirect=true";
        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }
}
