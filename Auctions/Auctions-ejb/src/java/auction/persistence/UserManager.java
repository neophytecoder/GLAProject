/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.persistence;

import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface UserManager {

    AuctionUser findUser(String username);

    AuctionUser findUserByUsernameAndPassword(String username, String password);

    AuctionUser findUserById(Long id);
    
}
