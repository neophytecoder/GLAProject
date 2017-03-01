/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction;

import auction.persistence.ItemManager;
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
@WebServlet(name = "CancelItemServlet", urlPatterns = {"/cancelItem"})
public class CancelItemServlet extends HttpServlet {
    @EJB
    ItemManager itemManager;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        if (itemId != null && itemId.length() > 0) {
            try {
                Long id = Long.parseLong(itemId);
                itemManager.deleteItem(id);
            } catch(Exception exc) {
                
            }
            
        }
        
        resp.sendRedirect("allItems?username="+req.getParameter("username"));
    }

    
}
