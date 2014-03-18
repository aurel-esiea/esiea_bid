package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import user.SystemUser;
import esiea_bid.MemoryObject;
import esiea_bid.Product;

public class TestSellerAction {

	private SystemUser user;
	private int bidCreationResult;
	MemoryObject memoryObject;
	Product product1;
	
	@Before
	public void setUp() throws Exception {
		memoryObject = new MemoryObject();
		product1 = new Product("Blue Car");
		memoryObject.listProduct.add(product1);
		user = new SystemUser("Dupont", "Thomas", "password");
	}

	@Test
	public void testBidCreationBadPrice() {
		bidCreationResult = user.createBid(0, -1000.00, 3000.00);
		assertEquals(0, bidCreationResult);
		fail("Negative price");
	}
	
	@Test
	public void testBidCreationBadReservePrice() {
		bidCreationResult = user.createBid(0, 2000.00, 1000.00);
		assertEquals(1, bidCreationResult);
		fail("Reserve price lower than product price");
	}
	
	@Test
	public void testBidCreationAlreadyUsedProduct() {
		bidCreationResult = user.createBid(0, 1000.00, 3000.00);
		assertEquals(2, bidCreationResult);
		fail("Product already used");
	}
	
	@Test
	public void testBidCreationProductDoesntExist() {
		bidCreationResult = user.createBid(0, 1000.00, 3000.00);
		assertEquals(3, bidCreationResult);
		fail("Product doesn't exist");
	}

	
}