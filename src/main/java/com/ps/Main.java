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
                mainMenuCommand = commandScanner.nextInt();
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

        System.out.println("Amount: ");
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

}