package esiea_bid;

import java.util.Date;

import user.SystemUser;

public class Bid {

	private Date endDate;
	private BidState bidState;
	private double price;
	private double reservePrice;
	private Product product;
	private SystemUser seller;
	
	public Bid (Product product, Date endDate, double price, double reservePrice, SystemUser seller){
		this.setProduct(product);
		this.endDate = endDate;
		this.price = price;
		this.reservePrice = reservePrice;
		this.seller = seller;
		this.bidState = BidState.CREATED;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Object getBidState() {
		return bidState;
	}

	public void setBidState(BidState bidState) {
		this.bidState = bidState;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(double reservePrice) {
		this.reservePrice = reservePrice;
	}

	public SystemUser getSeller() {
		return seller;
	}

	public void setSeller(SystemUser seller) {
		this.seller = seller;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
