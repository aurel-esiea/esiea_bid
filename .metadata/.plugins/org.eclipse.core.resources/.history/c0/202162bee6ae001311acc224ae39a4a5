package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import esiea_bid.MemoryObject;
import user.*;

public class TestSystemUserAction {

	private SystemUser user;
	private MemoryObject memoryObject;
	private int loginResult;
	@Before
	public void setUp() throws Exception {
		memoryObject = new MemoryObject();
		user = new SystemUser("Dupont", "Thomas", "password");
	}
	
	@Test
	public void testSucessConnection() {
		loginResult = user.loginUser(memoryObject, user.getPassword());
		assertEquals(1, loginResult);
	}

	@Test
	public void testAlreadyExistConnection() {
		user.loginUser(memoryObject, user.getPassword());
		loginResult = user.loginUser(memoryObject, user.getPassword());
		assertEquals(0, loginResult);
	}
	
	@Test
	public void testFailConnection() {
		loginResult = user.loginUser(memoryObject, user.getPassword() + "error");
		assertEquals(2, loginResult);
	}

}
