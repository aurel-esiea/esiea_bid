package esiea_bid;

import java.util.Date;

public class Bid {

	private int idBid;
	private static int currentId = 0;
	private Date endDate;
	private Object bidState;
	private double price;
	private double reservePrice;
	
	public Bid (Date endDate, double price, double reservePrice){
		this.idBid = currentId;
		currentId++;
		this.endDate = endDate;
		this.price = price;
		this.reservePrice = reservePrice;
		this.bidState = BidState.CREATED;
	}

	public int getIdBid() {
		return idBid;
	}

	public void setIdBid(int idBid) {
		this.idBid = idBid;
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

	public void setBidState(Object bidState) {
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
	
}
