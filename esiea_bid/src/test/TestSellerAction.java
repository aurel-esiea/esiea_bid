package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import user.SystemUser;
import esiea_bid.Bid;
import esiea_bid.BidState;
import esiea_bid.AlarmObserver;
import esiea_bid.Offer;
import esiea_bid.Product;

public class TestSellerAction {

	private SystemUser user;
	private SystemUser user2;
	private static List<Offer> listOffer;
	private static List<Bid> listBid;
	private Bid bid;
	private Product product1;
	private AlarmObserver cancelObserver;
	
	@Before
	public void setUp() throws Exception {
		listOffer = new ArrayList<Offer>();
		listBid = new ArrayList<Bid>();
		cancelObserver = new AlarmObserver(BidState.CANCELED, listBid, listOffer);
		product1 = new Product("Blue Car");
		user = new SystemUser("Dupont", "Thomas", "password");
		user2 = new SystemUser("Durant", "Paul", "password");
		bid = new Bid(product1, new Date(), 1000, 2000, user, cancelObserver);
	}

	@Test
	public void testSucessBidCreation() {
		user.createBid(product1, listBid, 1000.00, 3000.00, new Date(), cancelObserver);
		assertTrue(!listBid.isEmpty());
	}
	
	@Test
	public void testBidCreationNegativePrice() {
		user.createBid(product1, listBid, -1000.00, 3000.00, new Date(), cancelObserver);
		assertTrue(listBid.isEmpty());
	}

	@Test
	public void testBidCreationBadReservePrice() {
		user.createBid(product1, listBid, 2000.00, 1000.00, new Date(), cancelObserver);
		assertTrue(listBid.isEmpty());
	}
	
	@Test
	public void testBidCreationNegativeReservePrice() {
		user.createBid(product1, listBid, 2000.00, -1000.00, new Date(), cancelObserver);
		assertTrue(listBid.isEmpty());
	}
	
	@Test
	public void testBidCreationBadDate() {
		user.createBid(product1, listBid, 1000.00, 3000.00, new Date(), cancelObserver);
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
		user2.doOffer(bid, listOffer, 1500.00);
		user2.cancelBid(bid);
		assertEquals(BidState.PUBLISHED, bid.getBidState());
	}
	
	@Test
	public void testBidCancelReservePriceNotReached() {
		bid.setBidState(BidState.PUBLISHED);
		user2.doOffer(bid, listOffer, 1500.00);
		user.cancelBid(bid);
		assertEquals(BidState.CANCELED, bid.getBidState());
	}
		
	@Test
	public void testBidCancelReservePriceReached() {
		bid.setBidState(BidState.PUBLISHED);
		user2.doOffer(bid, listOffer, 2500.00);
		user.cancelBid(bid);
		assertEquals(BidState.PUBLISHED, bid.getBidState());
	}	
}
