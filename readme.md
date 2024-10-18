# Accounting Ledger Application 

## Overview 
This program is designed to manage and display transactions for the user in an accounting ledger. The program reads from a csv file which allows users to search up transactions based on specific properties and generate reports as well.

## Features

**Search:**
- By description
- By amount range

**Transaction sorting:**
- In descending order

## Project Layout
**transaction.csv**
Transactions loaded and saved to CSV file where each transaction follows the format: date|time|description|vendor|amount 

**Transaction Class**
This class represents a single transaction in the ledger and encapsulates it based on the transactions properties like date, time, description, vendor and amount. 
- The class contains a constructor
- Getters & setters
- A toString() method for displaying transaction information
- A deposit() and payment() method to distinguish between deposits and payments

**Main Class**
This class contains all the core functionality of the program, including:
- Methods to add new deposits and payments
- Sorting transactions in descending order by date and time
- Generating various types of reports
- Searching transactions by description, vendor, or amount range

## Menus
### **Main Menu**
The user can:
- Input new deposits (positive amounts)
- Input new payments (negative amounts)
- View and search transactions

### **Ledger Menu**
The user can:
- Display all transactions
- Display deposits only
- Display payments only

### **Reports Menu**
The user can view transactions based on the following criteria:
- **Month to date**: Shows all transactions for the current month
- **Previous Month**: Shows transactions from the previous month
- **Year to date**: Displays all transactions for the current year
- **Previous Year**: Displays transactions from the previous year
- **Search by vendor**: Finds transactions from a specific vendor

### **Added Search Features Menu**
The user can:
- **Search by description**: Find transactions by their description
- **Search by amount range**: Find transactions within a specific range of amounts

# Screenshots

## Main menu
![Main menu](images%2FMain%20menu.png)

## Ledger menu
![Ledger menu](images%2FLedger%20menu.png)

## Reports menu
![Reports menu](images%2FReports%20menu.png)

## Added search features
![Added search features menu](images%2FAdded%20search%20features%20menu.png)

## Deposit submenu
![Deposit submenu](images%2FDeposit%20submenu.png)

## Payment submenu
![Payment submenu](images%2FPayment%20submenu.png)

## Resources

Here are some helpful resources that I used for to sort in descending order and for date/time:

- [Sorting Objects in ArrayList by Date](https://www.geeksforgeeks.org/java-program-to-sort-objects-in-arraylist-by-date/) - Used for sorting transactions by date and time in descending order.
- [Java Lambdas](https://www.w3schools.com/java/java_lambda.asp) - Reference for using lambda expressions in Java.
- [Java Advanced Sorting](https://www.w3schools.com/java/java_advanced_sorting.asp) - Additional information on sorting arrays and lists in Java.
- [Sorting Date and Time Values](https://stackoverflow.com/questions/39699787/sort-date-and-time-values) - How to sort objects by date and time.
- [Get Previous Month and Year in Java](https://stackoverflow.com/questions/12814504/how-to-get-last-month-year-in-java) - Used for retrieving the previous month’s transactions.

## Interesting piece of code

![Collection.sort()](images%2FInteresting%20piece%20of%20code.png)

Finding out how to sort the transactions by date and time in descending order was quite the challenge for me, but i learned a lot about different ways to sort objects.
- Lambda Parameters -> Lambda Body
- The collection.sort() method is used to sort the elements present in the specified list of collections in ascending order. 
- The .compareTo() method is used to compare two objects to determine their order. Which is what allowed me to have my outputs in descending order. 

## A different approach for descending order
![Failed attempt #1](images%2Fdesc%20order%20pt%203%20w%20compareTo.png)

The .isAfter() and .isBefore() are methods used to compare two date or time object. Sadly every which way I played with the methods it stayed in ascending order.  
