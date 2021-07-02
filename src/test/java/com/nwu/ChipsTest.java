package com.nwu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChipsTest {

	Chips simbaChips = new Chips ("SimbaChips", "3.05");

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
		simbaChips.dispose();
		Assert.assertEquals("You have bought: SimbaChips for R3.05\nMunch Munch, Yum!", test01dispose.toString().trim());
	}

	@Test
	public void test_02_reduceQuantityTest() {
		simbaChips.getQuantity();
		Assert.assertEquals("5", simbaChips.getQuantity());

		simbaChips.decreaseQuantity();
		Assert.assertEquals("4", simbaChips.getQuantity());

		simbaChips.decreaseQuantity();
		Assert.assertEquals("3", simbaChips.getQuantity());

		simbaChips.decreaseQuantity();
		Assert.assertEquals("2", simbaChips.getQuantity());

		simbaChips.decreaseQuantity();
		Assert.assertEquals("1", simbaChips.getQuantity());

		simbaChips.decreaseQuantity();
		Assert.assertEquals("Sold out", simbaChips.getQuantity());

		simbaChips.decreaseQuantity();
		Assert.assertEquals("Sold out", simbaChips.getQuantity());
	}
}