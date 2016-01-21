package banksys.atm;

import java.util.Scanner;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.TaxAccount;
import banksys.control.AccountController;
import banksys.control.BankController;
import banksys.control.exception.BankTransactionException;
import banksys.persistence.AccountVector;

public class ATM24H {

	private static Scanner scanner = new Scanner(System.in);
	
	static BankController bank = new BankController(new AccountVector());
	static AccountController accountControl = new AccountController(bank.getRepository());
	static ATM24H atm24h = new ATM24H();
	
	private int mainMenu() {
		System.out.println("================================");
		System.out.println("Wellcome to the Our Bank");
		System.out.println("Automated Teller Machine");
		System.out.println("================================");
		System.out.println(" [1] Add New OrdinaryAccount");
		System.out.println(" [2] Do Credit");
		System.out.println(" [3] Do Debit");
		System.out.println(" [4] Do Transfer");
		System.out.println(" [5] Show Balance");
		System.out.println(" [6] Remove OrdinaryAccount");
		System.out.println(" [7] Earn Iterest");
		System.out.println(" [8] Earn Bonus");
		System.out.println(" [9] Exit");
		System.out.println("================================");
		System.out.println("Enter the desired option: ");
		return scanner.nextInt();

	}

	private int addAccountMenu() {
		System.out.println("================================");
		System.out.println("Add New OrdinaryAccount");
		System.out.println("================================");
		System.out.println(" [1] Ordinary");
		System.out.println(" [2] Special");
		System.out.println(" [3] Savings");
		System.out.println(" [4] Tax");
		System.out.println("================================");
		System.out.println("Enter the desired option: ");
		return scanner.nextInt();
	}
	
	public  boolean verifica_existencia_da_conta(AbstractAccount account){
		boolean verificador;
		if (account != null) {
			verificador= true; 
		}else 
			verificador = false;
		return verificador ;
	}
	
	public AbstractAccount menu_atribuirAccount(AbstractAccount account){
		switch (atm24h.addAccountMenu()) {
			case 1:
				System.out.println("Enter the ordinary account number: ");
				account = new OrdinaryAccount(scanner.next());
				break;
			case 2:
				System.out.println("Enter the special account number: ");
				account = new SpecialAccount(scanner.next());
				break;
			case 3:
				System.out.println("Enter the saving account number: ");
				account = new SavingsAccount(scanner.next());
				break;
			case 4:
				System.out.println("Enter the tax account number: ");
				account = new TaxAccount(scanner.next());
				break;

			default:
				System.out.println("Invalid option!!!!");
				break;
			}
		return	account;
	}
	
	public void menu_Credit( String number, double amount){
		try {
			accountControl.doCredit(number,amount);
			System.out.println("Operation was successful!");
		} catch (BankTransactionException bte) {
			System.out.println("Error: " + bte.getMessage());
		}
	}
	
	public  void menu_Debit(String number, double amount){
		try {
			accountControl.doDebit(number, amount);
			System.out.println("Operation was successful!");
		} catch (BankTransactionException bte) {
			System.out.println("Error: " + bte.getMessage());
		}
	}
	
	public void menu_transfer(String fromNumber, String toNumber, double amount){
		try {
			accountControl.doTransfer(fromNumber, toNumber, amount);
			System.out.println("Operation was successful!");
		} catch (BankTransactionException bte) {
			System.out.println("Error: " + bte.getMessage());
		}
	}
	
	public void menu_addAccount (AbstractAccount account){
		try {
			bank.addAccount(account);
			System.out.println("Operation was successful!");
		} catch (BankTransactionException bte) {
			System.out.println("Error: " + bte.getMessage());
		}
	}
	
	public void menu_remove(String number ){
		try {
			bank.removeAccount(number);
			System.out.println("Operation was successful!");
		}catch (BankTransactionException bte) {
			System.out.println("Error: " + bte.getMessage());
		}
	}
	
	public void menu_EarnInterest(String number){
		try {
			accountControl.doEarnInterest(number);
			System.out.println("Operation was successful!");
		} catch (BankTransactionException bte) {
			System.out.println("Error: " + bte.getMessage());
		}
	}
	
	public void menu_EarnBonus (String number){
		try {
			accountControl.doEarnBonus(number);
			System.out.println("Operation was successful!");
		} catch (BankTransactionException bte) {
			System.out.println("Error: " + bte.getMessage());
		}
	}
	
	public void menu_showBalance(String number){
		try {
			System.out.println("OrdinaryAccount number: " + number);
			System.out.println("Balance: " + accountControl.getBalance(number));
		} catch (BankTransactionException bte) {
			System.out.println("Error: " + bte.getMessage());
		}
	}	
	
	public static void main(String[] args) {
		boolean loop = true;
		while (loop) {
			switch (atm24h.mainMenu()) {
			case 1:
				AbstractAccount account = null;
				account = atm24h.menu_atribuirAccount(account);
				if (atm24h.verifica_existencia_da_conta(account));
					atm24h.menu_addAccount(account);
				break;
			case 2:
				System.out.println("Enter the account number: ");
				String number = scanner.next();
				System.out.println("Enter the amount to be credited: ");
				double amount = scanner.nextDouble();
				atm24h.menu_Credit(number, amount);
				break;		
			case 3:
				System.out.println("Enter the account number: ");
				number = scanner.next();
				System.out.println("Enter the amount to be debited: ");
				amount = scanner.nextDouble();
				atm24h.menu_Debit(number, amount);
				break;
			case 4:
				System.out.println("Enter the origin account number: ");
				String fromNumber = scanner.next();
				System.out.println("Enter the destination account number: ");
				String toNumber = scanner.next();
				System.out.println("Enter the amount to be transferred: ");
				amount = scanner.nextDouble();
				atm24h.menu_transfer(fromNumber, toNumber, amount);
				break;
			case 5:
				System.out.println("Enter the account number: ");
				number = scanner.next();
				atm24h.menu_showBalance(number);
				break;
			case 6:
				System.out.println("Enter the account number: ");
				number = scanner.next();
				atm24h.menu_remove(number);
				break;
			case 7:
				System.out.println("Enter the account number: ");
				number = scanner.next();
				atm24h.menu_EarnInterest(number);
				break;
			case 8:
				System.out.println("Enter the account number: ");
				number = scanner.next();
				atm24h.menu_EarnBonus(number);
				break;
			case 9:
				System.out.print("Goodbye and have a nice day!!!");
				loop = false;
				break;
			default:
				break;
			}
		}
	}
}