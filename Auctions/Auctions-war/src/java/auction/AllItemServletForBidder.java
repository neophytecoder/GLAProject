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
import auction.stateless.LoginUtil;
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
@WebServlet(name = "AllItemServletForBidder", urlPatterns = {"/allItemsForBidder"})
public class AllItemServletForBidder extends HttpServlet {

    @EJB
    private UserManager userManager;

    @EJB
    private ItemManager itemManager;

    @EJB
    private LoginUtil loginUtil;

    AuctionUser user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        user = loginUtil.getAuthenticatedUser(request);
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (user != null) {
            List<Item> items = itemManager.findItemsByBidder(user.getId());
            request.setAttribute("items", items);
            request.setAttribute("user", user);
        } else {
            request.setAttribute("items", new ArrayList<Item>());
        }

        request.getRequestDispatcher("allItemsForBidder.jsp").forward(request, response);
    }

}
