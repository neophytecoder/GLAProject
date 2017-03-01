/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.stateful;

import auction.persistence.AuctionUser;
import auction.persistence.UserManager;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author root
 */
@Stateful
@LocalBean
public class LoginBean {
    private AuctionUser user;
    
    @EJB
    private UserManager userManager;
    
    @Remove
    public void logout() {
        System.out.println("logout");
        user = null;
    }

    public void login(String username, String password) {
        user = userManager.findUserByUsernameAndPassword(username, password);
        
    }

    public boolean isAuthenticated() {
        return (user != null);
    }

    public AuctionUser getUser() {
        return user;
    }
    
    
}
