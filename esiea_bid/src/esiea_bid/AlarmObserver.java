package esiea_bid;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AlarmObserver implements Observer{
	 
	private BidState state;
	private List<Bid> listBid;
	private List<Offer> listOffer;
	public AlarmObserver(BidState state, List<Bid> listBid, List<Offer> listOffer)
	{
		this.state = state;
		this.listBid = listBid;
		this.listOffer = listOffer;
	}
	
	@Override
	public void update(Observable bid, Object arg1) {
		List<Alarm> listAlarm;
		{
			for (Offer offer : listOffer)
			{
				if(offer.getBid() == bid)
				{
					listAlarm = offer.getBuyer().getListAlarm();
					for (Alarm alarm : listAlarm)
					{
						if(alarm.getAlarmType().equals(AlarmType.BID_CANCELED) && alarm.getAlarmState().equals(AlarmState.ENABLE))
						{
							if(arg1.equals(BidState.CANCELED))
								System.out.println("Alarm : Bid has been canceled");
						}
						
						if(alarm.getAlarmType().equals(AlarmType.RESERVE_PRICE_REACHEABLE) && alarm.getAlarmState().equals(AlarmState.ENABLE))
						{
								if(offer.getBid().getPrice() >= offer.getBid().getReservePrice())
									System.out.println("Alarm : Reserve price reache");
						}
						if(alarm.getAlarmType().equals(AlarmType.BEST_OFFER) && alarm.getAlarmState().equals(AlarmState.ENABLE))
						{
								if(offer.getPrice() < offer.getBid().getPrice())
								{
									System.out.println("Alarm : User made better offer");
								}
						}
						if(alarm.getAlarmType().equals(AlarmType.BUYER_OFFER) && alarm.getAlarmState().equals(AlarmState.ENABLE))
						{
								System.out.println("Alarm : User do an offer on your bid");
						}
					}
				}
			}
		}	
	}
}
