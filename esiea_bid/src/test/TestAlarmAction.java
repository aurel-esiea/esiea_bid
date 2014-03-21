package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import user.SystemUser;
import esiea_bid.AlarmType;
import esiea_bid.Bid;
import esiea_bid.BidState;
import esiea_bid.AlarmObserver;
import esiea_bid.Offer;
import esiea_bid.Product;

public class TestAlarmAction {

	private SystemUser user;
	private SystemUser user2;
	private static List<Offer> listOffer;
	private static List<Bid> listBid;
	private Bid bid;
	private Product product1;
	private AlarmObserver cancelObserver;

	
	@Before
	public void setUp() throws Exception {
		listBid = new ArrayList<Bid>();
		listOffer = new ArrayList<Offer>();
		cancelObserver = new AlarmObserver(BidState.CANCELED, listBid, listOffer);
		product1 = new Product("Blue Car");
		user = new SystemUser("Dupont", "Thomas", "password");
		user2 = new SystemUser("Durant", "Paul", "password");
		bid = new Bid(product1, new Date(), 1000, 2000, user2, cancelObserver);
	}

	@Test
	public void testCancelAlarm() {
		//User1 has been offer on bid created by User2 and User2 canceled this bid
		System.out.println("Test 1");
		bid.setBidState(BidState.PUBLISHED);
		user.doOffer(bid, listOffer, 1000);
		user.enableAlarm(AlarmType.BID_CANCELED);
		user2.cancelBid(bid);
	}
	
	@Test
	public void testReservPriceReachedAlarm() {
		//User1 do an offer on bid created by User2 the reserve price is reached
		System.out.println("Test 2");
		listBid.add(bid);
		bid.setBidState(BidState.PUBLISHED);
		user.enableAlarm(AlarmType.RESERVE_PRICE_REACHEABLE);
		user.doOffer(bid, listOffer, 2000);
	}
	
	@Test
	public void testBestOfferAlarm() {
		//User1 do an offer on bid created by User2 and other user do a better offer
		System.out.println("Test 3");
		listBid.add(bid);
		bid.setBidState(BidState.PUBLISHED);
		user.enableAlarm(AlarmType.BEST_OFFER);
		user.doOffer(bid, listOffer, 1500);
		user.doOffer(bid, listOffer, 1800);
	}
	
	@Test
	public void testBuyerOffer() {
		//User1 do an offer on bid created by User2, user 2 is notified
		System.out.println("Test 4");
		listBid.add(bid);
		bid.setBidState(BidState.PUBLISHED);
		user.doOffer(bid, listOffer, 1500);
	}
}
