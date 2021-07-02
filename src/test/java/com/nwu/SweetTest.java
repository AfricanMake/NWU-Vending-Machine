package com.nwu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SweetTest{
 
	Sweets smarties = new Sweets ("Smarties", "1.50");

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
		smarties.dispose();
		Assert.assertEquals("You have bought: Smarties for R1.50\nYum Yum!", test01dispose.toString().trim());
	}

	@Test
	public void test_02_reduceQuantityTest() {
		smarties.getQuantity();
		Assert.assertEquals("5", smarties.getQuantity());

		smarties.decreaseQuantity();
		Assert.assertEquals("4", smarties.getQuantity());

		smarties.decreaseQuantity();
		Assert.assertEquals("3", smarties.getQuantity());

		smarties.decreaseQuantity();
		Assert.assertEquals("2", smarties.getQuantity());

		smarties.decreaseQuantity();
		Assert.assertEquals("1", smarties.getQuantity());

		smarties.decreaseQuantity();
		Assert.assertEquals("Sold out", smarties.getQuantity());

		smarties.decreaseQuantity();
		Assert.assertEquals("Sold out", smarties.getQuantity());
	}
}