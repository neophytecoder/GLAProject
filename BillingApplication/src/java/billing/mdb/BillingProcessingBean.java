/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing.mdb;

import bank.requests.BillingRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author techron
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "ReservationQueue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BillingProcessingBean implements MessageListener {

    @Inject
    JMSContext context;
    
    @Resource(mappedName = "ResultsQueue")
    private Destination resultsQueue;
        
    public BillingProcessingBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        try {
            BillingRequest bill = message.getBody(BillingRequest.class);
            System.out.println("Bill Request from account "+ bill.getAccountNumber() + " received.");
            
            if (bill.getPinCode().trim().equals("1234")) {
                bill.setResult("CONFIRMED");
                context.createProducer().send(resultsQueue, bill);
//                sendResultBill.sendBillingResult(bill);
                System.out.println("Transaction for "+bill.getAccountNumber() + " CONFIRMED");
            }
            else {
                bill.setResult("FAIL");
                context.createProducer().send(resultsQueue, bill);
//                sendResultBill.sendBillingResult(bill);
                System.out.println("Transaction for "+bill.getAccountNumber() + " FAILED");

            }
        } catch (JMSException ex) {
            Logger.getLogger(BillingProcessingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
            
                
        }
        
        
        
    }
    
