package com.example.bookapp.models;

import javax.persistence.*;

@Entity
@Table(name = "booklist")
public class BookList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    private String listName;
    private String bookId;
    private String title;
    private String author;
    private int pageCount;


    public void setlistName(String listName) {
        this.listName = listName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "{ \"id\"        :   " + id +
                ", \"listName\"  : \"" + listName + '\"' +
                ", \"bookId\"    : \"" + bookId + '\"' +
                ", \"title\"     : \"" + title + '\"' +
                ", \"author\"    : \"" + author + '\"' +
                ", \"pageCount\" :   " + pageCount + '}';
    }

    public BookList() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getListName() {
        return listName;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }

}
