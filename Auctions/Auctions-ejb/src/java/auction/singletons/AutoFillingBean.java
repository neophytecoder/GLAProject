/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.singletons;

import auction.persistence.AuctionUser;
import auction.persistence.Category;
import auction.persistence.Item;
import auction.persistence.ItemManager;
import auction.persistence.UserManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public void initDBFilling(){
//Adding 10 users    
        for (int i = 1; i < 11; i++) {
            em.persist(new AuctionUser("user" + i, "user" + i, "user Name-" + i, "Address of user-" + i, "FR000" + i));
        }

//Adding 10 catrgories
        em.persist(new Category("Motors"));
        em.persist(new Category("Fashion"));
        em.persist(new Category("Electronics"));
        em.persist(new Category("Toys"));
        em.persist(new Category("Music"));
        em.persist(new Category("Art"));
        em.persist(new Category("Home"));
        em.persist(new Category("Handmade"));
        em.persist(new Category("Guns"));
        em.persist(new Category("Sport"));
        
// Add 1 item
        AuctionUser user =  userManager.findUser("user8");
        Item item = new Item();
        System.out.println("persist: " + item.getName());

        item.setName("Harley Davidson8");
        item.setDescription("1972 Harley Davidson 8  ");
        item.setStartPrice(3150.0);
        item.setUser(user);
        
        List<Category> categories = new ArrayList<>();
        categories.add(em.find(Category.class, 15L));
        categories.add(em.find(Category.class, 16L));

        item.setCategories(categories);
        item.setDuration(3);
        
        Date startDate = Calendar.getInstance().getTime();
        item.setStartDate(startDate);
        
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 3);
        item.setEndDate(c.getTime());
        item.setState(Item.FOR_SALE);
        
//        em.persist(item);

        System.out.println("Merge "+item.getName());
        System.out.println(item.getCategories().get(1).getName().toString());

//        itemManager.createAuction(item);

//        em.persist(item);
        
// 
//         for (Category category: item.getCategories()) {
//             System.out.println(category.getName());
//            category.addItem(item);
//            em.merge(category);
//        }
         
       
    }
    
    
    
    
}
