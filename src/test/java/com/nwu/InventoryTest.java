package com.nwu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {

	Inventory inventoryTest = new Inventory();
	
	private  ByteArrayOutputStream test01showItems = new ByteArrayOutputStream();
	private  PrintStream originalOut = System.out;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(test01showItems));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}
	
	@Test
	public void test_01_showItems() {
		inventoryTest.getListFromFile();
		inventoryTest.setInventoryMap();
		inventoryTest.showItems();
		Assert.assertEquals("\r\n" + 
				"A1) Simba Chips R3.05 Quantity: 5\r\n" +
				"A2) Lays Chips R1.45 Quantity: 5\r\n" +
				"A3) Big Corn Bites R2.75 Quantity: 5\r\n" +
				"A4) Nik Naks R3.65 Quantity: 5\r\n" +
				"B1) Sparkles R1.80 Quantity: 5\r\n" +
				"B2) Jelly Babies R1.50 Quantity: 5\r\n" +
				"B3) Smarties R1.50 Quantity: 5\r\n" +
				"B4) Jelly Tots R1.75 Quantity: 5\r\n" +
				"C1) Coca Cola R1.25 Quantity: 5\r\n" +
				"C2) Fanta R1.50 Quantity: 5\r\n" +
				"C3) Sprite R1.50 Quantity: 5\r\n" +
				"C4) Pepsi R1.50 Quantity: 5\r\n" +
				"D1) Stimorol R0.85 Quantity: 5\r\n" +
				"D2) Beeches R0.95 Quantity: 5\r\n" +
				"D3) Chappies R0.75 Quantity: 5\r\n" +
				"D4) Orbit R0.75 Quantity: 5\r\n" +
				"", test01showItems.toString());
	}
	
	@Test
	public void test_02_getMap() {
		inventoryTest.getListFromFile();
		inventoryTest.setInventoryMap();
		inventoryTest.getCurrentInventory();
		Assert.assertEquals(inventoryTest.setInventoryMap(), inventoryTest.getCurrentInventory());
	}
}