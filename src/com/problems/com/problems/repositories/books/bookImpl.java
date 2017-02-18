package com.problems.com.problems.repositories.books;

import com.problems.com.problems.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class bookImpl implements BookDataInterface{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3311/library";

    static final String USER = "root";
    static final String PASS = "root";

    private Connection getDBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Successfully connected to DB");
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBook(Book book){
           Statement stmt = null;
           try {
               stmt = getDBConnection().createStatement();
               String sql = "UPDATE book SET rented= "+book.getRented()+
                   " , overdue ="+book.getOverdue()+" WHERE ISBN = '"+book.getISBN()+"';";
               stmt.execute(sql);

           } catch (SQLException e) {
               e.printStackTrace();
           } finally {
               if(stmt!=null){
                   try {
                       stmt.close();
                       System.out.println("Successfully updated book");
                   } catch (SQLException e) {
                       e.printStackTrace();
                   }
               }
           }
    }

    public void deleteBook(String ISBN) {
        Statement stmt = null;
        try {
            stmt = getDBConnection().createStatement();
            String sql = "DELETE FROM book WHERE ISBN='"+ISBN+"';";
            stmt.execute(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stmt!=null){
                try {
                    stmt.close();
                    System.out.println("Successfully deleted book");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void createBook(Book book) {
        Statement stmt = null;
        try {
            stmt = getDBConnection().createStatement();
            String sql = "INSERT INTO book (ISBN, title, author, rented, overdue) VALUES ('"
                   + book.getISBN() + "','"
                   + book.getTitle() + "','"
                   + book.getAuthor() + "',"
                   + book.getRented() + ","
                   + book.getOverdue() + ");";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stmt!=null){
                try {
                    stmt.close();
                    getDBConnection().close();
                    System.out.println("Successfully created book");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Successfully added book");
    }

    public Book findBookByISBN(String field,String value){
        Statement stmt = null;
        Book book = null;
        try {
            stmt = getDBConnection().createStatement();
            String sql = "SELECT ALL ISBN,title,author,rented,overdue FROM book WHERE "+field+" LIKE '%" + value + "%';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String ISBn = rs.getString("ISBN");
                String title = rs.getString("title");
                String author = rs.getString("author");
                Boolean rented = rs.getBoolean("rented");
                Boolean overdue = rs.getBoolean("overdue");
                book = new Book(ISBn, title, author, rented, overdue);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(stmt!=null || getDBConnection()!=null){
                try {
                    stmt.close();
                    getDBConnection().close();
                    if(book!=null){
                        System.out.println("Successfully found book");
                        return book;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<Book> search(String searchText) {
        List<Book> results = new ArrayList();
        Statement stmt = null;
        Connection dbConnection=null;
        try {
            dbConnection = getDBConnection();
            stmt = dbConnection.createStatement();
            String sql = "SELECT ALL ISBN,title,author,rented,overdue FROM book WHERE ISBN LIKE '%" + searchText + "%' OR title LIKE '%" + searchText + "%' OR author LIKE '%" + searchText + "%';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String ISBn = rs.getString("ISBN");
                String title = rs.getString("title");
                String author = rs.getString("author");
                Boolean rented = rs.getBoolean("rented");
                Boolean overdue = rs.getBoolean("overdue");
                Book book = new Book(ISBn, title, author, rented, overdue);
                results.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (stmt!=null){
                    stmt.close();
                }
                if (dbConnection!=null){
                    dbConnection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return results;
    }
}
