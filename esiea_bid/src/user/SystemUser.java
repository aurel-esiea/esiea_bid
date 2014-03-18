package user;

import esiea_bid.MemoryObject;


public class SystemUser extends AbstractUser implements Buyer, Seller {

	public SystemUser (String lastName, String firstName, String password)
	{
		super(lastName, firstName, password);
	}
	@Override
	public int createBid(int idProduct, double price, double reserverPrice) {
		return -1;
	}

	@Override
	public void cancelBid(int idBid) {
		
	}

	@Override
	public void displayBid() {
		
	}

	@Override
	public void doOffer() {
		
	}

	@Override
	public void displaySellerBid() {
		
	}
	@Override
	public void publishBid(int bidId) {
		
	}
	@Override
	public void showBuyerOffer() {
		
	}
	@Override
	public void displayBuyerOffer() {
		
	}
	
}
