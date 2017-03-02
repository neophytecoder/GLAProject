/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.AuctionUser;
import auction.persistence.Category;
import auction.persistence.Item;
import auction.persistence.ItemManager;
import auction.persistence.UserManager;
import auction.stateless.LoginUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
@WebServlet(name = "CreateAuctionServlet", urlPatterns = {"/createAuction"})
public class CreateAuctionServlet extends HttpServlet {
    
    @EJB
    private UserManager userManager;
    
    @EJB
    private LoginUtil loginUtil;
    
    @EJB
    private ItemManager itemManager;
   
    
    private String name;
    private String description;
    private Double startPrice;
    private Integer duration; // days
    private String[] category;
    private List<Category> categories;
    
    private AuctionUser user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginUtil.getAuthenticatedUser(req);
        
        List<Category> categories = itemManager.getCategories();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("createAuction.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = loginUtil.getAuthenticatedUser(req);
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        
        try {
            name = req.getParameter("name");
            description = req.getParameter("description");
            startPrice = Double.parseDouble(req.getParameter("startPrice"));
            duration = Integer.parseInt(req.getParameter("duration"));
            category = req.getParameterValues("category");
            
            createAuction();
        } catch (Exception exc) {
            System.out.println(exc);
        }
        resp.sendRedirect("createAuction");
    }
    
    
    public void createAuction() {
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setStartPrice(startPrice);
        item.setUser(user);
        
        categories = new ArrayList<>();
        for(String idCat : category) {
            categories.add(itemManager.findCategory(idCat));
        }
        System.out.println(categories.toString());
        item.setCategories(categories);
        item.setDuration(duration);
        
        Date startDate = Calendar.getInstance().getTime();
        item.setStartDate(startDate);
        
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, duration);
        item.setEndDate(c.getTime());
        item.setState(Item.FOR_SALE);
        itemManager.createAuction(item);
        
    }
}
