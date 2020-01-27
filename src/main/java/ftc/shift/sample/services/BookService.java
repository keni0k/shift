package ftc.shift.sample.services;

import ftc.shift.sample.models.Book;
import ftc.shift.sample.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book provideBook(Long userId) {
        return bookRepository.getOne(userId);
    }

    public Book updateBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    public void deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
    public List<Book> provideBooks() {
        return bookRepository.findAll();
    }
}
