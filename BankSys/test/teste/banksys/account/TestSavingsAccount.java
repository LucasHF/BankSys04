package teste.banksys.account;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import banksys.account.SavingsAccount;
import banksys.account.exception.NegativeAmountException;

public class TestSavingsAccount {

	static SavingsAccount conta;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conta = new SavingsAccount("123") ;	
	}

	@Test 
	public void testeearnInterest() throws NegativeAmountException {
		conta.credit(100);
		conta.earnInterest();
		assertEquals(conta.getBalance(),100.1,0);		
	}

}
