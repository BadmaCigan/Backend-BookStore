package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    Shop actualshop;

    @BeforeEach
    void setUp() {
        String initString = "balance: 1000, books: [(\"Алгебра, 10 класс\", 5, 100), (\"Теория чисел, 2 класс\", 42, 500)]";
        actualshop = new Shop(initString);
    }



    @AfterEach
    void tearDown() {
        System.out.println("ShopTest done");
    }

    @Test
    void print_balance_command_test(){
        String actualBalance = actualshop.processingRequest(Shop.printBalanceCommand);

        String expectedBalance = "1000";

        Assertions.assertEquals(expectedBalance,actualBalance);
    }

    @Test
    void show_books_in_stock_command_test(){
        String actualStockOfBooks = actualshop.processingRequest(Shop.showBooksInStockCommand);

        String expectedStockOfBooks = "\"Алгебра, 10 класс\", 5 шт., 100 руб.\n" +
                "\"Теория чисел, 2 класс\", 42 шт., 500 руб.";

        Assertions.assertEquals(expectedStockOfBooks,actualStockOfBooks);
    }

    @Test
    void buying_of_books_test(){
        String buyBooksRequest = "buy \"Алгебра, 10 класс\" 2";
        String actualResponse = actualshop.processingRequest(buyBooksRequest);
        String actaulBoughtBooks = actualshop.processingRequest(Shop.showBoughtBooksCommand);
        String actualBooksInStock = actualshop.processingRequest(Shop.showBooksInStockCommand);

        String expectedResponse = Shop.dealMessage;
        String expectedBoughtBooks = "\"Алгебра, 10 класс\", 2 шт.";
        String expectedBooksInStock = "\"Алгебра, 10 класс\", 3 шт., 100 руб.\n" +
                "\"Теория чисел, 2 класс\", 42 шт., 500 руб.";

        Assertions.assertEquals(expectedResponse,actualResponse);
        Assertions.assertEquals(expectedBoughtBooks,actaulBoughtBooks);
        Assertions.assertEquals(expectedBooksInStock,actualBooksInStock);
    }

    @Test
    void no_deal_buy_situations_test(){
        String actualZeroBookRequest = "buy \"Алгебра, 10 класс\" 0";
        String actualBiggerThanBalanceBooksRequest = "buy \"Алгебра, 10 класс\" 10000";
        String actualNullBookRequest = "buy \"C++ за час\" 1";

        String actualZeroBookResponse = actualshop.processingRequest(actualZeroBookRequest);
        String actualBiggerThanBalanceBooksResponse = actualshop.processingRequest(actualBiggerThanBalanceBooksRequest);
        String actualNullBookResponse = actualshop.processingRequest(actualNullBookRequest);


        String expectedResponse = Shop.noDealMessage;

        Assertions.assertEquals(expectedResponse,actualZeroBookResponse);
        Assertions.assertEquals(expectedResponse,actualBiggerThanBalanceBooksResponse);
        Assertions.assertEquals(expectedResponse,actualNullBookResponse);


    }


}