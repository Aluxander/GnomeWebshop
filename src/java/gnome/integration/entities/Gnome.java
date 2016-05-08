package gnome.integration.entities;

import gnome.dto.GnomeDTO;
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
    private double price;
    
    public Gnome(){}
    
    public Gnome(String name, Integer id, double price){
        this.id = name;
        this.quantity = id;
        this.price = price;
    }
    
    /**
     * Getter and setters!
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "gnome.model.Gnome[ id=" + id + " ]";
    }
    
}
