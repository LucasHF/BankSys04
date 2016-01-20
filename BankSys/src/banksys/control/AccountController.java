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

	public void doCredit(String number, double amount) throws BankTransactionException {
		
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}
		try {
			account.credit(amount);
		} catch (NegativeAmountException nae) {
			throw new BankTransactionException(nae);
		}

	}

	public void doDebit(String number, double amount) throws BankTransactionException {
		
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}
		try {
			account.debit(amount);
		} catch (InsufficientFundsException ife) {
			throw new BankTransactionException(ife);
		} catch (NegativeAmountException nae) {
			throw new BankTransactionException(nae);
		}
	}

	public double getBalance(String number) throws BankTransactionException {
		
		try {
			account = this.repository.retrieve(number);
			return account.getBalance();
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

	}

	public void doTransfer(String fromNumber, String toNumber, double amount) throws BankTransactionException {
		AbstractAccount fromAccount;
		try {
			fromAccount = this.repository.retrieve(fromNumber);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

		AbstractAccount toAccount;
		try {
			toAccount = this.repository.retrieve(toNumber);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

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
		
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

		if (account instanceof SavingsAccount) {
			((SavingsAccount) account).earnInterest();
		} else {
			throw new IncompatibleAccountException(number);
		}
	}

	public void doEarnBonus(String number) throws BankTransactionException, IncompatibleAccountException {
		
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

		if (account instanceof SpecialAccount) {
			((SpecialAccount) account).earnBonus();
		} else {
			throw new IncompatibleAccountException(number);
		}
	}
}
