/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.mdbs;

import auction.persistence.PaymentHistory;
import auction.persistence.ShoppingCartManager;
import auction.persistence.UserManager;
import bank.requests.BillingRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author techron
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "ResultsQueue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BillingResultsBean implements MessageListener {
    
    @EJB
    private UserManager userManager;
    
    @EJB
    ShoppingCartManager shoppingCartManager;
    
    @PersistenceContext(unitName = "Auctions-ejbPU")
    EntityManager em;
    
    public BillingResultsBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        try {
            BillingRequest bill = message.getBody(BillingRequest.class);
            
            PaymentHistory history = em.find(PaymentHistory.class, bill.getId());
            history.setResult(bill.getResult());
            em.merge(history);
            
            System.out.println("Answer from Bank Received!  Transaction for "+ bill.getAccountNumber() + " is " 
                                                                                                + bill.getResult());
        } catch (JMSException ex) {
            Logger.getLogger(BillingResultsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
