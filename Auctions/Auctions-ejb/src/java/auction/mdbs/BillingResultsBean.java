/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.mdbs;

import bank.requests.BillingRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

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
    
    public BillingResultsBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        try {
            BillingRequest bill = message.getBody(BillingRequest.class);
            System.out.println("Answer from Bank Received!  Transaction for "+ bill.getAccountNumber() + " is " 
                                                                                                + bill.getResult());
        } catch (JMSException ex) {
            Logger.getLogger(BillingResultsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
