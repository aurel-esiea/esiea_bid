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
import user.SystemUser;

public class TestSellerAction {

	private SystemUser user, user2;
	private static List<Offer> listOffer;
	private static List<Bid> listBid;
	private Bid bid;
	private Product product1;
	private AlarmObserver alarmObserver;
	private static HashSet<Alarm> listAlarm;

	@Before
	public void setUp() throws Exception {
		listOffer = new ArrayList<Offer>();
		listBid = new ArrayList<Bid>();
		listAlarm = new HashSet<Alarm>();
		alarmObserver = new AlarmObserver(BidState.CANCELED, listBid, listOffer, listAlarm);
		product1 = new Product("Blue Car");
		user = new SystemUser("Dupont", "Thomas", "password");
		user2 = new SystemUser("Durant", "Paul", "password");
		bid = new Bid(product1, new Date(), 1000, 2000, user, alarmObserver);
	}

	@Test
	public void testSucessBidCreation() {
		user.createBid(product1, listBid, 1000.00, 3000.00, new Date(), alarmObserver);
		assertFalse(listBid.isEmpty());
	}
	
	@Test
	public void testBidCreationNegativePrice() {
		user.createBid(product1, listBid, -1000.00, 3000.00, new Date(), alarmObserver);
		assertTrue(listBid.isEmpty());
	}

	@Test
	public void testBidCreationBadReservePrice() {
		user.createBid(product1, listBid, 2000.00, 1000.00, new Date(), alarmObserver);
		assertTrue(listBid.isEmpty());
	}
	
	@Test
	public void testBidCreationNegativeReservePrice() {
		user.createBid(product1, listBid, 2000.00, -1000.00, new Date(), alarmObserver);
		assertTrue(listBid.isEmpty());
	}
	
	@Test
	public void testBidCreationBadDate() {
		user.createBid(product1, listBid, 1000.00, 3000.00, new Date(), alarmObserver);
		assertTrue(listBid.isEmpty());
	}
	
	@Test
	public void testBidPublish() {
		user.publishBid(bid);
		assertEquals(BidState.PUBLISHED, bid.getBidState());
	}
	
	@Test
	public void testBidCancelBadUser() {
		bid.setBidState(BidState.PUBLISHED);
		user2.doOffer(bid, listOffer, 1500.00, alarmObserver);
		user2.cancelBid(bid);
		assertEquals(BidState.PUBLISHED, bid.getBidState());
	}
	
	@Test
	public void testBidCancelReservePriceNotReached() {
		bid.setBidState(BidState.PUBLISHED);
		user2.doOffer(bid, listOffer, 1500.00, alarmObserver);
		user.cancelBid(bid);
		assertEquals(BidState.CANCELED, bid.getBidState());
	}
		
	@Test
	public void testBidCancelReservePriceReached() {
		bid.setBidState(BidState.PUBLISHED);
		user2.doOffer(bid, listOffer, 2500.00, alarmObserver);
		user.cancelBid(bid);
		assertEquals(BidState.PUBLISHED, bid.getBidState());
	}	
	



	@Test
	public void displaySellerBidSuccess() {
		bid.setSeller(user);
		bid.setBidState(BidState.PUBLISHED);
		listBid.add(bid);
		List<Bid> sellerBid = user.displaySellerBid(listBid);
		assertFalse(sellerBid.isEmpty());
	}

	@Test
	public void displaySellerBidEmptyList() {
		List<Bid> sellerBid = user.displaySellerBid(listBid);
		assertTrue(sellerBid.isEmpty());
	}

	@Test
	public void displaySellerBidBadUser() {
		bid.setSeller(user);
		bid.setBidState(BidState.PUBLISHED);
		listBid.add(bid);
		List<Bid> sellerBid = user2.displaySellerBid(listBid);
		assertTrue(sellerBid.isEmpty());
	}


	@Test
	public void showBuyerOfferSuccess() {
		bid.setSeller(user);
		bid.setBidState(BidState.PUBLISHED);
		user2.doOffer(bid, listOffer, 3000, alarmObserver);
		listBid.add(bid);
		List<Offer> buyerOffer = user.showBuyerOffer(listOffer);
		assertFalse(buyerOffer.isEmpty());
	}

	@Test
	public void showBuyerOfferBadUser() {
		bid.setSeller(user);
		bid.setBidState(BidState.PUBLISHED);
		listBid.add(bid);
		user2.doOffer(bid, listOffer, 3000, alarmObserver);
		List<Offer> buyerOffer = user2.showBuyerOffer(listOffer);
		assertTrue(buyerOffer.isEmpty());
	}

	@Test
	public void showBuyerOfferEmptyList() {
		List<Offer> buyerOffer = user.showBuyerOffer(listOffer);
		assertTrue(buyerOffer.isEmpty());
	}
}
