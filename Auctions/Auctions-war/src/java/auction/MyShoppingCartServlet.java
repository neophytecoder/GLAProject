/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.AuctionUser;
import auction.persistence.Item;
import auction.persistence.ShoppingCart;
import auction.persistence.ShoppingCartManager;
import auction.stateless.LoginUtil;
import auction.stateless.SendBillingRequest;
import bank.requests.BillingRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan Karsten
 */
@WebServlet(name = "MyShoppingCartServlet", urlPatterns = {"/myShoppingCart"})
public class MyShoppingCartServlet extends HttpServlet {

    @EJB
    private LoginUtil loginUtil;

    @EJB
    private ShoppingCartManager shoppingCartManager;
    
    @EJB
    private SendBillingRequest sendBillingRequest;

    AuctionUser user;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = loginUtil.getAuthenticatedUser(req);
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        List<Item> items = shoppingCartManager.findAllItemsInMyShoppingCart(user);
        
        String totalOrderStr = req.getParameter("totalOrder");
        Double totalOrder = Double.parseDouble(totalOrderStr);
        String address = req.getParameter("address");
        String bankAccount = req.getParameter("bankAccount");
        String pinCode = req.getParameter("pin");
        
        shoppingCartManager.confirmOrder(user, bankAccount, address);
        BillingRequest request = new BillingRequest(bankAccount, totalOrder, pinCode);
        System.out.println(request);
        sendBillingRequest.sendBillingRequest(request);
        
        resp.sendRedirect("myShoppingCart");
    }
    
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = loginUtil.getAuthenticatedUser(req);
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        List<Item> items = shoppingCartManager.findAllItemsInMyShoppingCart(user);
        req.setAttribute("items", items);
        
        Double shippingCost = shoppingCartManager.calculateTotalShipping(items);
        Double totalOrder = shoppingCartManager.calculateTotalOrder(items);
        req.setAttribute("shippingCost", shippingCost);
        req.setAttribute("totalOrder", totalOrder+shippingCost);
        
        req.setAttribute("address", user.getAddress());
        req.setAttribute("bankAccount", user.getBankAccount());
        
        req.getRequestDispatcher("myShoppingCart.jsp").forward(req, resp);
    }

}
