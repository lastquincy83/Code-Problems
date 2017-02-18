package com.problems.com.problems.model;

public class Book {

    private String ISBN;
    private String title;
    private String author;
    private Boolean rented;
    private Boolean overdue;

    public Book(String ISBN, String title, String author, Boolean rented, Boolean overdue) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.rented = rented;
        this.overdue = overdue;
    }

    public String getISBN() {

        return ISBN;
    }

    public void setISBN(String ISBN) {


        this.ISBN = ISBN;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public Boolean getOverdue() {
        return overdue;
    }

    public void setOverdue(Boolean overdue) {
        this.overdue = overdue;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", rented=" + rented +
                ", overdue=" + overdue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!ISBN.equals(book.ISBN)) return false;
        if (!title.equals(book.title)) return false;
        if (!author.equals(book.author)) return false;
        if (rented != null ? !rented.equals(book.rented) : book.rented != null) return false;
        return overdue != null ? overdue.equals(book.overdue) : book.overdue == null;
    }

    @Override
    public int hashCode() {
        int result = ISBN.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + (rented != null ? rented.hashCode() : 0);
        result = 31 * result + (overdue != null ? overdue.hashCode() : 0);
        return result;
    }
}
