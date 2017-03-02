/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.requests;

import java.io.Serializable;

/**
 *
 * @author techron
 */
public class BillingRequest implements Serializable{
    
    private String accountNumber;
    private Double amountToDebit;
    private String pinCode;

    public BillingRequest() {
    }

    public BillingRequest(String accountNumber, Double amountToDebit, String pinCode) {
        this.accountNumber = accountNumber;
        this.amountToDebit = amountToDebit;
        this.pinCode = pinCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmountToDebit() {
        return amountToDebit;
    }

    public void setAmountToDebit(Double amountToDebit) {
        this.amountToDebit = amountToDebit;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
    
    
    
}
