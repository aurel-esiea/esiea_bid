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
import esiea_bid.Offer;
import esiea_bid.Product;

public class TestBuyerAction {

	private SystemUser user;
	private SystemUser user2;
	private static List<Offer> listOffer;
	private static List<Bid> listBid;
	private Bid bid;
	private Product product1;
	
	@Before
	public void setUp() throws Exception {
		listBid = new ArrayList<Bid>();
		listOffer = new ArrayList<Offer>();
		product1 = new Product("Blue Car");
		user = new SystemUser("Dupont", "Thomas", "password");
		user2 = new SystemUser("Durant", "Paul", "password");
		bid = new Bid(product1, new Date(), 1000, 2000, user2);
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
	public void testDisplayBid() {
		bid.setBidState(BidState.PUBLISHED);
		//user2.displayBid();
		assertTrue(listOffer.isEmpty());
	}
}
