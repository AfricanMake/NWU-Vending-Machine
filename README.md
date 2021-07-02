Vending Machine

* Developed a command line vending machine application using File I/O and OOP principles.
* Includes delimited input file processing, log functionality, inventory and credit system. 
* Completed bonus option that generated sales report using PrintWriter class.
* Wrote 24 JUnit tests to test all methods written. (src/test/java directory)
* Used an abstract class to act as a template for the 4 kinds of vending machine items. (Items.java)

User can:

* List all products and their quantity. (Inventory.java)
* Add money into the machine to buy items. (MoneySlot.java)
* See their current balance continually while adding money and purchasing items.
* Buy items until they are sold out.
* Receive their change in the smallest amount of coins. 
* Generate a sales report that lists items that were bought, how many, and the total sales amount. (ShoppingCart.java)

The program also:

* "Stocks" the vending machine by instantiating objects with a method that takes a pipe delimited CSV file and reads it with the Scanner class. (Inventory.java)
* Logs each time the user adds money, buys an item, or requests change time stamped with SimpleDateFormat class. (Logger.java)
* Logger creates a log.txt file with PrintWriter class that gets overridden each time the program runs (as per requirement).
* The sales report is uniquely named with SimpleDateFormat and persists even after program is started again. (ShoppingCart.java)
