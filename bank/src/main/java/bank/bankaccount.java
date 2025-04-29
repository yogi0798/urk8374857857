package bank;

import java.util.ArrayList;
import java.util.List;

public class bankaccount {
    private int currentBalance;
    private List<String> transactionHistory; // NEW: Transaction history list

    public bankaccount(int initialBalance) {
        this.currentBalance = initialBalance;
        this.transactionHistory = new ArrayList<>(); // Initialize the transaction history
    }

    public int deposit(int amount) {
        if (amount > 0) {
            currentBalance += amount;
            System.out.println("Deposit successful! New balance: " + currentBalance);
            transactionHistory.add("Deposited: " + amount + " | Balance: " + currentBalance); // Log transaction
        } else {
            System.out.println("Invalid deposit amount.");
        }
        return currentBalance;
    }

    public int withdraw(int withamount) {
        if (currentBalance < withamount) {
            System.out.println("Entered amount is less than your balance");
            System.out.println("Actual balance: " + currentBalance);
        } else {
            currentBalance -= withamount;
            System.out.println("Your withdrawal amount is: " + withamount);
            System.out.println("Your current amount balance: " + currentBalance);
            transactionHistory.add("Withdrew: " + withamount + " | Balance: " + currentBalance); // Log transaction
        }
        return currentBalance;
    }

    public int currentBalance(int withamount) {
        return currentBalance;
    }

    // NEW: Print all transaction history
    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String record : transactionHistory) {
            System.out.println(record);
        }
    }
}