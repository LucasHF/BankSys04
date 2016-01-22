package teste.banksys.account;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import banksys.account.SavingsAccount;
import banksys.account.exception.NegativeAmountException;

public class TesteSavingsAccount {

	static SavingsAccount conta;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conta = new SavingsAccount("123") ;	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test 
	public void testeearnInterest() throws NegativeAmountException {
		conta.credit(100);
		conta.earnInterest();
		assertEquals(conta.getBalance(),(100 * 0.001),0);
		
	}

}
