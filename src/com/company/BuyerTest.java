package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {
    Buyer actualBuyer;
    Book actualBook;


    @BeforeEach
    void setUp() {
        HashMap<String,Book> actualBookshelsf = new HashMap<>();
        actualBook = new Book("Actual Book",23,23);
        actualBookshelsf.put(actualBook.getTitle(),actualBook);
        actualBuyer = new Buyer(1000,actualBookshelsf);
    }

    @AfterEach
    void tearDown(){
        System.out.println("BuyerTest done");
    }

    @Test
    void should_add_a_new_book(){
            Book expectedBook = new Book("Expected Book",22,24);
            actualBuyer.addBooks(expectedBook);
        Assertions.assertEquals(expectedBook,actualBuyer.getPurchasedBooks().get(expectedBook.getTitle()));

    }

    @Test
    void should_add_a_book_to_an_existing_one(){
        Book expectedBook = new Book(actualBook.getTitle(),actualBook.getAmount()*2,actualBook.getPrice());

        actualBuyer.addBooks(actualBook);

        Assertions.assertEquals(expectedBook,actualBuyer.getPurchasedBooks().get(actualBook.getTitle()));
    }




}