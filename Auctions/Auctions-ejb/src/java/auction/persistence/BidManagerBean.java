/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.persistence;

import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.xml.registry.infomodel.User;

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
    public AuctionUser findWinner(Long itemId) {
        TypedQuery<Bid> query = em.createQuery("select d from Bid d join fetch d.item where d.item.id = ?1 ORDER BY d.bidValue DESC", Bid.class);
        query.setParameter(1, itemId);
        List<Bid> bids = query.getResultList();
        if (bids != null && bids.size() > 0) {
            return bids.get(0).getUser();
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

    @Override
    public Bid findMyHighestBid(Long itemId, Long userId) {
        TypedQuery<Bid> query = em.createQuery("select d from Bid d join fetch d.item where d.item.id = ?1 and d.user.id=?2 ORDER BY d.bidValue DESC", Bid.class);
        query.setParameter(1, itemId);
        query.setParameter(2, userId);
        List<Bid> bids = query.getResultList();
        if (bids != null && bids.size() > 0) {
            return bids.get(0);
        }
        return null;
    }
    @Override
    public void deleteAllBids(Long idItem, String username) {
        Query query = em.createQuery("delete from Bid b where b.user.userName=?1 and b.item.id=?2");
        query.setParameter(1, username);
        query.setParameter(2, idItem);
        query.executeUpdate();
        bidPenalty(idItem, username);
    }
    
    public void bidPenalty(Long idItem, String username) {
        Item item = itemManager.findItemById(idItem);
        AuctionUser user = userManager.findUser(username);
        if(item.getEndDate().before(Calendar.getInstance().getTime())) {
            user.setBidPenalty(user.getBidPenalty()+1);
            if(user.getBidPenalty() > 5) {
                Calendar today = Calendar.getInstance();
                today.add(Calendar.DATE, Bid.PENALTY_DURATION);
                user.setEndPenalty(today.getTime());
                user.setBidPenalty(0);
            }
            em.merge(user);
        }
    }
}
