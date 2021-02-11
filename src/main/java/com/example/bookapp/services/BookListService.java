package com.example.bookapp.services;

import com.example.bookapp.models.Book;
import com.example.bookapp.models.BookList;
import com.example.bookapp.repository.BookListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookListService {
    @Autowired
    private BookListRepository bookListRepository;

    public Iterable<BookList> findAllBookLists() {
        return bookListRepository.findAll();
    }

    public BookList getBookListById(Integer id) {
        Optional<BookList> oBook = bookListRepository.findById(id);
        return oBook.orElse(null);
    }

    public BookList saveBookList(BookList bookList) {
        return bookListRepository.save(bookList);
    }

}
