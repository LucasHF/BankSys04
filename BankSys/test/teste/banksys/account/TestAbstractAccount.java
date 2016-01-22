package teste.banksys.account;

import static org.junit.Assert.*;

import org.junit.Test;
import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.exception.NegativeAmountException;

public class TestAbstractAccount {
	
	AbstractAccount conta = new OrdinaryAccount("123");

	@Test
	public void test_credit() throws NegativeAmountException {
		conta.credit(100);
		assertEquals(conta.getBalance(), 100,0);
	}
	
	@Test (expected = NegativeAmountException.class )
	public void test_creditNegativo() throws NegativeAmountException {
		conta.credit(-10);
		assertEquals(0,conta.getBalance(),0);
	}

}



	