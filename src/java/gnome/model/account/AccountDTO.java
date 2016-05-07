/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnome.model.account;

/**
 *
 * @author Alex
 */
public interface AccountDTO {
    String getId();
    String getPassword();
    Integer getBanned();
    void setBanned(Integer banned);
}   
