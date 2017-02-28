/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author root
 */
@Stateless
public class UserManagerBean implements UserManager {
    @PersistenceContext(name = "Auctions-ejbPU")
    private EntityManager em;
    
    @Override
    public AuctionUser findUser(String username) {
        TypedQuery<AuctionUser> query = em.createQuery("select u from User u where u.userName = ?1", AuctionUser.class);
        query.setParameter(1, username);
        return query.getSingleResult();
    }
}