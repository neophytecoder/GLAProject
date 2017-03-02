/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.stateless;

import auction.persistence.AuctionUser;
import auction.persistence.UserManager;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
@Stateless
public class LoginUtilBean implements LoginUtil {
    @EJB
    private UserManager userManager;
    
    @Override
    public AuctionUser getAuthenticatedUser(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("idUser");
            AuctionUser user = userManager.findUserById(userId);
            
            request.setAttribute("user", user);
            
            return user;
        } catch(Exception exc) {
            return null;
        }
    }
}
