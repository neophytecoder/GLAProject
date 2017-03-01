/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.AuctionUser;
import auction.persistence.UserManager;
import auction.stateful.LoginBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    @EJB
    private UserManager userManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        System.out.println("post called");
        
        AuctionUser user = null;
        if (username != null && password != null)
            user = userManager.findUserByUsernameAndPassword(username, password);
        
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("idUser", user.getId());
            session.setMaxInactiveInterval(30*60*60);
            resp.sendRedirect("allItemsForUser");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }
    

}
