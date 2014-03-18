package user;

import java.util.*;

public interface Seller {
	public int createBid (int idProduct, double price, double reserverPrice);
	public void publishBid (int bidId);
	public void cancelBid(int idBid);
	public void displaySellerBid();
	public void showBuyerOffer();
}
