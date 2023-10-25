package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Player {
    private String username;
    private String password;
    private double balance;
    private List<Transaction> transactionHistory;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public void deposit(String transactionId, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        balance += amount;
        transactionHistory.add(new Transaction(transactionId, TransactionType.CREDIT, amount));
    }

    public void withdraw(String transactionId, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }
        if (balance < amount) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance -= amount;
        transactionHistory.add(new Transaction(transactionId, TransactionType.DEBIT, amount));
    }
}
