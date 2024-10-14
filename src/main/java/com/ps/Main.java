package com.ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Transaction> allTransactions = new ArrayList<>();

    public static void main(String[] args) {
        loadTransactionsFromFile();

        int mainMenuCommand;

        do {
            System.out.println("Welcome to Accounting Ledger Application");
            System.out.println("Please enter your command: ");
            System.out.println("1) To Add Deposit");
            System.out.println("2) To Make Payment (Deposit)");
            System.out.println("3) Display Ledger Screen");
            System.out.println("0) To Exit Application");
            System.out.println("Command: ");

            try {
                mainMenuCommand = scanner.nextInt();
            } catch (InputMismatchException ime) {
                mainMenuCommand = 0;
            }

            switch (mainMenuCommand) {
                case 1:
                    addDeposit();
                    break;

                case 2:
                    makePayment();
                    break;

                case 3:
                    displayLedger();
                    break;

                case 0:
                    System.out.println("Exiting...");

                default:
                    System.out.println("Command not found, please try again");
            }

        } while (mainMenuCommand != 0);

    }

    public static void loadTransactionsFromFile() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

            String firstLine = bufferedReader.readLine();
            String input;

            while ((input = bufferedReader.readLine()) != null) {
                String[] transactionsArr = input.split("\\|");
                String date = transactionsArr[0];
                String time = transactionsArr[1];
                String description = transactionsArr[2];
                String vendor = transactionsArr[3];
                float amount = Float.parseFloat(transactionsArr[4]);

                allTransactions.add(new Transaction(date, time, description, vendor, amount));

            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDeposit() {

        System.out.println("Command for adding a deposit");
        //add code
        System.out.println("Please enter the details of the deposit...");

        System.out.print("Date: ");
        String date = scanner.nextLine();

        System.out.print("Time: ");
        String time = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Name: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        int amount = scanner.nextInt();

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        allTransactions.add(transaction);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions,csv", true));
            bufferedWriter.write(String.format("\n%s|%s|%s|%s|%f",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount()
            ));

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void makePayment() {

        System.out.println("Command for making a payment");
        //add code
        System.out.println("Please enter the details of the payment...");

        System.out.print("Date: ");
        String date = scanner.nextLine();

        System.out.print("Time: ");
        String time = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Name: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        int amount = scanner.nextInt();

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        allTransactions.add(transaction);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions,csv", true));
            bufferedWriter.write(String.format("\n%s|%s|%s|%s|%f",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount()
            ));

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayLedger() {

        int subMenuCommand;

        do {
            System.out.println("Please enter your command: ");
            System.out.println("1) Display all entries");
            System.out.println("2) Display deposits");
            System.out.println("3) Display payments");
            System.out.println("4) Display reports screen");
            System.out.println("0) Go back to home page");
            System.out.println("Command: ");

            subMenuCommand = scanner.nextInt();

            switch (subMenuCommand) {
                case 1:
                    allTransactions();
                    break;

                case 2:
                    allDeposits();
                    break;

                case 3:
                    allPayments();
                    break;

                case 4:
                    displayReports();
                    break;

                case 0:
                    System.out.println("Going back to home page...");
                    break;

                default:
                    System.out.println("Command not found, please try again");


            }

        } while (subMenuCommand != 0);

    }

    public static void allTransactions() {
        System.out.println("Display all Entries");
        for (int i = 0; i < allTransactions.size(); i++) {
            System.out.println(allTransactions.get(i));
        }
    }

    public static void allDeposits() {
        System.out.println("Display all deposits");
        for (int i = 0; i< allTransactions.size(); i++) {
            Transaction allDeposit = allTransactions.get(i);
            if (Transaction.deposit()) {
                System.out.println(allDeposit);
            }
        }
    }

    public static void allPayments() {
        System.out.println("Display all payments");
        for (int i = 0; i< allTransactions.size(); i++) {
            Transaction allPayments = allTransactions.get(i);
            if (Transaction.payment()) {
                System.out.println(allPayments);
            }
        }
    }

    public static void displayReports() {

        int subMenuCommand;

        do {
            System.out.println("Please enter your Command");
            System.out.println("1) Month to date");
            System.out.println("2) Previous month");
            System.out.println("3) Year to date");
            System.out.println("4) Previous year");
            System.out.println("5) Search by vendor");
            System.out.println("0) Go back to home page");
            System.out.println("Command: ");

            subMenuCommand = scanner.nextInt();

            switch (subMenuCommand) {
                case 1:
                    displayMonthToDate();
                    break;

                case 2:
                    displayPreviousMonth();
                    break;

                case 3:
                    displayYearToDate();
                    break;

                case 4:
                    displayPreviousYear();
                    break;

                case 5:
                    searchByVendor();
                    break;

                case 0:
                    System.out.println("Going back to home page...");

                default:
                    System.out.println("Command not found, please try again");
            }
        } while (subMenuCommand != 0);
    }
}