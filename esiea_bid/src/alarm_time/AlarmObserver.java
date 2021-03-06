package alarm_time;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import objects.Bid;
import objects.BidState;
import objects.Offer;

public class AlarmObserver implements Observer{
	 
	private HashSet<Alarm> listAlarm;

	public AlarmObserver(HashSet<Alarm> listAlarm)
	{
		this.listAlarm = listAlarm;
	}
	
	/**  
	 * Receive notification from observable object and do revelant actions 
	 * @param Observable obs
	 * @return Object arg1
	 *
	 */
	@Override
	public void update(Observable obs, Object arg1) {
		// Check if observable is a Bid, then if the new bid state is Cancel, notify users.
		if(obs instanceof Bid)
		{
			if(arg1.equals(BidState.CANCELED))
			{
				for (Alarm alarm : listAlarm)
				{
					if(alarm.getBid() == obs && alarm.getAlarmType().equals(AlarmType.BID_CANCELED))
					{
						System.out.println("Alarm for " + alarm.getUser().getFirstName() + ": bid on product " + alarm.getBid().getProduct().getDescription()+ " has been canceled ");
						alarm.getUser().getNotificationList().add("Alarm for " + alarm.getUser().getFirstName() + ": bid on product " + alarm.getBid().getProduct().getDescription()+ " has been canceled ");
					}
				}	
			}
		}
		
		/* Check if observable is an offer, then if the reserve price is reached 
		 * or/and if a user do a better offer, notify users.*/
		if(obs instanceof Offer)
		{
			Offer offer = (Offer)obs;
	
			if(offer.getPrice() >= offer.getBid().getReservePrice() && !offer.getBid().isReservePriceReached())
			{
				offer.getBid().setReservePriceReached(true);
				for (Alarm alarm : listAlarm)
				{
					if(alarm.getAlarmType().equals(AlarmType.RESERVE_PRICE_REACHED) && alarm.getBid().equals(offer.getBid()))
					{
						System.out.println("Alarm for " + alarm.getUser().getFirstName() + ": reserve Price on product " + offer.getBid().getProduct().getDescription() + " has been reached");
						alarm.getUser().getNotificationList().add("Alarm for " + alarm.getUser().getFirstName() + ": reserve Price on product " + offer.getBid().getProduct().getDescription() + " has been reached");
					}
				}
			}	
			
			if(offer.getPrice() > offer.getBid().getPrice())
			{
				for (Alarm alarm : listAlarm)
				{
					if(alarm.getAlarmType().equals(AlarmType.BEST_OFFER) && alarm.getBid().equals(offer.getBid()) && !alarm.getUser().equals(offer.getBuyer()))
					{	
						System.out.println("Alarm for " + alarm.getUser().getFirstName() + ": user " + offer.getBuyer().getFirstName() + " do better offer on product " + offer.getBid().getProduct().getDescription());
						alarm.getUser().getNotificationList().add("Alarm for " + alarm.getUser().getFirstName() + ": user " + offer.getBuyer().getFirstName() + " do better offer on product " + offer.getBid().getProduct().getDescription());
					}
				}
			}
			System.out.println("Alarm for " + offer.getBid().getSeller().getFirstName() + ": user " + offer.getBuyer().getFirstName() + " do an offer on your bid " + offer.getBid().getProduct().getDescription());
			offer.getBid().getSeller().getNotificationList().add("Alarm for " + offer.getBid().getSeller().getFirstName() + ": user " + offer.getBuyer().getFirstName() + " do an offer on your bid " + offer.getBid().getProduct().getDescription());
		}
	}
}
