package teste.banksys.account;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import banksys.account.OrdinaryAccount;
import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public class TestOrdinaryAccount {

	static OrdinaryAccount conta;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 conta = new OrdinaryAccount("123");
	}

	//debitar normal
	@Test
	public void testDebit() throws NegativeAmountException, InsufficientFundsException {
		conta.credit(100);
		conta.debit(90);
		assertEquals(conta.getBalance(),10,0);
	}
	
	//debitar com saldo insufisciente
	@Test (expected = InsufficientFundsException.class )
	public void testDebitInsufficient() throws NegativeAmountException, InsufficientFundsException {
		conta.debit(90);
		assertEquals(conta.getBalance(),0,0);
	}
	
	//debitar valor negativo
		@Test (expected = NegativeAmountException.class )
		public void testDebitNegative() throws NegativeAmountException, InsufficientFundsException {
			conta.debit(-90);
			assertEquals(conta.getBalance(),0,0);
		}
}
