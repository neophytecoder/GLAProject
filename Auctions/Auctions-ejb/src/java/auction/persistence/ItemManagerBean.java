/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.persistence;

import java.util.List;
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
        return query.getResultList();
    }
    
    
    
}
