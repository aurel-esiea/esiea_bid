package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
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
import user.SystemUser;

public class TestBuyerAction {

	private SystemUser user, user2;
	private static List<Offer> listOffer;
	private static List<Bid> listBid;
	private static HashSet<Alarm> listAlarm;
	private Bid bid;
	private Product product1;
	private AlarmObserver alarmObserver;

	@Before
	public void setUp() throws Exception {
		listBid = new ArrayList<Bid>();
		listOffer = new ArrayList<Offer>();
		listAlarm = new HashSet<Alarm>();
		alarmObserver = new AlarmObserver(listAlarm);
		product1 = new Product("Blue Car");
		user = new SystemUser("Dupont", "Thomas", "password");
		user2 = new SystemUser("Durant", "Paul", "password");
		bid = new Bid(product1, new Date(), 1000, 2000, user2, alarmObserver);
        listBid.add(bid);
	}

	@Test
	public void testdoOfferSucess() {
		bid.setBidState(BidState.PUBLISHED);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		assertFalse(listOffer.isEmpty());
	}
	
	@Test
	public void testdoOfferBadPrice() {
		bid.setBidState(BidState.PUBLISHED);
		user.doOffer(bid, listOffer, 800, alarmObserver);
		assertTrue(listOffer.isEmpty());
	}
	
	@Test
	public void testdoOfferBidNotPublished() {
		bid.setBidState(BidState.CREATED);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		assertTrue(listOffer.isEmpty());
	}
	
	@Test
	public void testdoOfferByTheSeller() {
		bid.setBidState(BidState.PUBLISHED);
		user2.doOffer(bid, listOffer, 1500, alarmObserver);
		assertTrue(listOffer.isEmpty());
	}
	
	@Test
	public void testDisplayBidSuccess() {
		user2.publishBid(bid);
        List<Bid> visibleBids = user.displayBid(listBid, listOffer);
		assertFalse(visibleBids.isEmpty());
	}

	@Test
	public void testDisplayBidBadStateCreated() {
		bid.setBidState(BidState.CREATED);
        List<Bid> visibleBids = user.displayBid(listBid, listOffer);
		assertTrue(visibleBids.isEmpty());
	}
	
	@Test
	public void testDisplayBidBadStateCanceled() {
		user2.publishBid(bid);
		user2.cancelBid(bid);
        List<Bid> visibleBids = user.displayBid(listBid, listOffer);
		assertTrue(visibleBids.isEmpty());
	}
	
	@Test
	public void testDisplayBidSuccessOffer() {
		user2.publishBid(bid);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		user2.cancelBid(bid);
		List<Bid> visibleBids = user.displayBid(listBid, listOffer);
		assertFalse(visibleBids.isEmpty());
	}
	
	@Test
	public void testDisplayBidBadUserForOffer() {
		user2.publishBid(bid);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		user2.cancelBid(bid);
        List<Bid> visibleBids = user2.displayBid(listBid, listOffer);
		assertTrue(visibleBids.isEmpty());
	}
	
	@Test
	public void testDisplayBuyerOfferSuccess() {
		user2.publishBid(bid);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		List<Offer> buyerOffer = user.displayBuyerOffer(listOffer);
		assertFalse(buyerOffer.isEmpty());
	}
	
	@Test
	public void testDisplayBuyerOfferBadUser() {
		user2.publishBid(bid);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		List<Offer> buyerOffer = user2.displayBuyerOffer(listOffer);
		assertTrue(buyerOffer.isEmpty());
	}

	@Test
	public void testDisplayBuyerOfferEmptyList() {
		List<Offer> buyerOffer = user.displayBuyerOffer(listOffer);
		assertTrue(buyerOffer.isEmpty());
	}	
	
	@Test
	public void testCreateAlarmSuccess() {
		user2.publishBid(bid);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		user.createAlarm(AlarmType.BID_CANCELED, bid, listAlarm);
		assertEquals(listAlarm.size(), 1);
	}	
	
	@Test
	public void testCreateAlarmAlreadyExist() {
		user2.publishBid(bid);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		user.createAlarm(AlarmType.BID_CANCELED, bid, listAlarm);
		user.createAlarm(AlarmType.BID_CANCELED, bid, listAlarm);
		assertEquals(listAlarm.size(), 2);
	}	
	
	@Test
	public void testDeleteAlarmSucess() {
		user2.publishBid(bid);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		user.createAlarm(AlarmType.BID_CANCELED, bid, listAlarm);
		user.deleteAlarm(AlarmType.BID_CANCELED, bid, listAlarm);
		assertEquals(listAlarm.size(), 0);
	}
	
	@Test
	public void testDeleteNonExistingAlarm() {
		user2.publishBid(bid);
		user.doOffer(bid, listOffer, 1500, alarmObserver);
		user.deleteAlarm(AlarmType.BID_CANCELED, bid, listAlarm);
		assertEquals(listAlarm.size(), 0);
	}		
}
