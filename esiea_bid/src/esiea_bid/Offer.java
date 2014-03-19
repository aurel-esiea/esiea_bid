package esiea_bid;


import user.SystemUser;

public class Offer {
	
	private Bid bid;
	private double price;
	private SystemUser buyer;
	
	public Offer (Bid bid, double price, SystemUser buyer){
		this.bid = bid;
		this.price = price;
		this.buyer = buyer;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Bid getBid() {
		return bid;
	}
	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public SystemUser getBuyer() {
		return buyer;
	}

	public void setBuyer(SystemUser buyer) {
		this.buyer = buyer;
	}
	
}
