/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.stateful;

import auction.persistence.Item;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author root
 */
@Stateful
public class ShoppingCartBean implements ShoppingCart {
    List<Item> orderedItem;

    @Override
    public void addToShoppingCart(Item item) {
        if (orderedItem  == null) {
            orderedItem = new ArrayList<>();
        }
        orderedItem.add(item);
    }
    
    

    @Override
    public void completeShopping() {
        
    }

    //@Override
    public List<Item> getCart() {
        return null;
    }
    
    
    
    
}
