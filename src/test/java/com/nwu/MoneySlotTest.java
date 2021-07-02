package com.nwu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneySlotTest {

	private  ByteArrayOutputStream testStream = new ByteArrayOutputStream();
	private  PrintStream originalOut = System.out;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(testStream));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}
	
	@Test
	public void test_01_displayCurrentBalance() {
		MoneySlot moneySlotTest = new MoneySlot();
		moneySlotTest.inputMoney(BigDecimal.TEN);
		moneySlotTest.showCurrentBalance();
		Assert.assertEquals("Current Money Provided: R10.00", testStream.toString().trim());
	}
	
	@Test
	public void test_02_inputMoney() {
		MoneySlot moneySlotTest = new MoneySlot();
		moneySlotTest.inputMoney(BigDecimal.ONE.setScale(2));
		Assert.assertEquals(moneySlotTest.getBalance(), BigDecimal.ONE.setScale(2));
	}
	
	@Test
	public void test_03_spendMoney() {
		MoneySlot moneySlotTest = new MoneySlot();
		moneySlotTest.inputMoney(BigDecimal.TEN.setScale(2));
		moneySlotTest.spendMoney(BigDecimal.TEN.setScale(2));
		Assert.assertEquals(moneySlotTest.getBalance(), BigDecimal.ZERO.setScale(2));
	}
	
	@Test
	public void test_04_checkBuy() {
		MoneySlot moneySlotTest = new MoneySlot();
		
		// positive value
		moneySlotTest.inputMoney(BigDecimal.TEN.setScale(2));
		moneySlotTest.checkBuy(BigDecimal.ONE.setScale(2));
		Assert.assertEquals(BigDecimal.valueOf(9).setScale(2), moneySlotTest.checkBuy(BigDecimal.ONE.setScale(2)));
		
		// negative value
		moneySlotTest.spendMoney(BigDecimal.ONE.setScale(2));
		moneySlotTest.checkBuy(BigDecimal.TEN.setScale(2));
		Assert.assertEquals(BigDecimal.valueOf(-1).setScale(2), moneySlotTest.checkBuy(BigDecimal.TEN.setScale(2)));
	}
	
	@Test
	public void test_05_giveChange0() {
		MoneySlot moneySlotTest = new MoneySlot();

		moneySlotTest.giveChange();
		Assert.assertEquals("Your change is 0 ten rand note(s), 0 rand(s), and 0 cent(s).", testStream.toString().trim());
	}
	
	@Test
	public void test_06_giveChangeAllCoinsWith140() {
		MoneySlot moneySlotTest = new MoneySlot();
		moneySlotTest.inputMoney(BigDecimal.valueOf(1.40).setScale(2));
		moneySlotTest.giveChange();
		Assert.assertEquals("Your change is 0 ten rand note(s), 1 rand(s), and 40 cent(s).", testStream.toString().trim());
	}
}
