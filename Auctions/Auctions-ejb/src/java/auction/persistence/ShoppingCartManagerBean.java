/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class ShoppingCartManagerBean implements ShoppingCartManager {
    @PersistenceContext(unitName = "Auctions-ejbPU")
    EntityManager em;
    
    
    
    @Override
    public void addToShoppingCart(Item item, AuctionUser user) {
        ShoppingCart cart = user.getShoppingCart();
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(user);
            em.persist(cart);
        }
        
        item.setShoppingCart(cart);
        em.merge(item);
    }
    
}
