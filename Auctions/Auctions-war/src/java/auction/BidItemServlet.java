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
import auction.stateless.LoginUtil;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "BidItemServlet", urlPatterns = {"/bidItem"})
public class BidItemServlet extends HttpServlet {
    
    @EJB
    private BidManager bidManager;
    
    @EJB
    private ItemManager itemManager;
    
    @EJB
    private UserManager userManager;
    
    @EJB
    private LoginUtil loginUtil;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuctionUser user = loginUtil.getAuthenticatedUser(req);
       if (user == null) {
           resp.sendRedirect("login.jsp");
           return;
       }
        
        String itemId = req.getParameter("itemId");
        Bid bid = null;
        Item item = null;
        try {
            Long id = Long.parseLong(itemId);
            bid = bidManager.findHighestBid(id);
            
            item = itemManager.findItemById(id);
        } catch (Exception exc) {
            
        }
        
        
        
        Double value = 0.0;
        if (bid == null || bid.getBidValue() == null || bid.getBidValue()==0) {
            value = item.getStartPrice();
        } else {
            value = bid.getBidValue();
        }
        
        
        req.setAttribute("item", item);
        req.setAttribute("bid", bid);
        req.setAttribute("bidValue", value);
        req.getRequestDispatcher("bidItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuctionUser user = loginUtil.getAuthenticatedUser(req);
       if (user == null) {
           resp.sendRedirect("login.jsp");
           return;
       }
        
        String itemIdStr = req.getParameter("itemId");
        String priceStr = req.getParameter("price");
        String username = user.getUserName();
        boolean result = false;
        try {
            Long itemId = Long.parseLong(itemIdStr);
            Double price = Double.parseDouble(priceStr);
            result = bidManager.bid(itemId, username, price);
        } catch (Exception exc) {
            System.out.println(exc);
        }
        
        if (result) {
            System.out.println("bidd");
            resp.sendRedirect("allItemsForUser");
        } else {
            System.out.println("not bid");
           
            resp.sendRedirect("bidItem?itemId="+itemIdStr);
        }
    }

    
    
}
