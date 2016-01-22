package teste.banksys.control;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.control.AccountController;
import banksys.persistence.AccountVector;

public class AccountControllerTest {

	AccountController control;
	
	@Before
	public void setUp() throws Exception {
		control = new AccountController(new AccountVector());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented"); // TODO
	}

}
