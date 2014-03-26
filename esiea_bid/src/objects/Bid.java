package objects;

import java.util.Date;
import java.util.Observable;

import alarm_time.AlarmObserver;
import user.SystemUser;

public class Bid extends Observable{

	private Date endDate;
	private BidState bidState;
	private double price;
	private double reservePrice;
	private Product product;
	private SystemUser seller;
	private boolean reservePriceReached;

	public Bid (Product product, Date endDate, double price, double reservePrice, SystemUser seller, AlarmObserver alarmObserver){
		this.product = product;
		this.endDate = endDate;
		this.price = price;
		this.reservePrice = reservePrice;
		this.seller = seller;
		this.bidState = BidState.CREATED;
		this.reservePriceReached = false;
		this.addObserver(alarmObserver);
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
		//Notify observers than the bidState has changed
		setChanged();
	    notifyObservers(this.bidState);
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

	public boolean isReservePriceReached() {
		return reservePriceReached;
	}

	public void setReservePriceReached(boolean reservePriceReached) {
		this.reservePriceReached = reservePriceReached;
	}
}
