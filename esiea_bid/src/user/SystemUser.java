package user;

import java.util.Date;
import java.util.List;

import esiea_bid.Bid;
import esiea_bid.BidState;
import esiea_bid.Offer;
import esiea_bid.Product;


public class SystemUser extends AbstractUser implements Buyer, Seller {

	public SystemUser (String lastName, String firstName, String password)
	{
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
	}
	
	@Override
	public void createBid(Product product,List<Bid> listBid,  double price, double reservePrice, Date endDate) {
		if (price < 0)
		{
			System.out.println("Negative Price");
		}
		else if(reservePrice < 0)
		{
			System.out.println("Negative Reserve price");
		}
		else if(price > reservePrice)
		{
			System.out.println("Reserve price is lower than price");
		}
		else
		{
			//try
			Bid bid = new Bid(product ,new Date(), price, reservePrice, this);
			listBid.add(bid);
		}
	}

	@Override
	public void publishBid(Bid bid) {
		bid.setBidState(BidState.PUBLISHED);
	}
	
	@Override
	public void cancelBid(Bid bid) {
		if(bid.getPrice() < bid.getReservePrice())
		{	
			if(bid.getSeller().equals(this))
			{
				bid.setBidState(BidState.CANCELED);
			    System.out.println("Bid canceled");
			}
			else
			{
				System.out.println("You don't are the seller, unable to cancel this bid");	
			}	
		}
	    else 
			System.out.println("Reserve price has been reached, unable to cancel this bid");
	}

	@Override
	public void displayBid(List<Bid> listBid, List<Offer> listOffer) {
		for (Bid bid : listBid) {
			if(bid.getBidState().equals(BidState.PUBLISHED))
			{
				System.out.println("Product description : " + bid.getProduct().getDescription());
				System.out.println("Product price : " + bid.getPrice());
				System.out.println("Bid end the : " + bid.getEndDate());
				if(bid.getPrice() >= bid.getReservePrice())
					System.out.println("Reserve price has been reached");
			}
		}
	}

	@Override
	public void doOffer(Bid bid, List<Offer> listOffer, double price) {
		if (price < bid.getPrice())
		{
			System.out.println("Offer price lower than current bid price");		
		}
		else if(!bid.getBidState().equals(BidState.PUBLISHED))
		{
			System.out.println("Bid is not in state published");		
		}
		else if(bid.getSeller().equals(this))
		{
			System.out.println("A seller cannot do an offer on his own bid");		
		}
		else
		{
			Offer offer = new Offer(bid, price, this);
			listOffer.add(offer);
			bid.setPrice(price);
			System.out.println("Offer created");		
		}
	}

	@Override
	public void displaySellerBid() {
		
	}
	
	@Override
	public void showBuyerOffer() {
		
	}
	
	@Override
	public void displayBuyerOffer() {
		
	}
	
}
