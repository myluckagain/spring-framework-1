package ru.otus.dz6.service;

import org.springframework.stereotype.Service;
import ru.otus.dz6.dao.BookDao;
import ru.otus.dz6.domain.Book;

@Service
public class BooksServiceImpl implements BooksService {

    private final BookDao bookDao;

    public BooksServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void start() {
        bookDao.insert(new Book(1, "Азазель", 1, 1));
        System.out.println("Book have been inserted");
    }

    @Override
    public void addBook(String name, String genre, String author) {
//        bookDao.insert(new Book(2, name, author, genre));
    }

//    @Override
//    public void addBook() {
//        bookDao.insert(new Book(2, ));
//    }
}
