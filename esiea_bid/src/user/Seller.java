package user;

import java.util.*;

import objects.Bid;
import objects.Product;
import alarm_time.AlarmObserver;

public interface Seller {
	public void createBid(Product product, List<Bid> listBid, double price, double reservePrice, Date endDate,AlarmObserver cancelObserver);
	public void publishBid (Bid bid);
	public void cancelBid(Bid bid);
	public void displaySellerBid();
	public void showBuyerOffer();
}
