package com.problems.com.problems;

/**
 * Created by Damian on 2017/02/13.
 */
import java.sql.*;
import java.util.Scanner;

public class MsqlDB{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3311/library";

    static final String USER = "root";
    static final String PASS = "root";


    public static void main(String args[]){
        Connection conn =null;
        Statement stmt =null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            /*
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE BOOK " +
                    "(ISBN INTEGER not NULL, " +
                    " title VARCHAR(255), " +
                    " author VARCHAR(255), " +
                    " rented BOOLEAN, " +
                    " overdue BOOLEAN, " +
                    " PRIMARY KEY ( ISBN ))";

            stmt.executeUpdate(sql);

            System.out.println("Created table in given database...");
            */

            Scanner input = new Scanner(System.in);
            System.out.println("Enter ISBN: ");
            int ISBn=input.nextInt();
            System.out.println("Enter author: ");
            String author =input.next();
            System.out.println("Enter title: ");
            String title =input.next();
            System.out.println("Enter rented (true/false): ");
            String rented =input.next();
            System.out.println("Enter overdue (true/false): ");
            boolean overdue =Boolean.parseBoolean(input.next());
            stmt = conn.createStatement();
            String sql1 = "INSERT INTO BOOK (ISBN, title, author, rented, overdue) VALUES ('"+ISBn+"','"+title+"','"+author+"','"+rented+"','"+overdue+"');";
            stmt.execute(sql1);
            System.out.println("Successfully added book");
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}