package user;

import java.util.*;

import objects.Bid;
import objects.Offer;
import objects.Product;
import alarm_time.AlarmObserver;

public interface Seller {
	public void createBid(Product product, List<Bid> listBid, double price, double reservePrice, Date endDate,AlarmObserver cancelObserver);
	public void publishBid (Bid bid);
	public void cancelBid(Bid bid);
	public List<Bid> displaySellerBid(List<Bid> listBid);
	public List<Offer> showBuyerOffer(List<Offer> listOffer);
}
