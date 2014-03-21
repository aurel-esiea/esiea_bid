package user;

import java.util.*;

import esiea_bid.Bid;
import esiea_bid.AlarmObserver;
import esiea_bid.Product;

public interface Seller {
	public void createBid(Product product, List<Bid> listBid, double price, double reservePrice, Date endDate,AlarmObserver cancelObserver);
	public void publishBid (Bid bid);
	public void cancelBid(Bid bid);
	public void displaySellerBid();
	public void showBuyerOffer();
}
