package com.example.bookapp.repository;

import com.example.bookapp.models.BookList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookListRepository extends CrudRepository<BookList, Integer> {
}
