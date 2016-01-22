package teste.banksys.persistence;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.persistence.AccountVector;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountVectorTest {

	AccountVector accountVector;
	@Before
	public void setUp() throws Exception {
		accountVector = new AccountVector();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=AccountNotFoundException.class)
	public void testDelete() throws AccountCreationException, AccountNotFoundException, AccountDeletionException {
		OrdinaryAccount account1_ = new OrdinaryAccount("123");
		OrdinaryAccount account2_ = new OrdinaryAccount("321");
		accountVector.create(account1_);
		accountVector.create(account2_);
		accountVector.delete("123");
		accountVector.retrieve("123");
	}
	
	@Test(expected=AccountDeletionException.class)
	public void testDeleteNotFound() throws AccountDeletionException {
		accountVector.delete("123");
	}

	@Test
	public void testCreate() throws AccountCreationException, AccountNotFoundException {
		OrdinaryAccount account1_ = new OrdinaryAccount("123");
		accountVector.create(account1_);
		assertEquals(true, account1_.equals(accountVector.retrieve("123")));
	}
	
	@Test(expected=AccountCreationException.class)
	public void testCreateAlreadyExists() throws AccountCreationException, AccountNotFoundException {
		OrdinaryAccount account1_ = new OrdinaryAccount("123");
		accountVector.create(account1_);
		accountVector.create(account1_);
	}

	@Test
	public void testMumberOfAccounts() throws AccountCreationException {
		OrdinaryAccount account1_ = new OrdinaryAccount("123");
		OrdinaryAccount account2_ = new OrdinaryAccount("321");
		accountVector.create(account1_);
		accountVector.create(account2_);
		assertEquals(2, accountVector.mumberOfAccounts());
	}
	
	@Test
	public void testMumberOfAccountsEmpty(){
		assertEquals(0, accountVector.mumberOfAccounts());
	}

	@Test(expected=AccountNotFoundException.class)
	public void testRetrieveNotFound() throws AccountNotFoundException {
		@SuppressWarnings("unused")
		AbstractAccount accountFound = accountVector.retrieve("123");
	}
	
	@Test
	public void testRetrieve() throws AccountNotFoundException, AccountCreationException {
		OrdinaryAccount account1_ = new OrdinaryAccount("123");
		accountVector.create(account1_);
		assertEquals(true, account1_.equals(accountVector.retrieve("123")));
	}

}
