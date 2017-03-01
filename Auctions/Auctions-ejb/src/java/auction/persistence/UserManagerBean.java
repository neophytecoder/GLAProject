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
        TypedQuery<AuctionUser> query = em.createQuery("select u from AuctionUser u where u.userName = ?1", AuctionUser.class);
        query.setParameter(1, username);
        try {
            return query.getSingleResult();
        } catch (Exception exc) {
            return null;
        }
    }

    @Override
    public AuctionUser findUserByUsernameAndPassword(String username, String password) {
        TypedQuery<AuctionUser> query = em.createQuery("select u from AuctionUser u where u.userName = ?1 and u.password= ?2", AuctionUser.class);
        query.setParameter(1, username);
        query.setParameter(2, password);
        try {
            return query.getSingleResult();
        } catch (Exception exc) {
            return null;
        }
    }

    @Override
    public AuctionUser findUserById(Long id) {
        return em.find(AuctionUser.class, id);
    }
    
    
}
