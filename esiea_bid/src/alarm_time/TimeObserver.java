package alarm_time;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import objects.Bid;
import objects.BidState;
import objects.Offer;

public class TimeObserver implements Observer {

	private List<Bid> listBid;
	private List<Offer> listOffer;
	
	public TimeObserver(List<Bid> listBid, List<Offer> listOffer){
		this.listBid = listBid;
		this.listOffer = listOffer;
	}
	@Override
	public void update(Observable obs, Object arg) {
		if(obs instanceof TimeManager)
		{
			TimeManager timeManager = (TimeManager)obs;
			for (Bid bid : listBid)
			{
				if(timeManager.getSystemTime().after(bid.getEndDate()))
				{
					for (Offer offer : listOffer)
					{
						if(offer.getBid().equals(bid) && Double.compare(offer.getPrice(), bid.getPrice()) == 0 ? true : false)
						{
							bid.setBidState(BidState.END);
							System.out.println("Bid on prodcut : " + bid.getProduct().getDescription() + " is terminated, Final price : " + bid.getPrice() + " Buyer : " + offer.getBuyer().getFirstName());
						}
					}
				}
			}
		}
	}
}
