package com.problems;

import com.problems.com.problems.model.Book;
import com.problems.com.problems.repositories.books.BookDataInterface;
import com.problems.com.problems.repositories.books.bookImpl;


import java.io.IOException;
import java.util.*;

public class Library {

    public static void main(String[] args) {
        BookDataInterface mySQLBooksDataInterface = new bookImpl();

//        String title = "My First Book";
//        String author = "Me";
//        String isbn = UUID.randomUUID().toString();
//        Book book = new Book(isbn, title, author, false, false);
//        mySQLBooksDataInterface.createBook(book);
//
//        Book book2 = mySQLBooksDataInterface.findBookByISBN(isbn);
//        System.out.println(book2);
//        System.out.println("Book == book2 -> " + book.equals(book2));
//
//        String title1 = "My Second Book";
//        String isbn1 = UUID.randomUUID().toString();
//        Book book3 = new Book(isbn1, title1, author, true, false);
//        mySQLBooksDataInterface.createBook(book3);
//
//        book2.setRented(true);
//        mySQLBooksDataInterface.updateBook(book2);
//
//        Book book4 = mySQLBooksDataInterface.findBookByISBN(isbn);
//        System.out.println(book4);
//        System.out.println("Book == book4 -> " + book2.equals(book4));
//
//        mySQLBooksDataInterface.deleteBook(isbn1);
//
//        Book book5 = mySQLBooksDataInterface.findBookByISBN(isbn1);
//        System.out.println(book5);
//        System.out.println("Book == book5 -> " + book2.equals(book5));

        Scanner sc = new Scanner(System.in);
        Boolean set = true;
        while(set==true){
            System.out.println("What would you like to do: (Enter a number)");
            System.out.println("1. Create a book?");
            System.out.println("2. Find a book?");
            System.out.println("3. Update a book?");
            System.out.println("4. Delete a book?");
            System.out.println("5. Search");
            System.out.println("6. Exit?");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("Enter ISBN");
                    String isbn=sc.nextLine();
                    System.out.println("Enter title");
                    String title = sc.nextLine();
                    System.out.println("Enter author");
                    String author = sc.nextLine();
                    Book book = new Book(isbn, title, author, false, false);
                    mySQLBooksDataInterface.createBook(book);
                    break;
                }
                case 2: {
                    System.out.println("Search by: " +
                            "1. ISBN" +
                            " 2. Title" +
                            " 3. Author");
                    int choice2 = sc.nextInt();
                    Book book2= null;
                    switch (choice2){
                        case 1: System.out.println("Enter ISBN");
                            String ISBN = sc.next();
                            book2 = mySQLBooksDataInterface.findBookByISBN("ISBN",ISBN);
                            System.out.println(book2);
                            break;
                        case 2:
                            sc.nextLine();
                            System.out.println("Enter title");
                            String title = sc.nextLine();
                            book2 = mySQLBooksDataInterface.findBookByISBN("title",title);
                            System.out.println(book2);
                            break;
                        case 3:
                            sc.nextLine();
                            System.out.println("Enter author");
                            String author = sc.nextLine();
                            book2 = mySQLBooksDataInterface.findBookByISBN("author",author);
                            System.out.println(book2);
                            break;
                        default: break;
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter ISBN");
                    String isbn=sc.next();
                    System.out.println("Is it rented: true/false");
                    Boolean rented =sc.nextBoolean();
                    System.out.println("Is it overdue: true/false");
                    Boolean overdue = sc.nextBoolean();
                    Book book2 = mySQLBooksDataInterface.findBookByISBN("ISBN",isbn);
                    book2.setRented(rented);
                    book2.setOverdue(overdue);
                    mySQLBooksDataInterface.updateBook(book2);
                    break;
                }
                case 4: {
                    System.out.println("Enter ISBN");
                    String isbn=sc.next();
                    mySQLBooksDataInterface.deleteBook(isbn);
                    break;
                }
                case 5: {
                    System.out.println("Enter text to search ");
                    String searchText = sc.nextLine();
                    System.out.println("Searching...");
                    List<Book> searchResults =mySQLBooksDataInterface.search(searchText);
                    System.out.println("Results: ");
                    for (int i=0;i<searchResults.size();i++){
                        Book book1 = searchResults.get(i);
                        System.out.println((i+1)+". "+book1);
                    }
                    System.out.println("Select a book, or 0 to return to main menu");
                    String choice1 = sc.nextLine();
                    int choiceNum = Integer.parseInt(choice1);
                    if (choiceNum==0){
                        break;
                    }else if(choiceNum<=searchResults.size()){
                        Book book1 = searchResults.get(Integer.parseInt(choice1)-1);
                        System.out.println((Integer.parseInt(choice1))+". "+book1);
                        if (book1.getRented()==false){
                            System.out.println("1. Rent book");
                            String rented = sc.nextLine();
                            if(rented.equals("1")){
                                book1.setRented(true);
                                mySQLBooksDataInterface.updateBook(book1);

                            }else break;

                        } else {
                            System.out.println("1. Return book");
                            System.out.println("2. Set overdue");
                            String update = sc.nextLine();
                            if (update.equals("1")){
                                book1.setRented(false);
                                book1.setOverdue(false);
                            } else if (update.equals("2")){
                                book1.setOverdue(true);
                            } else break;
                            mySQLBooksDataInterface.updateBook(book1);
                        }
                    }else System.out.println("Incorrect input!!!!");


//                    Iterator<Book> iterator = searchResults.iterator();
//                    while (iterator.hasNext()){
//                        Book book = iterator.next();
//                        System.out.println(book);
//                    }
                    break;
                }
                case 6: {
                    set = false;
                    System.exit(0);
                }
                default: {
                    System.out.println("Please input 1-5");
                }
            }
        }


    }

}
