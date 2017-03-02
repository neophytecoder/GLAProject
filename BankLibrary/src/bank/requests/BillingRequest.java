/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.requests;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author techron
 */
public class BillingRequest implements Serializable{
    
    private String accountNumber;
    private Double amountToDebit;
    private String pinCode;
    private String result;
    private String items;
    private Long userId;
    private Date transactionDate;
    private Long id;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BillingRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public BillingRequest(String accountNumber, Double amountToDebit, String pinCode, String result, String items, Long userId, Date transactionDate) {
        this.accountNumber = accountNumber;
        this.amountToDebit = amountToDebit;
        this.pinCode = pinCode;
        this.result = result;
        this.items = items;
        this.userId = userId;
        this.transactionDate = transactionDate;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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
