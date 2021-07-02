package com.nwu;

import java.math.BigDecimal;

public class MoneySlot {

	//instance Variable
	private BigDecimal balance;

	//constructor
	public MoneySlot() {
		this.balance = BigDecimal.valueOf(0.00).setScale(2);
	}

	//getter

	public BigDecimal getBalance() {
		return balance;
	}

	//methods

	public void showCurrentBalance() {
		System.out.println("\n" + "Current Money Provided: R" + ((getBalance())).setScale(2));
	}

	public void inputMoney(BigDecimal money) {
		balance = balance.add(money);
	}

	public void spendMoney(BigDecimal money) {
		balance = balance.subtract(money);
	}

	public BigDecimal checkBuy(BigDecimal money) {
		BigDecimal checkingMoney = getBalance().subtract(money);
		return checkingMoney;
	}

	public void giveChange() {

		double currentBalance = getBalance().doubleValue();

		int numOfCents = 0;
		int numOfRands = 0;
		int numOfNotes = 0;

		while (currentBalance > 0) {
			if (currentBalance >= 10) {
				numOfNotes++;
				currentBalance -= 10;
			} else if (currentBalance < 10 && currentBalance >= 1) {
				numOfRands++;
				currentBalance -= 1;
			} else if (currentBalance < 1 && currentBalance > 0) {
				numOfCents++;
				currentBalance -= 0.01;
			}
		}

System.out.println( "Your change is " + numOfNotes + " ten rand note(s), " +
			numOfRands + " rand(s), and " + numOfCents + " cent(s).");


			//this.balance = BigDecimal.valueOf(0.00).setScale(2);
		}
	}

