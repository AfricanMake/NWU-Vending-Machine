package com.nwu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BeveragesTest {

	Beverages fanta = new Beverages ("Fanta", "1.50");

	private  ByteArrayOutputStream test01dispose = new ByteArrayOutputStream();
	private  PrintStream originalOut = System.out;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(test01dispose));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	public void test_01_disposeTest() {
		fanta.dispose();
		Assert.assertEquals("You have bought: Fanta for R1.50\nGulp Gulp, Yum!", test01dispose.toString().trim());
	}

	@Test
	public void test_02_decreaseQuantityTest() {
		fanta.getQuantity();
		Assert.assertEquals("5", fanta.getQuantity());

		fanta.decreaseQuantity();
		Assert.assertEquals("4", fanta.getQuantity());

		fanta.decreaseQuantity();
		Assert.assertEquals("3", fanta.getQuantity());

		fanta.decreaseQuantity();
		Assert.assertEquals("2", fanta.getQuantity());

		fanta.decreaseQuantity();
		Assert.assertEquals("1", fanta.getQuantity());

		fanta.decreaseQuantity();
		Assert.assertEquals("Sold out", fanta.getQuantity());

		fanta.decreaseQuantity();
		Assert.assertEquals("Sold out", fanta.getQuantity());
	}
}