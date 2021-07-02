package com.nwu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BubbleGumTest {

	BubbleGum stimorol = new BubbleGum ("Stimorol", "0.85");

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
		stimorol.dispose();
		Assert.assertEquals("You have bought: Stimorol for R0.85\nChew Chew, Yum!", test01dispose.toString().trim());
	}

	@Test
	public void test_02_decreaseQuantityTest() {
		stimorol.getQuantity();
		Assert.assertEquals("5", stimorol.getQuantity());

		stimorol.decreaseQuantity();
		Assert.assertEquals("4", stimorol.getQuantity());

		stimorol.decreaseQuantity();
		Assert.assertEquals("3", stimorol.getQuantity());

		stimorol.decreaseQuantity();
		Assert.assertEquals("2", stimorol.getQuantity());

		stimorol.decreaseQuantity();
		Assert.assertEquals("1", stimorol.getQuantity());

		stimorol.decreaseQuantity();
		Assert.assertEquals("Sold out", stimorol.getQuantity());

		stimorol.decreaseQuantity();
		Assert.assertEquals("Sold out", stimorol.getQuantity());
	}
}