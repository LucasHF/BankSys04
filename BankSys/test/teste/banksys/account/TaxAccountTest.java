package teste.banksys.account;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banksys.account.TaxAccount;
import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public class TaxAccountTest {

	TaxAccount account;
	
	@Before
	public void setUp() throws Exception {
		 account = new TaxAccount("123");
		account.credit(100);
	}


	@Test
	public void testDebit() throws NegativeAmountException, InsufficientFundsException {
		account.debit(10);
		double tax = 10*(0.001);
		assertEquals(90-tax, account.getBalance(), 0);
		
	}
	
	@Test(expected = NegativeAmountException.class)
	public void testNegativeDebit() throws NegativeAmountException, InsufficientFundsException {
		account.debit(-10);
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void testInsufficientFundsDebit() throws NegativeAmountException, InsufficientFundsException {
		account.debit(100);
	}
}
