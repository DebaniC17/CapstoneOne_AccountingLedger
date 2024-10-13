package com.ps;
import java.io.BufferedReader;
import java.io.FileReader;
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
}