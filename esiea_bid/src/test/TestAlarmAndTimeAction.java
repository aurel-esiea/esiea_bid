package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import objects.Bid;
import objects.BidState;
import objects.Offer;
import objects.Product;

import org.junit.Before;
import org.junit.Test;

import alarm_time.Alarm;
import alarm_time.AlarmObserver;
import alarm_time.AlarmType;
import alarm_time.TimeManager;
import alarm_time.TimeObserver;
import user.SystemUser;

public class TestAlarmAndTimeAction {

	private SystemUser user, user2, user3;
	private static List<Offer> listOffer;
	private static List<Bid> listBid;
	private static HashSet<Alarm> listAlarm;
	private Bid bid, bid2;
	private Product product1;
	private AlarmObserver alarmObserver;
	private TimeManager timeManager;
	private TimeObserver timeObserver;
	private GregorianCalendar calendar;
	private Date date1;

	@Before
	public void setUp() throws Exception {
		listBid = new ArrayList<Bid>();
		listOffer = new ArrayList<Offer>();
		listAlarm = new HashSet<Alarm>();
		alarmObserver = new AlarmObserver(listAlarm);
		timeObserver = new TimeObserver(listBid, listOffer);
		timeManager = new TimeManager(new Date(), timeObserver);
		product1 = new Product("Blue Car");
		calendar = new GregorianCalendar(2014, Calendar.APRIL, 15);
		date1 = calendar.getTime();
		user = new SystemUser("user", "Dupont", "Thomas", "password");
		user2 = new SystemUser("user2", "Durant", "Paul", "password");
		user3 = new SystemUser("user3", "Hollande", "Fran�ois", "password");
		bid = new Bid(product1, date1, 1000, 2000, user2, alarmObserver);
		bid2 = new Bid(product1, date1, 1000, 2000, user, alarmObserver);
		listBid.add(bid);
		listBid.add(bid2);
	}

	@Test
	public void testCancelAlarm() {
		//User1 has been offer on bid created by User2 and User2 canceled this bid
		System.out.println("Test 1");
		user2.publishBid(bid);
		user.doOffer(bid, listOffer, 1200, alarmObserver);
		user.createAlarm(AlarmType.BID_CANCELED, bid, listAlarm);
		user2.cancelBid(bid);
		assertFalse(user.getNotificationList().isEmpty());
		assertFalse(user2.getNotificationList().isEmpty());
	}
	
	@Test
	public void testBestOfferAlarm() {
		//User1 do an offer on bid created by User2 and other user do a better offer
		System.out.println("Test 2");
		user2.publishBid(bid);
		user.publishBid(bid2);
		user.doOffer(bid, listOffer, 2100, alarmObserver);
		user.createAlarm(AlarmType.BEST_OFFER, bid, listAlarm);
		user3.doOffer(bid, listOffer, 2200, alarmObserver);
		assertFalse(user.getNotificationList().isEmpty());
		assertTrue(user3.getNotificationList().isEmpty());
		assertFalse(user2.getNotificationList().isEmpty());
	}
	
	@Test
	public void testReservePriceReachedAlarm() {
		System.out.println("Test 3");
		//User1 do an offer on bid created by User2 the reserve price is reached
		user2.publishBid(bid);
		user.publishBid(bid2);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		user.createAlarm(AlarmType.RESERVE_PRICE_REACHED, bid, listAlarm);
		user3.doOffer(bid, listOffer, 1600, alarmObserver);
		user3.createAlarm(AlarmType.RESERVE_PRICE_REACHED, bid, listAlarm);
		user.doOffer(bid, listOffer, 2100, alarmObserver);
		user3.doOffer(bid, listOffer, 2200, alarmObserver);
		assertTrue(bid.isReservePriceReached());
		assertFalse(user.getNotificationList().isEmpty());
		assertFalse(user2.getNotificationList().isEmpty());
		assertFalse(user3.getNotificationList().isEmpty());
	}
	
	@Test
	public void testBuyerOffer() {
		//User1 do an offer on bid created by User2, user 2 is notified
		System.out.println("Test 4");
		bid.setBidState(BidState.PUBLISHED);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		assertFalse(user2.getNotificationList().isEmpty());
	}
	
	@Test
	public void testBidEndDate() {
		/*User do an offer on bid created by user2, set the system time after the ended date
		*of the bid, then check if the bidState change.*/
		user2.publishBid(bid);
		user.doOffer(bid, listOffer, 3100, alarmObserver);
		calendar = new GregorianCalendar(2014, Calendar.APRIL, 16);
		timeManager.setSystemTime(calendar.getTime());
		assertEquals(BidState.END, bid.getBidState());
	}
}
