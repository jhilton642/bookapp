package com.example.bookapp.models;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    private String title;
    private String author;
    private int pageCount;
    private int yearPublished;
    private String previewLink;
    private String infoLink;
    private String smallThumbnail;
    private String thumnail;
    private String etag;
    private String bookId;
    private String listName;

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {

        this.pageCount = pageCount;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Book() {
    }

    public Book(String title, String author, int pageCount, String listName) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.listName = listName;
    }
}
