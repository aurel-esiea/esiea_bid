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

public class TestBuyerAction {

	private SystemUser user;
	private SystemUser user2;
	private static List<Offer> listOffer;
	private static List<Bid> listBid;
	private Bid bid;
	private Offer offer;
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
		offer = new Offer(bid, 1000, user);
	}

	@Test
	public void testdoOfferSucess() {
		bid.setBidState(BidState.PUBLISHED);
		user.doOffer(bid, listOffer, 1500);
		assertTrue(!listOffer.isEmpty());
	}
	
	@Test
	public void testdoOfferBadPrice() {
		bid.setBidState(BidState.PUBLISHED);
		user.doOffer(bid, listOffer, 800);
		assertTrue(listOffer.isEmpty());
	}
	
	@Test
	public void testdoOfferBidNotPublished() {
		bid.setBidState(BidState.CREATED);
		user.doOffer(bid, listOffer, 1500);
		assertTrue(listOffer.isEmpty());
	}
	
	@Test
	public void testdoOfferByTheSeller() {
		bid.setBidState(BidState.PUBLISHED);
		user2.doOffer(bid, listOffer, 1500);
		assertTrue(listOffer.isEmpty());
	}
	
	@Test
	public void testDisplayBidSuccess() {
		bid.setBidState(BidState.PUBLISHED);
        listBid.add(bid);
        List<Bid> visibleBids = user.displayBid(listBid, listOffer);
		assertFalse(visibleBids.isEmpty());
	}

	@Test
	public void testDisplayBidBadStateCreated() {
		// bid belongs to user2
		bid.setBidState(BidState.CREATED);
        List<Bid> visibleBids = user.displayBid(listBid, listOffer);
		assertTrue(visibleBids.isEmpty());
	}
	
	@Test
	public void testDisplayBidBadStateCanceled() {
		// bid belongs to user2
		bid.setBidState(BidState.CANCELED);
        List<Bid> visibleBids = user.displayBid(listBid, listOffer);
		assertTrue(visibleBids.isEmpty());
	}
	
	@Test
	public void testDisplayBidBadUserForOffer() {
		bid.setPrice(1000);
		bid.setReservePrice(100);
		bid.setBidState(BidState.PUBLISHED);
		user.doOffer(bid, listOffer, 1500);
		bid.setBidState(BidState.CANCELED);
        List<Bid> visibleBids = user2.displayBid(listBid, listOffer);
		assertTrue(visibleBids.isEmpty());
	}
	
	@Test
	public void testDisplayBidSuccessOffer() {
		bid.setPrice(1000);
		bid.setReservePrice(100);
		bid.setBidState(BidState.PUBLISHED);
		user.doOffer(bid, listOffer, 1500);
		bid.setBidState(BidState.CANCELED);
        List<Bid> visibleBids = user.displayBid(listBid, listOffer);
		assertFalse(visibleBids.isEmpty());
	}
}
