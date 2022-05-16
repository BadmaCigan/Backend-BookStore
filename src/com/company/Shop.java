package com.company;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Shop {
    //Константы
    public static final String printBalanceCommand = "print balance";
    public static final String showBooksInStockCommand = "show books in stock";
    public static final String showBoughtBooksCommand = "show bought books";
    public static final String dealMessage = "deal";
    public static final String noDealMessage = "no deal";
    public static final Pattern buyCommandRegularExpression = Pattern.compile("buy \"[^\"]+\" \\d+");
    public static final Pattern buyBookCommandRegularExpression = Pattern.compile("buy book \"[^\"]+\" \\d+");
    public static final Pattern initStringRegularExpression = Pattern.compile("balance: \\d+, books: \\[\\(\"[^\"]+\", \\d+, \\d+\\)(, \\(\"[^\"]+\", \\d+, \\d+\\)){0,}\\]");
    public static final String exit = "exit";

    //Поля класса
    private HashMap<String, Book> bookshelf;
    private Buyer buyer;
    private Scanner in;

    //иницилизация магазина
    public Shop(String initString) {
        in = new Scanner(System.in);
        bookshelf = new HashMap<>();
        if (initStringRegularExpression.matcher(initString).find()) {
            int balance = Integer.parseInt(initString.substring(9, initString.indexOf(",")));
            this.buyer = new Buyer(balance);
            String[] books = initString.substring(initString.indexOf('[') + 1, initString.indexOf(']')).split("(\\), )|\\)");

            for (String bookString :
                    books) {
                Book book = Book.bookFromString(bookString);
                bookshelf.put(book.getTitle(), book);
            }
        } else {
            System.err.println("Неправильный формат ввода");
            System.exit(1);
        }


    }

    //метод работы при котором обрабатываются пользвательские команды с клавиатуры
    public void open() {
        String response = this.in.nextLine();
        while (!response.equals(null)) {
            System.out.println(processingRequest(response));
            response = this.in.nextLine();
        }


    }

    //метод выполнения запроса клиента
    public String processingRequest(String request) {
        if (request.equals(printBalanceCommand)) {
            return getBalance(buyer);
        } else if (request.equals(showBooksInStockCommand)) {
            return showBooksInStock();
        } else if (request.equals(exit)) {
            return null;
        } else if (buyCommandRegularExpression.matcher(request).find() || buyBookCommandRegularExpression.matcher(request).find()) {
            String[] params = request.split("( \")|(\" )");
            String titleOfBook = params[1].substring(0, params[1].length());
            return buyBooks(buyer, bookshelf.get(titleOfBook), Integer.parseInt(params[2]));


        } else if (request.equals(showBoughtBooksCommand)) {
            return showBoughtBooks(buyer);


        } else {
            return "I don't understand";
        }

    }

    //метод, возращающий строковое представление купленных книг
    private String showBoughtBooks(Buyer buyer) {
        String res = "";
        Set<String> titles = buyer.getPurchasedBooks().keySet();
        Stream<String> sortedTitles = titles.stream().sorted();


        for (Object bookTitle :
                titles) {
            res += buyer.getPurchasedBooks().get(bookTitle).toShortStoreProduxtText() + "\n";
        }

        return res.substring(0, res.length() - 1);
    }

    //метод, возращающий строковое представление книг в продаже
    private String showBooksInStock() {
        String res = "";
        for (String bookKey :
                this.bookshelf.keySet()) {
            res += bookshelf.get(bookKey) + "\n";
        }
        return res.substring(0, res.length() - 1);
    }

    //метод возращающий баланс покупателя
    private String getBalance(Buyer buyer) {
        return Integer.toString(buyer.getBalance());
    }

    //метод, осуществляющий продажу книг покупателю
    private String buyBooks(Buyer buyer, Book book, int count) {
        if (count <= 0 || book == null || book.getAmount() <= 0) {
            return noDealMessage;
        }
        //захват пользователя для проведения транзакции
        synchronized (buyer) {
            if (buyer.getBalance() >= book.getPrice() * count) {
                book.setAmount(book.getAmount() - count);
                buyer.setBalance(buyer.getBalance() - book.getPrice() * count);
                buyer.addBooks(book, count);
                return dealMessage;
            }
        }
        return noDealMessage;
    }

}
