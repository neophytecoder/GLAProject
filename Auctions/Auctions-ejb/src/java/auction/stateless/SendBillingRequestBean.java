/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.stateless;

import bank.requests.BillingRequest;
import javax.annotation.Resource;
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
    
    @Resource(mappedName = "ReservationQueue")
    private Destination reservationQueue;

    @Override
    public void sendBillingRequest(BillingRequest billingRequest) {
        context.createProducer().send(reservationQueue, billingRequest);
    }
    
}
