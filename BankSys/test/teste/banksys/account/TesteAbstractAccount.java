package teste.banksys.account;

import static org.junit.Assert.*;

import org.junit.Test;
import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.exception.NegativeAmountException;

public class TesteAbstractAccount {
	
	AbstractAccount conta = new OrdinaryAccount("123");

	@Test
	public void test_credit() throws NegativeAmountException {
		conta.credit(100);
		assertEquals(100,conta.getBalance(),0);
	}

}
