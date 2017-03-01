/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.AuctionUser;
import auction.persistence.Bid;
import auction.persistence.BidManager;
import auction.persistence.Item;
import auction.persistence.ItemManager;
import auction.persistence.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
@WebServlet(name = "AllItemServletForUser", urlPatterns = {"/allItemsForUser"})
public class AllItemServletForUser extends HttpServlet {

    @EJB
    private UserManager userManager;
    
    @EJB
    private ItemManager itemManager;
    
    @EJB 
    private BidManager bidManager;
    
    AuctionUser user;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       List<Item> items = itemManager.findAllActiveItems();
       
       for (Item item: items) {
           Bid bid = bidManager.findHighestBid(item.getId());
           if (bid != null) {
               item.setHighestBid(bid.getBidValue());
           } else {
               item.setHighestBid(item.getStartPrice());
           }
       }
       
       request.setAttribute("items", items);
       
       request.getRequestDispatcher("allItemsForUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
