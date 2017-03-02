/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.stateless;

import bank.requests.BillingRequest;
import javax.ejb.Local;

/**
 *
 * @author techron
 */
@Local
public interface SendBillingRequest {

    public void sendBillingRequest(BillingRequest billingRequest);

}
