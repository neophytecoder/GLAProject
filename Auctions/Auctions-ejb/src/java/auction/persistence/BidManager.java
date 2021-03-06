/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.persistence;

import javax.ejb.Local;
import javax.xml.registry.infomodel.User;

/**
 *
 * @author root
 */
@Local
public interface BidManager {

    public Bid findHighestBid(Long itemId);

    public boolean bid(Long itemId, String username, Double bidValue);

    Bid findMyHighestBid(Long itemId, Long userId);
    
    void deleteAllBids(Long idItem, String username);
    
    AuctionUser findWinner(Long itemId);
}
