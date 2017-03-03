/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity
public class AuctionUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true)
    private String userName;
    private String password;
    private String name;
    private String address;
    private String bankAccount;
    private Integer bidPenalty;
    @Temporal(TemporalType.DATE)
    private Date endPenalty;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Item> sellingItems;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Bid> bids;
    
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private ShoppingCart shoppingCart;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<PaymentHistory> histories;

    public AuctionUser() {
    }

    public AuctionUser(Long id, String userName, String password, String name, String address, String bankAccount) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.address = address;
        this.bankAccount = bankAccount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<Item> getSellingItems() {
        return sellingItems;
    }

    public void setSellingItems(List<Item> sellingItems) {
        this.sellingItems = sellingItems;
    }
    
    public void addItem(Item sellingItem) {
        sellingItems.add(sellingItem);
    }
    
    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
    
    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public Integer getBidPenalty() {
        if (bidPenalty == null) {
            return 0;
        }
        return bidPenalty;
    }

    public void setBidPenalty(Integer bidPenalty) {
        this.bidPenalty = bidPenalty;
    }

    public Date getEndPenalty() {
        return endPenalty;
    }

    public void setEndPenalty(Date startPenalty) {
        this.endPenalty = startPenalty;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public List<PaymentHistory> getHistories() {
        return histories;
    }

    public void setHistories(List<PaymentHistory> histories) {
        this.histories = histories;
    }
    
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuctionUser)) {
            return false;
        }
        AuctionUser other = (AuctionUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "auction.persistence.User[ id=" + id + " ]";
    }
    
}
