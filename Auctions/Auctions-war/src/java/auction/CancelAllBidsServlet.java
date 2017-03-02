/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.AuctionUser;
import auction.persistence.BidManager;
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
@WebServlet(name = "CancelAllBidsServlet", urlPatterns = {"/cancelBid"})
public class CancelAllBidsServlet extends HttpServlet {

    @EJB
    BidManager bidManager;

    @EJB
    UserManager userManager;

    @EJB
    LoginUtil loginUtil;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        AuctionUser user = loginUtil.getAuthenticatedUser(req);
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        String username = user.getUserName();

        if ((itemId != null && itemId.length() > 0) && (username != null && username.length() > 0)) {
            try {
                Long idItem = Long.parseLong(itemId);
                bidManager.deleteAllBids(idItem, username);
            } catch (Exception exc) {
                System.out.println(exc);
            }

        }

        resp.sendRedirect("allItemsForBidder?username=" + req.getParameter("username"));
    }

}
