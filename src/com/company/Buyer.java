package com.company;

import java.util.ArrayList;
import java.util.HashMap;


public class Buyer {
    //поля
    private int balance;
    private HashMap<String, Book> purchasedBooks;

    //геттеры
    public HashMap<String, Book> getPurchasedBooks() {
        return purchasedBooks;
    }

    public int getBalance() {
        return balance;
    }

    //сеттеры
    public void setBalance(int balance) {
        if (balance >= 0) {
            this.balance = balance;
        }
    }

    public Buyer(int balance, HashMap<String, Book> purchasedBooks) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }

        if (purchasedBooks != null) {
            this.purchasedBooks = purchasedBooks;
        } else {
            this.purchasedBooks = new HashMap<>();
        }
    }

    public Buyer(int balance) {
        this(balance, new HashMap<>());
    }


    //метод добавления книг в корзину покупателя с учетом количества
    public void addBooks(Book book, int count) {
        if (purchasedBooks.containsKey(book.getTitle())) {
            purchasedBooks.get(book.getTitle())
                    .setAmount(purchasedBooks.get(book.getTitle()).getAmount() + count);
        } else {
            try {
                Book bookToAdd = book.clone();
                bookToAdd.setAmount(count);
                purchasedBooks.put(book.getTitle(), bookToAdd);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    //метод добавления всех имеющихся книг одной позиции в корзину покупателя
    public void addBooks(Book book) {
        addBooks(book, book.getAmount());
    }

}
