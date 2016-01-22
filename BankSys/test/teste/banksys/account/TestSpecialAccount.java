package teste.banksys.account;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import banksys.account.SpecialAccount;
import banksys.account.exception.NegativeAmountException;

public class TestSpecialAccount {
	
	static SpecialAccount conta;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	      conta = new SpecialAccount("123"); 
	}

	@Test
	public void testArnBonus() throws NegativeAmountException {
		conta.credit(100);
		conta.earnBonus();
		assertEquals(conta.getBalance(),101,0);
	}
	
	@Test
	public void testCredit() throws NegativeAmountException{
		conta.credit(100);
		assertEquals(conta.getBonus(),1,0);
	}

}
