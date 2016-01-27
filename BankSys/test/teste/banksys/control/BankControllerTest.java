package teste.banksys.control;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banksys.account.OrdinaryAccount;
import banksys.control.BankController;
import banksys.control.exception.BankTransactionException;
import banksys.persistence.AccountVector;
import banksys.persistence.exception.AccountNotFoundException;

public class BankControllerTest {
	BankController controller;
	AccountVector av = new AccountVector();
	OrdinaryAccount account = new OrdinaryAccount("123");
	
	@Before
	public void setUp() throws Exception {
		controller = new BankController(av);
	}

	@Test
	public void testAddAccount() throws BankTransactionException, AccountNotFoundException {
		controller.addAccount(account);
		assertEquals(account, controller.getRepository().retrieve("123"));
	}
	
	@Test(expected=BankTransactionException.class)
	public void testAddExistentAccount()throws BankTransactionException{
		controller.addAccount(account);
		controller.addAccount(account);
	}

	@Test(expected=AccountNotFoundException.class)
	public void testRemoveAccount() throws BankTransactionException, AccountNotFoundException {
		controller.addAccount(account);
		controller.removeAccount("123");
		controller.getRepository().retrieve("123");
	}
	
	@Test(expected=BankTransactionException.class)
	public void testRemoveInexistentAccount() throws BankTransactionException{
		controller.removeAccount("123");
	}
	@Test
	public void testGetRepository() {
		assertEquals(av, controller.getRepository());
	}

}
