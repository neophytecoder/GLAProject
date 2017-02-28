/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.AuctionUser;
import auction.persistence.Item;
import auction.persistence.ItemManager;
import auction.persistence.UserManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.xml.registry.infomodel.User;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class AllItems {
    String username;
    AuctionUser user;
    
    @EJB
    private UserManager userManager;
    
    @EJB
    private ItemManager itemManager;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void search() {
        System.out.println("I am called search");
        user = userManager.findUser(username);
    }
    
    public List<Item> getAllItemForUser() {
        if (user == null) {
            return new ArrayList<>();
        }
        return itemManager.findItemsByUser(user);
    }
    
    public void deleteItem() {
        System.out.println("I am called delete");
        //user = userManager.findUser(username);
    }
}
