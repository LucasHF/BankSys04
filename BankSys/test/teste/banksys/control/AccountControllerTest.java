package teste.banksys.control;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.control.AccountController;
import banksys.control.BankController;
import banksys.control.exception.BankTransactionException;
import banksys.persistence.AccountVector;

public class AccountControllerTest {
	
	BankController bankControl;
	AccountController control;
	
	@Before
	public void setUp() throws Exception {
		bankControl = new BankController(new AccountVector());
		control = new AccountController(bankControl.getRepository());
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void doCreditTest() throws BankTransactionException{
		AbstractAccount account_1 = new OrdinaryAccount("123");
		bankControl.addAccount(account_1);
		control.doCredit("123", 100);
		assertEquals(control.getBalance("123"), 100, 0);
	}
	
	@Test(expected=BankTransactionException.class)
	public void doCreditNegativeTest() throws BankTransactionException{
		AbstractAccount account_1 = new OrdinaryAccount("123");
		bankControl.addAccount(account_1);
		control.doCredit("123", -100);
	}
	
	@Test
	public void doDebitTest() throws BankTransactionException{
		AbstractAccount account_1 = new OrdinaryAccount("123");
		bankControl.addAccount(account_1);
		control.doCredit("123", 100);
		control.doDebit("123", 50);
		assertEquals(control.getBalance("123"), 50, 0);
	}
	
	@Test(expected=BankTransactionException.class)
	public void doDebitNegativeTest() throws BankTransactionException{
		AbstractAccount account_1 = new OrdinaryAccount("123");
		bankControl.addAccount(account_1);
		control.doCredit("123", 100);
		control.doDebit("123", -50);
	}
	
	@Test(expected=BankTransactionException.class)
	public void doDebitEmptyTest() throws BankTransactionException{
		AbstractAccount account_1 = new OrdinaryAccount("123");
		bankControl.addAccount(account_1);
		control.doDebit("123", 50);
		System.out.println(control.getBalance("123"));
	}
	
	@Test
	public void getBalanceTest() throws BankTransactionException{
		AbstractAccount account_1 = new OrdinaryAccount("123");
		bankControl.addAccount(account_1);
		control.doCredit("123", 100);
		assertEquals(control.getBalance("123"), 100, 0);
	}
	
	@Test
	public void doEarnInterestTest() throws BankTransactionException{
		AbstractAccount account_1 = new SavingsAccount("123");
		bankControl.addAccount(account_1);
		control.doCredit("123", 100);
		control.doEarnInterest("123");
		assertEquals(control.getBalance("123"), 100 * 1.001, 0);
	}
	
	@Test(expected=BankTransactionException.class)
	public void doEarnInterestWrongAccountTest() throws BankTransactionException{
		AbstractAccount account_1 = new OrdinaryAccount("123");
		bankControl.addAccount(account_1);
		control.doCredit("123", 100);
		control.doEarnInterest("123");
	}
	
	@Test
	public void doEarnBonusTest() throws BankTransactionException{
		AbstractAccount account_1 = new SpecialAccount("123");
		bankControl.addAccount(account_1);
		control.doCredit("123", 100);
		control.doEarnBonus("123");
		assertEquals(control.getBalance("123"), 100 * 1.01, 0);
	}
	
}