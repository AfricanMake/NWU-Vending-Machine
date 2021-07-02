package com.nwu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Inventory {

	// instance variables
	private String filePath;
	private File inventoryFile;
	private List<String[]> inventoryListFromFile;
	private Map<String, Items> inventoryMap;

	//constructor
	public Inventory() {

		this.filePath = "inventory.csv";
		this.inventoryFile = new File(filePath);
		this.inventoryListFromFile = new ArrayList<>();
		this.inventoryMap = new LinkedHashMap<>();
	}

	//Getters and Setters
	
	//Getter that uses csv file to generate String[]s into a List
	public List<String[]> getListFromFile() {
		try (Scanner fileScanner = new Scanner(inventoryFile)) {
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] lineArray = line.split("\\|");
				inventoryListFromFile.add(lineArray);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (Exception ex) {
			System.out.println("An error has occurred!");
			ex.printStackTrace();
		} return inventoryListFromFile;
	}
  
	// Setter that populates Map where key=slot and value=each item
	public Map<String, Items> setInventoryMap() {

		for (String[] theArray : inventoryListFromFile) {
			if (theArray[3].equalsIgnoreCase("Sweets"))
			{
				Sweets theseSweets = new Sweets(theArray[1], theArray[2]);
				inventoryMap.put(theArray[0], theseSweets);

			}
			else if (theArray[3].equalsIgnoreCase("Chips"))
			{
				Chips theseChips = new Chips(theArray[1], theArray[2]);
				inventoryMap.put(theArray[0], theseChips);
			}
			else if (theArray[3].equalsIgnoreCase("Beverage"))
			{
				Beverages theseBeverages = new Beverages(theArray[1], theArray[2]);
				inventoryMap.put(theArray[0], theseBeverages);
			}
			else if (theArray[3].equalsIgnoreCase("BubbleGum"))
			{
				BubbleGum theseBubbleGums = new BubbleGum(theArray[1], theArray[2]);
				inventoryMap.put(theArray[0], theseBubbleGums);
			}
		}

		return inventoryMap;
	}

	// Getter that allows access to current inventory
	public Map<String, Items> getCurrentInventory(){
		return inventoryMap;
	}
	
	// Prints current inventory to console 
	public void showItems() {
		Set<String> keys = inventoryMap.keySet();
		System.out.println();
		for (String key : keys) {
			System.out.println(key.toString() + ") " + inventoryMap.get(key).getName() + " R"
			+ inventoryMap.get(key).getPrice() + " Quantity: " + String.valueOf(inventoryMap.get(key).getQuantity()));
		} 
	}
}
