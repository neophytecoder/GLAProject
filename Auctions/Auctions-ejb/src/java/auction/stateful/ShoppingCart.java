/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.stateful;

import auction.persistence.Item;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface ShoppingCart {

    void addToShoppingCart(Item item);

    void completeShopping();

    //List<Item> getCart();

}
