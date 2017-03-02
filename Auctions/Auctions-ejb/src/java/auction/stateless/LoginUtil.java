/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.stateless;

import auction.persistence.AuctionUser;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author root
 */
@Local
public interface LoginUtil {
    
    AuctionUser getAuthenticatedUser(HttpServletRequest request);
}
