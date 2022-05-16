package com.company;


import java.util.Objects;

public class Book implements Comparable<Book>, Cloneable {
    //поля
    private String title;
    private int amount;
    private int price;

    //геттеры
    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    //сеттеры
    public void setAmount(int amount) {
        if (amount >= 0) {
            this.amount = amount;
        }
    }

    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        }
    }


    public Book(String title, int amount, int price) {
        this.title = title;
        this.amount = amount;
        this.price = price;

    }

    public Book() {
        this(null, 0, 0);
    }

    //создание объекта Book с помощью строки инициализации
    public static Book bookFromString(String stringBook) {
        String title = stringBook.substring(stringBook.indexOf("\"") + 1,
                stringBook.indexOf("\"", stringBook.indexOf("\"") + 1));

        String[] parametrs = stringBook.substring(stringBook.indexOf(title) + title.length() + 3).split(", ");
        int amount = Integer.parseInt(parametrs[0]);
        int price = Integer.parseInt(parametrs[1]);
        return new Book(title, amount, price);
    }

    public String toStoreProductText() {
        return "\"" + this.title + "\", " + this.amount + " шт., " + this.price + " руб.";
    }

    public String toShortStoreProduxtText() {
        return "\"" + this.title + "\", " + this.amount + " шт.";
    }

    //метод преобразования объекта в строку
    @Override
    public String toString() {
        return toStoreProductText();
    }

    //метод клонирования
    @Override
    public Book clone() throws CloneNotSupportedException {
        return (Book) super.clone();
    }


    //метод сравнения для сортировки
    @Override
    public int compareTo(Book book) {
        //Сравнение по названию
        return this.title.compareTo(book.title);
    }

    //логическое сравнение двух объетов Book
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getAmount() == book.getAmount() && getPrice() == book.getPrice() && getTitle().equals(book.getTitle());
    }

    //Метод, обеспечивающий уникальность объекта
    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAmount(), getPrice());
    }
}
