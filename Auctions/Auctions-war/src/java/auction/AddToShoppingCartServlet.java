/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.AuctionUser;
import auction.persistence.Item;
import auction.persistence.ItemManager;
import auction.persistence.ShoppingCartManager;
import auction.persistence.UserManager;
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
@WebServlet(name = "AddToShoppingCartServlet", urlPatterns = {"/addToShoppingCart"})
public class AddToShoppingCartServlet extends HttpServlet {

    AuctionUser user;
    
    @EJB
    UserManager userManager;
    
    @EJB
    ItemManager itemManager;
    
    @EJB
    ShoppingCartManager shoppingCartManager;
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user = LoginUtil.getAuthenticatedUser(request, userManager);
       if (user == null) {
           response.sendRedirect("login.jsp");
           return;
       }
       
       try {
        String itemId = request.getParameter("itemId");
        Long idItem = Long.parseLong(itemId);
         Item item = itemManager.findItemById(idItem);


        shoppingCartManager.addToShoppingCart(item, user);
        itemManager.changeStatus(item, Item.ORDERED);
       } catch(Exception exc) {
           System.out.println(exc);
       } finally {
           response.sendRedirect("allItemsForBidder");
       }
       
    }

    

}
