package com.example.bookapp.controllers;

import com.example.bookapp.models.Book;
import com.example.bookapp.models.BookList;
import com.example.bookapp.services.BookListService;
import com.example.bookapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")            //  this will add books to the start of all URL endpoints
public class BookController {

    @Autowired
    //	@Autowired will request SpringBoot to find the BookService class and instantiate one for us
    //	and assign (INJECT) the class property with the value. This is Dependency Injection.
    //	our class depends on this service and SpringBoot will inject it into our class
    private BookService bookService;
    @Autowired
    private BookListService bookListService;


    @RequestMapping("/")                                    //  this code will be reached by /books/
    public String index(Model model) {
        //  get a list of all books add to the model and list them
        Iterable<Book> books = bookService.listAllBooks();
        model.addAttribute("books", books);

        //  the the bookList page will be happy to display it
        return "MyLists";
    }

    @RequestMapping("/cat/{genera}")                                    //  this code will be reached by /books/
    public String fantasy(Model model, @PathVariable String genera) {
        //  get a list of all books add to the model and list them
        Iterable<BookList> bookLists = bookListService.findAllBookLists();
        Iterable<Book> books = bookService.findAllBooks();
        List<Book> list = new ArrayList<>();
        List<BookList> list2 = new ArrayList<>();
        books.forEach(b -> {
            if (b.getListName().equals(genera)) {
                list.add(b);
            }
        });
        bookLists.forEach(b -> {
            if (b.getListName().equals(genera)) {
                list2.add(b);
            }
        });
        model.addAttribute("books", list);
        model.addAttribute("books2", list2);
        model.addAttribute("cat", genera);
        //  the the bookList page will be happy to display it
        return "Cat";
    }

    //  let's CREATE a new book
    @RequestMapping("/new")
    public String newBook(Model model) {
        //  since we do not have a book, let's send an empty book to the bookEdit page
        model.addAttribute("book", new Book());
        return "bookEdit";
    }

    //  let's CREATE a new book
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(@RequestParam String author, String title, Integer pageCount, String listName, Model model) {
        //  since we do not have a book, let's send an empty book to the bookEdit page
        Book book = new Book(title, author, pageCount == null ? 0 : pageCount, listName);
        bookService.add(book);
        return "redirect:/books/";
    }

    //  id will be the key to the book we want to READ from the database
    @RequestMapping("/{id}")
    public String readBook(@PathVariable Integer id, Model model) {
        //  find in the database a book with id = to our PathVariable
        Book book = bookService.getBookById(id);
        BookList bookList = bookListService.getBookListById(id);
        //  did we find a book?
        if (book != null) {
            //  yes. add the book to the model and display the bookDetails page
            model.addAttribute("book", book);
            return "bookDetails";
        } else {
            //  no, we did not find a book. Display an error message
            model.addAttribute("message", "The Book Id: " + id + " was not found in the database");
            return "404";       //  book (page) not found
        }
    }

    //  id will be the key to the book we want to UPDATE
    @RequestMapping("/edit/{id}")
    public String updateBook(@PathVariable Integer id, Model model) {
        //  find the book in the database and send that data to the bookEdit page
        model.addAttribute("book", bookService.getBookById(id));
        return "bookEdit";
    }

    //  we have finished making our changes to our book. The data is POSTed back to the server
    //  all of the data is saved in a Book object and UPDATEd in the database.


    //  using the id from the URL find and DELETE our book
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        //  go to the list all books page when complete
        return "redirect:/books/";
    }

    //  using the author from the search form get all books by this author
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String author, Model model) {
        //  SEARCH for all books by author
        Iterable<Book> list = bookService.findByAuthor(author);

        //  pass the list of books by author
        model.addAttribute("author", author);
        model.addAttribute("books", list);

        //  the the bookList page will be happy to display it
        return "Cat";
    }

    //  using the author from the search form get all books by this author
    @RequestMapping(value = "/title", method = RequestMethod.POST)
    public String searchTitle(@RequestParam String title, Model model) {
        //  SEARCH for all books by author
        Iterable<Book> list = bookService.findByTitle(title);

        //  pass the list of books by title back to the web page
        model.addAttribute("title", title);
        model.addAttribute("books", list);

        //  the the bookList page will be happy to display it
        return "Cat";
    }

    //  create a random book and add to the database
    @RequestMapping("/add")                                    //  this code will be reached by /books/add
    public String add(Model model) {
        //  create lists of random titles and things and authors
        String[] titles = {"Master", "Ruler", "Lord", "King", "Programmer", "Dude"};
        String[] subjects = {"Rings", "Kitchen", "Code", "Keyboard", "Debugger"};
        String[] authors = {"Tolkien", "Heinlein", "Asimov", "Adams", "Clarke"};
        String[] listNames = {"Non-Fiction", "Fantasy", "Science Fiction", "Mystery", "Poetry", "Drama"};
        String title, thing, author, listName;

        //  randomly create choose a title, object and author from our lists
        title = titles[(int) (Math.random() * titles.length)];                   //  choose a random title
        thing = subjects[(int) (Math.random() * subjects.length)];               //  choose a random subject
        author = authors[(int) (Math.random() * authors.length)];          //  choose a random author
        listName = listNames[(int) (Math.random() * authors.length)];
        int numberOfPages = (int) (Math.random() * 600) + 100;             //  make book at least 100 page but less than 700

        //  create a book from these random pieces and display the list of books
        Book book = new Book(title + " of the " + thing, author, numberOfPages, listName);
        bookService.add(book);
        return "redirect:/books/";
    }

    @RequestMapping("/searchAPI")                                    //  this code will be reached by /books/
    public String searchAPI() {
        return "bookSearch";
    }

    @RequestMapping("/home")                                    //  this code will be reached by /books/
    public String getHome() {
        return "HomePage";
    }

    @RequestMapping("/patreon")                                    //  this code will be reached by /books/
    public String getPatreon() {
        return "Patreon";
    }
}
/*
Field error in object 'bookList' on field 'bookId':
rejected value [o8SVQscnVEQC]; codes [typeMismatch.bookList.bookId,typeMismatch.bookId,typeMismatch.int,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [bookList.bookId,bookId]; arguments []; default message [bookId]]; default message [Failed to convert property value of type 'java.lang.String' to required type 'int' for property 'bookId'; nested exception is java.lang.NumberFormatException: For input string: "o8SVQscnVEQC"]

 */