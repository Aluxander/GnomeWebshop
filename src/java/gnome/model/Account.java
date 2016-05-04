
package gnome.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Alex
 */
@Entity
public class Account implements AccountDTO, Serializable {
    private static final long serialVersionUID = 13372L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String password;
    private Integer banned;
    
    public Account(){}
    
    public Account(String id, String password){
        this.id = id;
        this.password = password;
        this.banned = 0;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Integer getBanned() {
        return banned;
    }

    @Override
    public void setBanned(Integer banned) {
        this.banned = banned;
    }


    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "currency.controller.Currency[ country=" + id + " ]";
    }
    
}
