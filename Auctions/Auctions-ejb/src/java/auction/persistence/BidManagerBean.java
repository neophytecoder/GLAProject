/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.persistence;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author root
 */
@Stateless
public class BidManagerBean implements BidManager {
    @PersistenceContext(name = "Auctions-ejbPU")
    private EntityManager em;
    
    @EJB
    private ItemManager itemManager;
    
    @EJB
    private UserManager userManager;
    
    @Override
    public Bid findHighestBid(Long itemId) {
        TypedQuery<Bid> query = em.createQuery("select d from Bid d join fetch d.item where d.item.id = ?1 ORDER BY d.bidValue DESC", Bid.class);
        query.setParameter(1, itemId);
        List<Bid> bids = query.getResultList();
        if (bids != null && bids.size() > 0) {
            return bids.get(0);
        }
        return null;
    }

    @Override
    public boolean bid(Long itemId, String username, Double bidValue) {
        Bid bid = new Bid();
        
        Item item = itemManager.findItemById(itemId);
        AuctionUser user = userManager.findUser(username);
        
        bid.setItem(item);
        bid.setUser(user);
        bid.setBidValue(bidValue);
        
        Bid highestBid = findHighestBid(itemId);
        if ((highestBid == null && bidValue > item.getStartPrice()) || bidValue > highestBid.getBidValue()) {
            em.persist(bid);
            return true;
        }
        return false;
    }
    
    
}
