/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.AuctionUser;
import auction.persistence.ShoppingCartManager;
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
 * @author Juan Karsten
 */
@WebServlet(name = "CancelItemInShoppingCart", urlPatterns = {"/cancelShoppingCart"})
public class CancelItemInShoppingCart extends HttpServlet {

    @EJB
    private LoginUtil loginUtil;
    
    @EJB
    ShoppingCartManager shoppingCartManager;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemIdStr = request.getParameter("itemId");
        AuctionUser user = loginUtil.getAuthenticatedUser(request);
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            Long itemId = Long.parseLong(itemIdStr);
            shoppingCartManager.cancelShoppingCart(itemId);
        } catch(Exception exc) {
            
        } finally {
            response.sendRedirect("myShoppingCart");
        }
        
    }
    
}
