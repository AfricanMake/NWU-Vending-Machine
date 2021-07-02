package com.nwu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShoppingCart {

	// instance variables
	private List<String> boughtItems;
	private Map<String, Integer> salesReport;
	private String format;
	private SimpleDateFormat dateFormat;
	private String dateTime;
	private String salesPath;
	private File salesFile;
	private PrintWriter salesWriter;
	private BigDecimal totalSales;

	// constructor
	public ShoppingCart() {
		this.boughtItems = new ArrayList<>();
		this.format = "MM-dd-yyyy 'at' hh mm a";
		this.dateFormat = new SimpleDateFormat(format);
		this.dateTime = dateFormat.format(new Date());
		this.salesPath = dateTime;
		this.salesFile = new File(salesPath);
		this.totalSales = BigDecimal.valueOf(0.00).setScale(2);
	}

	// getters for tests only
	public List<String> getBoughtList() {
		return boughtItems;
	}
	
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	
	// methods
	public void boughtItems(String itemName) {
		boughtItems.add(itemName);
	}
	
	public void addToTotalSales(BigDecimal price) {
		totalSales = totalSales.add(price);
	}
 
	public void salesReport() {
		this.salesReport = new HashMap<>();
		for (String item : boughtItems) {
			Integer num = salesReport.get(item);
			if (num == null) {
				salesReport.put(item, 1);
			} else {
				salesReport.put(item, num + 1);
			}
		} try {
			this.salesWriter = new PrintWriter(new FileOutputStream("Sales Report for " + salesFile + ".txt"),true);
			for (Map.Entry<String, Integer> sales : salesReport.entrySet()) {
				salesWriter.println(sales.getKey() + "|" + sales.getValue());
			} salesWriter.println("\nTOTAL SALES: R" + totalSales.toString());
			salesWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
		} 
	}
}