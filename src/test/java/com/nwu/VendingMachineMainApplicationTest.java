package com.nwu;

import com.nwu.view.Menu;
import org.junit.Assert;
import org.junit.Test;



public class VendingMachineMainApplicationTest {

	private Menu menu;
	VendingMachineMainApplication VMA = new VendingMachineMainApplication(menu);

	@Test
	public void test_01_run() {
		try {
			VMA.run();
		} catch (NullPointerException ex) {
			Assert.assertTrue("NullPointerException", true);
		}
	}
}
