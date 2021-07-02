package com.nwu;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class ShoppingCartTest {

	@Test
	public void test_01_buyItem() {
		ShoppingCart cartTest = new ShoppingCart();
		List<String> testList = new ArrayList<>();
		testList.add("Simba Chips");
		testList.add("Jelly Tots");
		
		cartTest.boughtItems("Simba Chips");
		cartTest.boughtItems("Jelly Tots");
		Assert.assertEquals(testList, cartTest.getBoughtList());
	}
	
	@Test
	public void test_02_addToTotalSales() {
		ShoppingCart cartTest = new ShoppingCart();
		cartTest.addToTotalSales(BigDecimal.ONE);
		cartTest.addToTotalSales(BigDecimal.TEN);
		Assert.assertEquals(BigDecimal.valueOf(11.00).setScale(2), cartTest.getTotalSales());
	}

	@Test
	public void test_03_salesReport() throws FileNotFoundException {
		ShoppingCart cartTest = new ShoppingCart();
		cartTest.boughtItems("Simba Chips");
		cartTest.boughtItems("Jelly Tots");
		cartTest.boughtItems("Coca Cola");
		
		String pattern = "MM-dd-yyyy 'at' hh mm a";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateTime = simpleDateFormat.format(new Date());
		File salesFile = new File("Vending Machine Sales Report for " + dateTime + ".txt");
		String cartTestString = "";
		
		cartTest.salesReport();
		Scanner cartTestScanner = new Scanner(salesFile);
		while (cartTestScanner.hasNextLine()) {
			cartTestString += cartTestScanner.nextLine() + "\n";
		}
		cartTestScanner.close();
		Assert.assertEquals("Simba Chips|1\nJelly Tots|2\n\nTOTAL SALES: R0.00", cartTestString.trim());
	}
}
