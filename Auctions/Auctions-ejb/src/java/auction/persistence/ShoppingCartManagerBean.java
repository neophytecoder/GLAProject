/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.persistence;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
    
    @EJB
    private BidManager bidManager;
    
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

    @Override
    public List<Item> findAllItemsInMyShoppingCart(AuctionUser user) {
        ShoppingCart cart = user.getShoppingCart();
        if (cart == null || cart.getItems() == null) {
            return new ArrayList<>();
        }
        
        List<Item> returnedItems = new ArrayList<>();
        List<Item> items = cart.getItems();
        for (Item item : items) {
            Bid bid = bidManager.findHighestBid(item.getId());
            if (bid != null) 
                item.setHighestBid(bid.getBidValue());
            else 
                item.setHighestBid(item.getStartPrice());
            if (item.getState() == Item.ORDERED) {
                returnedItems.add(item);
            }
        }
        
        return returnedItems;
    }

    @Override
    public void cancelShoppingCart(Long itemId) {
        Item item = em.find(Item.class, itemId);
        if (item != null) {
            item.setShoppingCart(null);
            item.setState(Item.FOR_SALE);
            em.merge(item);
        }
        
    }

    @Override
    public Double calculateTotalOrder(List<Item> items) {
        Double price = 0.0;
        for (Item item: items) {
            price += item.getHighestBid();
        }
        return price;
    }

    @Override
    public Double calculateTotalShipping(List<Item> items) {
        Double price = 0.0;
        for (Item item: items) {
            if (!item.getFreeDelivery())
                price += 5;
        }
        return price;
    }

    @Override
    public void confirmOrder(AuctionUser user, String bankAccount, String address) {
        ShoppingCart cart = user.getShoppingCart();
        cart.setBankAccount(bankAccount);
        cart.setAddress(address);
        
        em.merge(cart);
        
        List<Item> items = cart.getItems();
        for (Item item: items) {
            item.setState(Item.DELIVERING);
            em.merge(item);
        }
    }

    @Override
    public PaymentHistory savePaymentHistory(PaymentHistory history) {
        em.persist(history);
        
        return history;
    }

    
    
}
