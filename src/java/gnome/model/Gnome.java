/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Gnome implements GnomeDTO, Serializable {
    private static final long serialVersionUID = 981738912738371L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Integer quantity;
    
    public Gnome(){}
    
    public Gnome(String name, Integer id){
        this.id = name;
        this.quantity = id;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        if (!(object instanceof Gnome)) {
            return false;
        }
        Gnome other = (Gnome) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gnome.model.Gnome[ id=" + id + " ]";
    }
    
}
