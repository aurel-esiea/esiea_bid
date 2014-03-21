package user;

import java.util.List;

import esiea_bid.Alarm;
import esiea_bid.AlarmType;
import esiea_bid.Bid;
import esiea_bid.Offer;

public interface Buyer {
	public List<Bid> displayBid(List<Bid> listBid, List<Offer> listOffer);
	public void displayBuyerOffer();
	public void doOffer(Bid bid, List<Offer> listOffer, double price);
	public void enableAlarm(AlarmType alarmType);
	public void disableAlarm(AlarmType alarmType);
}
