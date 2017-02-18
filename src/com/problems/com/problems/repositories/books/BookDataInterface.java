package com.problems.com.problems.repositories.books;


import com.problems.com.problems.model.Book;

import java.util.List;

public interface BookDataInterface {

    public void createBook(Book book);

    public Book findBookByISBN(String field,String value);

    public void updateBook(Book book);

    public void deleteBook(String ISBN);

    public List<Book> search(String searchText);
}

 