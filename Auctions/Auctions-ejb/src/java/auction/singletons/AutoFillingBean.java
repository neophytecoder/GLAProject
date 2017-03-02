/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.singletons;

import auction.persistence.AuctionUser;
import auction.persistence.Bid;
import auction.persistence.Category;
import auction.persistence.Item;
import auction.persistence.ItemManager;
import auction.persistence.UserManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.Conversation;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author techron
 */
@Singleton
@Startup
public class AutoFillingBean implements AutoFilling {

    @PersistenceContext(name = "Auctions-ejbPU")
    private EntityManager em;

    @EJB
    private UserManager userManager;

    @EJB
    private ItemManager itemManager;

    @PostConstruct
    @Override
    public void initDBFilling() {

        TypedQuery<AuctionUser> queryUser = em.createQuery("select u from AuctionUser u", AuctionUser.class);
        List<AuctionUser> rs1 = queryUser.getResultList();
        if (rs1.isEmpty()) {
//Adding 10 users    
            for (int i = 1; i < 11; i++) {
                em.persist(new AuctionUser(new Long(i), "user" + i, "user" + i, "user Name-" + i, "Address of user-" + i, "FR000" + i));
            }
        }

        TypedQuery<Category> queryCategory = em.createQuery("select c from Category c", Category.class);
        List<Category> rs2 = queryCategory.getResultList();
        if (rs2.isEmpty()) {
////Adding 10 catrgories
            em.persist(new Category(1L, "Motors"));
            em.persist(new Category(2L, "Fashion"));
            em.persist(new Category(3L, "Electronics"));
            em.persist(new Category(4L, "Toys"));
            em.persist(new Category(5L, "Music"));
            em.persist(new Category(6L, "Art"));
            em.persist(new Category(7L, "Home"));
            em.persist(new Category(8L, "Handmade"));
            em.persist(new Category(9L, "Guns"));
            em.persist(new Category(10L, "Sport"));
        }

        TypedQuery<Item> queryItem = em.createQuery("select i from Item i", Item.class);
        List<Item> rs3 = queryItem.getResultList();
        if (rs3.isEmpty()) {

            List<Category> categories = new ArrayList<>();
            int days_tmp;
            Calendar c;
            Item item;
// Item - 1
            categories.clear();
            categories.add(em.find(Category.class, 1L));
            categories.add(em.find(Category.class, 10L));
            c = Calendar.getInstance();
            days_tmp = 9;
            c.add(Calendar.DATE, days_tmp);
            item = new Item(1L, "Mitsubishi Exlipse", "Car like from fast and furious", 9000.0, days_tmp, Calendar.getInstance().getTime(),
                    c.getTime(), Item.FOR_SALE, userManager.findUser("user1"), categories);
            em.persist(item);

            for (Category category : categories) {
                System.out.println(category.getName());
                category.addItem(item);
                em.merge(category);
            }

// Item - 2
            categories.clear();
            categories.add(em.find(Category.class, 7L));
            c = Calendar.getInstance();
            days_tmp = 7;
            c.add(Calendar.DATE, days_tmp);
            item = new Item(2L, "Rocking chair", "New folding rocing chair", 54.99, days_tmp, Calendar.getInstance().getTime(),
                    c.getTime(), Item.FOR_SALE, userManager.findUser("user9"), categories);
            em.persist(item);

            for (Category category : categories) {
                System.out.println(category.getName());
                category.addItem(item);
                em.merge(category);
            }

            // Item - 3
            categories.clear();
            categories.add(em.find(Category.class, 3L));
            categories.add(em.find(Category.class, 10L));
            c = Calendar.getInstance();
            days_tmp = 1;
            c.add(Calendar.DATE, days_tmp);
            item = new Item(3L, "Diver watch dv12", "Invicta Mens Pro Diver watch", 73.0, days_tmp, Calendar.getInstance().getTime(),
                    c.getTime(), Item.FOR_SALE, userManager.findUser("user2"), categories);
            em.persist(item);

            for (Category category : categories) {
                System.out.println(category.getName());
                category.addItem(item);
                em.merge(category);
            }

// Item - 4
            categories.clear();
            categories.add(em.find(Category.class, 1L));
            categories.add(em.find(Category.class, 4L));
            c = Calendar.getInstance();
            days_tmp = 10;
            c.add(Calendar.DATE, days_tmp);
            item = new Item(4L, "Yamaha YZF", "2015 Yamaha YZF", 3499.0, days_tmp, Calendar.getInstance().getTime(),
                    c.getTime(), Item.FOR_SALE, userManager.findUser("user3"), categories);
            em.persist(item);

            for (Category category : categories) {
                System.out.println(category.getName());
                category.addItem(item);
                em.merge(category);
            }

// Item - 5
            categories.clear();
            categories.add(em.find(Category.class, 6L));
            categories.add(em.find(Category.class, 7L));
            c = Calendar.getInstance();
            days_tmp = 4;
            c.add(Calendar.DATE, days_tmp);
            item = new Item(5L, "China Dollar", "China Dollar, 3 Year (1914)", 73.0, days_tmp, Calendar.getInstance().getTime(),
                    c.getTime(), Item.FOR_SALE, userManager.findUser("user6"), categories);
            em.persist(item);

            for (Category category : categories) {
                System.out.println(category.getName());
                category.addItem(item);
                em.merge(category);
            }

        }

        TypedQuery<Bid> queryBid = em.createQuery("select c from Bid c", Bid.class);
        List<Bid> rs4 = queryBid.getResultList();
        if (rs4.isEmpty()) {
//Bid-1            
            Item itemForBid;
            AuctionUser userForBid;
            for (int i = 1; i < 5; i++) {
                Bid bid = new Bid();
                itemForBid = itemManager.findItemById(new Long(i));
                userForBid = userManager.findUser("user" + i);

                bid.setItem(itemForBid);
                bid.setUser(userForBid);
                bid.setBidValue(itemForBid.getStartPrice() + i * 100);
                em.persist(bid);
            }
        }
    }
}
