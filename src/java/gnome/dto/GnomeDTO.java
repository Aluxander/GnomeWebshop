/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.dto;

/**
 *
 * @author Alex
 */
public interface GnomeDTO {
    
    public String getId();
    public Integer getQuantity();
    public double getPrice();
    public void setQuantity(Integer quantity);
    
}
