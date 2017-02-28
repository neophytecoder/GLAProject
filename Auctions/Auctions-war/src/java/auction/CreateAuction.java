/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.ItemManager;
import auction.persistence.UserManager;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import auction.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
/**
 *
 * @author root
 */
@RequestScoped
@ManagedBean
public class CreateAuction  {
    private String name;
    private String description;
    private Double startPrice;
    private Integer duration; // days
    private String username;
    private String[] category;
    private List<Category> categories;
    
    @EJB
    private UserManager userManager;
    
    @EJB
    private ItemManager itemManager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public List<Category> getCategories() {
       return itemManager.getCategories();
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }
    
    public void createAuction() {
        AuctionUser user =  userManager.findUser(username);
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setStartPrice(startPrice);
        item.setUser(user);
        
        categories = new ArrayList<>();
        for(String idCat : category) {
            categories.add(itemManager.findCategory(idCat));
        }
        System.out.println(categories.toString());
        item.setCategories(categories);
        item.setDuration(duration);
        
        Date startDate = Calendar.getInstance().getTime();
        item.setStartDate(startDate);
        
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, duration);
        item.setEndDate(c.getTime());
        item.setState(Item.FOR_SALE);
        itemManager.createAuction(item);
        
    }
    
   
}
