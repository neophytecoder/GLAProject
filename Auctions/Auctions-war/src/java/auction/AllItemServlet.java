/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.AuctionUser;
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
@WebServlet(name = "AllItemServlet", urlPatterns = {"/allItems"})
public class AllItemServlet extends HttpServlet {

    @EJB
    private UserManager userManager;
    
    @EJB
    private ItemManager itemManager;
    
    AuctionUser user;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
       if (username != null && username.length()>0) {
           user = userManager.findUser(username);
           System.out.println(user);
       }
       
       if (user != null) {
           System.out.println("not null user");
           List<Item> items = itemManager.findItemsByUser(user);
           request.setAttribute("items", items);
       } else {
           request.setAttribute("items", new ArrayList<Item>());
       }
       
       
       
       request.getRequestDispatcher("allItemsForSeller.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
