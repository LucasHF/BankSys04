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

	@Test  // ta dependedo do teste do metedo cretidat
	public void testeearnInterest() throws NegativeAmountException {
		conta.credit(100);
		conta.earnInterest();
		
		// verificar se saldo atual da conta esta certo depois de chamar earnInterest() 
		assertEquals(conta.getBalance(),(100 * 0.001),0);
		
	}

}
