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
import esiea_bid.Product;

public class TestSellerAction {

	private SystemUser user;
	private static List<Bid> listBid;
	private Bid bid;
	private Product product1;
	
	@Before
	public void setUp() throws Exception {
		listBid = new ArrayList<Bid>();
		product1 = new Product("Blue Car");
		user = new SystemUser("Dupont", "Thomas", "password");
		bid = new Bid(product1, new Date(), 1000, 2000, user);
	}

	@Test
	public void testSucessBidCreation() {
		user.createBid(product1, listBid, 1000.00, 3000.00, new Date());
		assertTrue(!listBid.isEmpty());
	}
	
	@Test
	public void testBidCreationNegativePrice() {
		user.createBid(product1, listBid, -1000.00, 3000.00, new Date());
		assertTrue(listBid.isEmpty());
	}

	@Test
	public void testBidCreationBadReservePrice() {
		user.createBid(product1, listBid, 2000.00, 1000.00, new Date());
		assertTrue(listBid.isEmpty());
	}
	
	@Test
	public void testBidCreationNegativeReservePrice() {
		user.createBid(product1, listBid, 2000.00, -1000.00, new Date());
		assertTrue(listBid.isEmpty());
	}
	
	@Test
	public void testBidPublish() {
		user.publishBid(bid);
		assertEquals(BidState.PUBLISHED, bid.getBidState());
	}
	
	@Test
	public void testBidCancel() {
		user.cancelBid(bid);
		assertEquals(BidState.CANCELED, bid.getBidState());
	}
	
	
	
}
