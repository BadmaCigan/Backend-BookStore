package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
Book actualBook;


    @BeforeEach
    void setUp() {


    }

    @AfterEach
    void tearDown() {
        System.out.println("BookTest done");
    }

    @Test
    void string_should_be_converted_to_a_book_test(){
        String actualStringBook = "\"Junit 5 для начинающих\", 10, 100";
        Book actualBook = Book.bookFromString(actualStringBook);

        Book expectedBook = new Book("Junit 5 для начинающих", 10, 100);

        Assertions.assertEquals(expectedBook,actualBook);
    }


}