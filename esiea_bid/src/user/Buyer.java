package user;

import java.util.HashSet;
import java.util.List;

import objects.Bid;
import objects.Offer;
import alarm_time.Alarm;
import alarm_time.AlarmObserver;
import alarm_time.AlarmType;

public interface Buyer {
	public List<Bid> displayBid(List<Bid> listBid, List<Offer> listOffer);
	public void doOffer(Bid bid, List<Offer> listOffer, double price, AlarmObserver alarmObserver);
	public void createAlarm(AlarmType alarmType, Bid bid, HashSet<Alarm> listAlarm);
	public void deleteAlarm(AlarmType alarmType, Bid bid, HashSet<Alarm> listAlarm);
	public List<Offer> displayBuyerOffer(List<Offer> listOffer);
}
