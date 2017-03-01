/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.AuctionUser;
import auction.persistence.UserManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
public class LoginUtil {
    public static AuctionUser getAuthenticatedUser(HttpServletRequest request, UserManager userManager) {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("idUser");
            AuctionUser user = userManager.findUserById(userId);
            
            return user;
        } catch(Exception exc) {
            return null;
        }
    }
}
