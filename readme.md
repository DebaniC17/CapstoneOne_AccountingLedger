# Accounting Ledger Application 

## Overview 
This program is designed to manage and display transactions for the user in an accounting ledger. The program reads from a csv file which allows users to search up transactions based on specific properties and generate reports as well.

## Features
**Add Transactions:**
- Input new deposits and payments

**Search:**
- By vendor
- By description
- By amount range

**Transaction Reports:**
- Month to date 
- Previous month 
- Year to date
- Previous year

**Transaction sorting:**
- In descending order

## Project Layout
**transaction.csv**
Transactions loaded and saved to CSV file where each transaction follows the format: date|time|description|vendor|amount

**Transaction Class**
This class represents a single transaction in the ledger and encapsulates it based on the transactions properties. The class contains a constructor, getters & setters. Plus a toString, deposit and payment method. 

**Main Class**
This class includes methods to display different types of reports and search for transactions based on specific criteria.

## Menus
**Main menu**
- Input new deposits (positive amounts)
- Input payments (negative amounts)

**Ledger menu**
-

- Added search features menu

