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
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(item);
        cart.setUser(user);
        em.persist(cart);
    }
    
}
