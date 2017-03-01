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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String description;
    private Double startPrice;
    private Integer duration; // days
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private Integer state;
    private Boolean freeDelivery = false;

    public Boolean getFreeDelivery() {
        return freeDelivery;
    }

    public void setFreeDelivery(Boolean freeDelivery) {
        this.freeDelivery = freeDelivery;
    }
    
    transient Double highestBid;
    transient Double myHighestBid;
    transient AuctionUser winner;
    
    @ManyToOne
    private AuctionUser user;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<Bid> bids;
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "items")
    private List<Category> categories;
    
    public static final int FOR_SALE = 1;
    public static final int ORDERED = 2;
    public static final int DELIVERING = 3;
    public static final int DELIVERED = 4;

    public Item(Long id, String name, String description, Double startPrice, Integer duration, Date startDate, Date endDate, Integer state, AuctionUser user, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.user = user;
        this.categories = categories;
    }
    
    
    

    public Item(String name, String description, Double startPrice, Integer duration, List<Category> categories) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.duration = duration;
        this.categories = categories;
    }
    
    public Item(String name, String description, Double startPrice, Integer duration) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.duration = duration;
    }
    
    public Item(String name, String description, Double startPrice, Integer duration, Double myHighestBid) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.duration = duration;
        this.myHighestBid = myHighestBid;
    }

    public Item() {
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public AuctionUser getUser() {
        return user;
    }

    public void setUser(AuctionUser user) {
        this.user = user;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    public void addCategory(Category category) {
        categories.add(category);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(Double highestBid) {
        this.highestBid = highestBid;
    }

    public Double getMyHighestBid() {
        return myHighestBid;
    }

    public void setMyHighestBid(Double myHighestBid) {
        this.myHighestBid = myHighestBid;
    }

    public AuctionUser getWinner() {
        return winner;
    }

    public void setWinner(AuctionUser winner) {
        this.winner = winner;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "auction.persistence.Item[ id=" + id + " ]";
    }
    
}
