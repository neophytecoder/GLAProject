/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.persistence;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface ShoppingCartManager {

    void addToShoppingCart(Item item, AuctionUser user);

    List<Item> findAllItemsInMyShoppingCart(AuctionUser user);

    void cancelShoppingCart(Long itemId);

    Double calculateTotalOrder(List<Item> items);

    Double calculateTotalShipping(List<Item> items);

    void confirmOrder(AuctionUser user, String bankAccount, String address);

    PaymentHistory savePaymentHistory(PaymentHistory history);

    
}
