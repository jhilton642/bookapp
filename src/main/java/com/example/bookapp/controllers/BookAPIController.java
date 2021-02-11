package com.example.bookapp.controllers;

import com.example.bookapp.models.Book;
import com.example.bookapp.models.BookList;
import com.example.bookapp.services.BookListService;
import com.example.bookapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Version;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/booksAPI")            //  this will add books to the start of all URL endpoints
public class BookAPIController {

    @Autowired
    private BookListService bookListService;

    @RequestMapping(value = "/saveToList", method = RequestMethod.POST)
    //  this code will be reached by /books/
    public ResponseEntity<String> saveToList(BookList bookList) {
        bookList = bookListService.saveBookList(bookList);
        return ResponseEntity.ok(bookList.toString());
    }
}
