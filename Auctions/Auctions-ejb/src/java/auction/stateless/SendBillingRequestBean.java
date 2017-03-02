/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.stateless;

import auction.persistence.PaymentHistory;
import auction.persistence.ShoppingCartManager;
import auction.persistence.UserManager;
import bank.requests.BillingRequest;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;

/**
 *
 * @author techron
 */
@Stateless
public class SendBillingRequestBean implements SendBillingRequest {

    @Inject
    JMSContext context;
    
    @EJB
    UserManager userManager;
    
    @EJB
    ShoppingCartManager shoppingCartManager;

    @Resource(mappedName = "ReservationQueue")
    private Destination reservationQueue;

    @Override
    public void sendBillingRequest(BillingRequest billingRequest) {
        BillingRequest bill = billingRequest;
        PaymentHistory history = new PaymentHistory();
        history.setBankAccount(bill.getAccountNumber());
        history.setItems(bill.getItems());
        history.setResult(bill.getResult());
        history.setTransactionDate(bill.getTransactionDate());
        history.setUser(userManager.findUserById(bill.getUserId()));
        history = shoppingCartManager.savePaymentHistory(history);
        
        bill.setId(history.getId());
        context.createProducer().send(reservationQueue, billingRequest);
    }

}
