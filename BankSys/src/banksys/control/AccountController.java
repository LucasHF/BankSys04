package banksys.control;

import banksys.account.AbstractAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;
import banksys.control.exception.BankTransactionException;
import banksys.control.exception.IncompatibleAccountException;
import banksys.persistence.IAccountRepository;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountController{
	
	IAccountRepository repository;
	AbstractAccount account;
	
	public AccountController(IAccountRepository repository) {
		this.repository = repository;
	}

	public AbstractAccount associateAccount(String number) throws BankTransactionException{
		try {
			return this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}
		
	}
	
	public void doCredit(String number, double amount) throws BankTransactionException {
		
			account = associateAccount(number);	
			
		try {
			account.credit(amount);
		} catch (NegativeAmountException nae) {
			throw new BankTransactionException(nae);
		}

	}

	public void doDebit(String number, double amount) throws BankTransactionException {
		
		account = associateAccount(number);	
		
		try {
			account.debit(amount);
		} catch (InsufficientFundsException ife) {
			throw new BankTransactionException(ife);
		} catch (NegativeAmountException nae) {
			throw new BankTransactionException(nae);
		}
	}

	public double getBalance(String number) throws BankTransactionException {
		
		account = associateAccount(number);	
		return account.getBalance();
		
	}

	public void doTransfer(String fromNumber, String toNumber, double amount) throws BankTransactionException {
		AbstractAccount fromAccount;
		fromAccount = associateAccount(fromNumber);	

		AbstractAccount toAccount;
			toAccount = associateAccount(toNumber);	;

		try {
			fromAccount.debit(amount);
			toAccount.credit(amount);
		} catch (InsufficientFundsException sie) {
			throw new BankTransactionException(sie);
		} catch (NegativeAmountException nae) {
			throw new BankTransactionException(nae);
		}
	}

	public void doEarnInterest(String number) throws BankTransactionException, IncompatibleAccountException {
		
		account = associateAccount(number);	
		
		if (account instanceof SavingsAccount) {
			((SavingsAccount) account).earnInterest();
		} else {
			throw new IncompatibleAccountException(number);
		}
	}

	public void doEarnBonus(String number) throws BankTransactionException, IncompatibleAccountException {
		
		account = associateAccount(number);	

		if (account instanceof SpecialAccount) {
			((SpecialAccount) account).earnBonus();
		} else {
			throw new IncompatibleAccountException(number);
		}
	}
}
