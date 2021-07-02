package com.nwu;

import com.nwu.view.Menu;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineMainApplication {
    private static final String OPTION_TO_DISPLAY_ITEMS = "Display Items";
    private static final String OPTION_TO_BUY = "Buy";
    private static final String OPTION_TO_EXIT = "Exit";
    private static final String SALES_REPORT = "SALES REPORT!";
    private static final String[] MAIN_MENU_OPTIONS = {OPTION_TO_DISPLAY_ITEMS, OPTION_TO_BUY, OPTION_TO_EXIT, SALES_REPORT};

    private static final String BUY_MENU_OPTION_INPUT_MONEY = "Input money";
    private static final String BUY_MENU_OPTION_SELECT_PRODUCT = "Select a product from the list";
    private static final String BUY_MENU_OPTION_FINISH_TRANSACTION = "Complete Transaction";
    private static final String[] BUY_MENU_OPTIONS = {BUY_MENU_OPTION_INPUT_MONEY, BUY_MENU_OPTION_SELECT_PRODUCT, BUY_MENU_OPTION_FINISH_TRANSACTION};

    private static final String ONE_RAND = "R1";
    private static final String TWO_RANDS = "R2";
    private static final String FIVE_RANDS= "R5";
    private static final String TEN_RANDS= "R10";
    private static final String INPUT_MONEY_DONE = "Finished";
    private static final String[] MENU_OPTIONS = {ONE_RAND, TWO_RANDS, FIVE_RANDS, TEN_RANDS, INPUT_MONEY_DONE};

    private Menu menu;
    public VendingMachineMainApplication(Menu menu) {
        this.menu = menu;}

    private Inventory inventory = new Inventory();
    private MoneySlot moneySlot = new MoneySlot();
    private Logger logger = new Logger();
    //private VMExceptions VME = new VMExceptions();
    private ShoppingCart shoppingCart = new ShoppingCart();

    public static void main(String[] args){

        Menu menu = new Menu(System.in, System.out);

        VendingMachineMainApplication cli = new VendingMachineMainApplication(menu);

        cli.run();}

    public void run(){
        inventory.getListFromFile();  		// Reads file and generates Inventory List
        inventory.setInventoryMap(); 		// Stocks machine at startup
        System.out.println("Welcome to North West University Vending Machine!");

        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(OPTION_TO_DISPLAY_ITEMS)) {
                /* Displays items */
                inventory.showItems();
            }
            else if (choice.equals(OPTION_TO_BUY))
            {
                boolean purchase = true;
                while (purchase) {
                    /* Displays balance */
                    moneySlot.showCurrentBalance();
                    String choicePurchase = (String) menu.getChoiceFromOptions(BUY_MENU_OPTIONS);
                    if (choicePurchase.equals(BUY_MENU_OPTION_INPUT_MONEY)) {
                        boolean input = true;
                        while (input) {
                            moneySlot.showCurrentBalance();
                            String choiceInputMoney = (String) menu.getChoiceFromOptions(MENU_OPTIONS);

                            /*  Input Money	*/
                            if (choiceInputMoney.equals(ONE_RAND))
                            {
                                logger.log("Input some money:", moneySlot.getBalance().toString(), moneySlot.getBalance().add(BigDecimal.valueOf(1.00)).toString());
                                moneySlot.inputMoney(BigDecimal.valueOf(1.00));
                            } else if (choiceInputMoney.equals(TWO_RANDS)) {
                                logger.log("Input some money:", moneySlot.getBalance().toString(), moneySlot.getBalance().add(BigDecimal.valueOf(2.00)).toString());
                                moneySlot.inputMoney(BigDecimal.valueOf(2.00));
                            } else if (choiceInputMoney.equals(FIVE_RANDS)) {
                                logger.log("Input some money:", moneySlot.getBalance().toString(), moneySlot.getBalance().add(BigDecimal.valueOf(5.00)).toString());
                                moneySlot.inputMoney(BigDecimal.valueOf(5.00));
                            } else if (choiceInputMoney.equals(TEN_RANDS)) {
                                logger.log("Input some money:", moneySlot.getBalance().toString(), moneySlot.getBalance().add(BigDecimal.valueOf(10.00)).toString());
                                moneySlot.inputMoney(BigDecimal.valueOf(10.00));
                            } else if (choiceInputMoney.equals(INPUT_MONEY_DONE)) {
                                input = false; }
                        }
                    } else if (choicePurchase.equals(BUY_MENU_OPTION_SELECT_PRODUCT)) {
                        /* Displays items for purchase	*/
                        inventory.showItems();
                        moneySlot.showCurrentBalance();
                        Scanner purchaseScanner = new Scanner(System.in);
                        System.out.print("Please choose a slot >>> ");
                        String slotString = purchaseScanner.nextLine();
                        boolean validSlot = false;
                        for (Map.Entry<String, Items> entry : inventory.getCurrentInventory().entrySet()) {
                            /*  Checks input  */					if ((slotString.equalsIgnoreCase(entry.getKey()))) {
                                validSlot = true;
                            }
                        }
                        if (validSlot) {
                            for (Map.Entry<String, Items> entry : inventory.getCurrentInventory().entrySet()) {

                                if (slotString.equalsIgnoreCase(entry.getKey())) {
                                    /*  Checks if SOLD OUT  */
                                    if (entry.getValue().getQuantity().equals("SOLD OUT")) {
                                        System.out.println("\n" + "Sorry, we are SOLD OUT of that item!");
                                    } else {
                                        BigDecimal currentPriceFromString = new BigDecimal(entry.getValue().getPrice());
                                        /*  Checks for sufficient funds  */
                                        if (moneySlot.checkBuy(currentPriceFromString).compareTo(BigDecimal.ZERO) < 0 ) {
                                            System.out.println("\n" + "Sorry, you do not have enough money! Input some money!");
                                        } else {
                                            /*  Sells item and updates all  */
                                            logger.log(entry.getValue().getName() + " " + slotString.toUpperCase(), moneySlot.getBalance().toString(), moneySlot.getBalance().subtract(currentPriceFromString).toString());
                                            moneySlot.spendMoney(currentPriceFromString);
                                            shoppingCart.boughtItems(entry.getValue().getName());
                                            shoppingCart.addToTotalSales(currentPriceFromString);
                                            entry.getValue().dispose();
                                            entry.getValue().decreaseQuantity();
                                        }
                                    }
                                }
                            }
                        }

                        if (!validSlot)
                        {
                            System.out.println("\n" + "*** " + slotString + " is not a valid option ***");
                        }
                        /*  Gives  */
                        }
                        else if (choicePurchase.equals(BUY_MENU_OPTION_FINISH_TRANSACTION))
                        {
                        /*  Change */
                            System.out.println("\n" + "Calculating change for R" + ((moneySlot.getBalance())).setScale(2) + " ..."+"\n");

                        logger.log("Change given:", moneySlot.getBalance().toString(), "0.00");
                        moneySlot.giveChange();
                        moneySlot.showCurrentBalance();
                        purchase = false;
                        }
                }
            }
            else if (choice.equals(OPTION_TO_EXIT))
            {
                System.exit(1);
            }
            else if (choice.equals(SALES_REPORT))
            {
                shoppingCart.salesReport();
            }
        }
    }

}
