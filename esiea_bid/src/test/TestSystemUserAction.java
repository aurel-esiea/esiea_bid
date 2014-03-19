package test;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import user.*;

public class TestSystemUserAction {

	private SystemUser user;
	private List<Object> listUserConnected;
	@Before
	public void setUp() throws Exception {
		listUserConnected = new ArrayList<Object>();
		user = new SystemUser("Dupont", "Thomas", "password");
	}
	
	@Test
	public void testSucessConnection() {
		user.loginUser(listUserConnected, user.getPassword());
		assertEquals(user, listUserConnected.get(0));
	}

	@Test
	public void testAlreadyExistConnection() {
		user.loginUser(listUserConnected, user.getPassword());
		user.loginUser(listUserConnected, user.getPassword());
		assertNotEquals(user, listUserConnected.get(1));
	}
	
	@Test
	public void testFailConnection() {
		user.loginUser(listUserConnected, user.getPassword() + "error");
		assertNotEquals(user, listUserConnected.get(0));
	}

}
