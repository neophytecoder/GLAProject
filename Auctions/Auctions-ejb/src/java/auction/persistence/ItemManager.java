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
public interface ItemManager {

    void createAuction(Item Item);

    List<Category> getCategories();

    Category findCategory(String id);

    List<Item> findItemsByUser(AuctionUser user);

    void deleteItem(Long id);

    List<Item> findAllActiveItems();

    Item findItemById(Long id);
    
    List<Item> findItemByName(String name);

}
