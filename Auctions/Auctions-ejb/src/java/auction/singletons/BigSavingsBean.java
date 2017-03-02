/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.singletons;

import auction.persistence.Item;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author techron
 */
@Singleton
@Startup
public class BigSavingsBean implements BigSavings {
    
    @PersistenceContext(name = "Auctions-ejbPU")
    private EntityManager em;
    
    Random rnd = new Random();

    @Schedule(second = "0", minute = "*", hour = "*",
            dayOfMonth = "*", month = "*", year = "*")
    @Override
    public void startSchedule() {
        TypedQuery<Item> queryGoodAllItem = em.createQuery("select i from Item i where i.endDate >= ?1 and i.state=1",
                                                                                            Item.class);
        queryGoodAllItem.setParameter(1, new Date());
        List<Item> allItemsList = queryGoodAllItem.getResultList();
        int bidAmount = allItemsList.size();
        int savingAmount = (int)(bidAmount*0.1);
        if (savingAmount==0) {
            savingAmount++;
        }
        System.out.println("Size of items is: " + bidAmount);
        System.out.println("Savings amount: " + savingAmount);
        System.out.println("******************");

        for (int i = 0; i < savingAmount; i++) {
        int rndItem = rnd.nextInt(bidAmount);
        System.out.println("Random Item: " + rndItem);
        allItemsList.get(rndItem).setFreeDelivery(Boolean.TRUE);
        em.merge(allItemsList.get(rndItem)); 
        }
    }
    

}
