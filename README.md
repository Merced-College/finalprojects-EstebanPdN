# Restaurant Ordering System

## Project Description

The Restaurant Ordering System is a Java application that simulates a simple restaurant ordering program. The user can view a menu, search for food items, sort menu items by price, add items to an order, remove items, clear the order, save a receipt, and send orders to a kitchen queue.

## How to Run the Program

1. Open the project in Eclipse.
2. Make sure all Java files are inside the `restaurant` package.
3. Make sure `menu.csv` is in the main project folder.
4. Run `Main.java`.

The program should open a GUI where the user can interact with the system.

## Features

- Display a restaurant menu from a CSV file
- Search for menu items by name
- Show all menu items after searching
- Sort menu items by price
- Add items to the current order
- Track item quantities
- Remove items from the order
- Undo the last added item
- Clear the current order
- Calculate and display the total price
- Count the total number of items
- Save a receipt to a text file
- Send orders to a kitchen queue
- Process the next order in the kitchen queue

## Data Structures Used

### ArrayList

The program uses `ArrayList` to store the menu items loaded from the CSV file. It is also used when displaying and working with order items.

### LinkedHashMap

The program uses `LinkedHashMap` to store items in the current order with their quantities. This helps the program avoid showing the same item many times. Instead, it can show something like `Burger x3`.

### Stack

The program uses a `Stack` for the undo feature. When the user adds an item, that item is pushed onto the stack. If the user clicks “Undo Last Add,” the program removes the most recently added item.

### Queue

The program uses a `Queue` to simulate a kitchen order line. That way the user can send multiple orders to the kitchen and processed in the order they were received.

### Strings

The program uses `String` values for item names, search input, receipt text, and file reading.

## Algorithms Used

### Search Algorithm

The search algorithm checks each menu item and compares the item name with the user’s search text. If the item name contains the search text, it is added to the search results.

Time Complexity: O(n)

### Selection Sort Algorithm

The selection sort algorithm sorts the menu items from lowest price to highest price. It repeatedly finds the lowest-priced item and places it in the correct position.

Time Complexity: O(n²)

### Total Calculation Algorithm

The total calculation algorithm adds the subtotal of each item in the order. It uses a recursive method to go through the order items and return the final total.

Time Complexity: O(n)

### Recursive Item Count Algorithm

The recursive item count algorithm counts the total number of food items in the order, including quantities.

Time Complexity: O(n)

### Receipt Saving Algorithm

The receipt saving algorithm creates receipt text from the current order and saves it into a text file.

Time Complexity: O(n)

## Java Classes

### Main.java

Starts the program by opening the graphical user interface.

### Item.java

Represents a single menu item with a name and price.

### OrderItem.java

Represents an item inside an order, including its quantity.

### Order.java

Manages the current order, item quantities, total calculation, recursive item counting, and undo logic.

### MenuLoader.java

Loads menu items from the `menu.csv` file.

### ReceiptWriter.java

Saves the receipt into a text file.

### RestaurantOrderingGUI.java

Creates the graphical interface and connects the buttons to the program logic.

## Files Used

### menu.csv

Stores the restaurant menu items and prices.

### receipt.txt

Created when the user saves a receipt.

## Contributor

Esteban Pereira das Neves Siburo