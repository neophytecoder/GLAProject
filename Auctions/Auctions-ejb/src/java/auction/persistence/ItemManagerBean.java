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
import javax.persistence.TypedQuery;

/**
 *
 * @author root
 */
@Stateless
public class ItemManagerBean implements ItemManager {
    @PersistenceContext(name = "Auctions-ejbPU")
    private EntityManager em;
    
    @EJB
    BidManager bidManager;

    @Override
    public void createAuction(Item item) {
        em.persist(item);
        if (item.getCategories() == null) {
            return;
        }
         for (Category category: item.getCategories()) {
            category.addItem(item);
            em.merge(category);
        }
        
    }

    @Override
    public List<Category> getCategories() {
        TypedQuery<Category> query = em.createQuery("select c from Category c", Category.class);
        return query.getResultList();
    }

    @Override
    public Category findCategory(String id) {
        Long idCat = Long.parseLong(id);
        return em.find(Category.class, idCat);
    }

    @Override
    public List<Item> findItemsByUser(AuctionUser user) {
         TypedQuery<Item> query = em.createQuery("select i from Item i where i.user = ?1", Item.class);
         query.setParameter(1, user);
        List<Item> items = query.getResultList();
        setWinner(items);
        return items;
    }
    
    

    @Override
    public void deleteItem(Long id) {
        em.remove(em.find(Item.class, id));
    }

    @Override
    public List<Item> findAllActiveItems() {
         TypedQuery<Item> query = em.createQuery("select i from Item i where i.endDate >= ?1", Item.class);
         query.setParameter(1, Calendar.getInstance().getTime());
        return query.getResultList();
    }

    @Override
    public Item findItemById(Long id) {
        return em.find(Item.class, id);
    }
    
    @Override
    public List<Item> findItemByName(String name) {
        TypedQuery<Item> query = em.createQuery("select i from Item i where i.name like ?1 and i.endDate >= ?2", Item.class);
        query.setParameter(1, "%"+name+"%");
        query.setParameter(2, Calendar.getInstance().getTime());
        return query.getResultList();
    }
    
    @Override
    public List<Item> findItemByCategory(Long id) {
        TypedQuery<Item> query = em.createQuery("select i from Item i join fetch i.categories c where c.id = ?1 and i.endDate >= ?2", Item.class);
        query.setParameter(1, id);
        query.setParameter(2, Calendar.getInstance().getTime());
        return query.getResultList();
    }

    @Override
    public List<Item> findItemsByBidder(Long userId) {
        TypedQuery<Item> query = em.createQuery("select distinct(i) from Item i join fetch i.bids b where b.user.id=?1", Item.class);
        query.setParameter(1, userId);
        List<Item> items = query.getResultList();
        
        for (Item item:items) {
            Bid bid = bidManager.findHighestBid(item.getId());
            if (bid != null) 
                item.setHighestBid(bid.getBidValue());
            else 
                item.setHighestBid(item.getStartPrice());
            
            bid = bidManager.findMyHighestBid(item.getId(), userId);
            if (bid != null) 
                item.setMyHighestBid(bid.getBidValue());
            else 
                item.setMyHighestBid(0.0);
        }
        setWinner(items);
        return items;
    }

    @Override
    public void setWinner(List<Item> items) {
        for (Item item : items) {
            if (item.getEndDate().before(Calendar.getInstance().getTime())) {
                AuctionUser winner = bidManager.findWinner(item.getId());
                item.setWinner(winner);
                System.out.println("pick winner" + item.getName()+winner.getName());
            }
        }
    }
    
   
}
