package ftc.shift.sample.api;


import ftc.shift.sample.models.Book;
import ftc.shift.sample.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class BooksController {
    private static final String BOOKS_PATH = "/api/v001/books";
    private BookService service;

    @Autowired
    public BooksController(BookService service) {
        this.service = service;
    }

    /**
     * Добавление новой книги
     *
     * @param book   - Данные для новой книги (Название, автор, количество страниц, жанры)
     * @return Сохранённая книга с установленным {@link Book#getId()}
     */
    @PostMapping(BOOKS_PATH)
    public ResponseEntity<Book> createBook(
            @RequestBody Book book) {
        Book result = service.createBook(book);
        return ResponseEntity.ok(result);
    }

    /**
     * Получение книги с указанным идентификатором
     *
     * @param bookId - Идентификатор книги
     */
    @GetMapping(BOOKS_PATH + "/{bookId}")
    public ResponseEntity<Book> readBook(
            @PathVariable Long bookId) {
        Book book = service.provideBook(bookId);
        return ResponseEntity.ok(book);
    }

    /**
     * Обновление существующей книги
     *
     * @param book   - Новые данные для книги (Название, автор, количество страниц, жанры)
     */
    @PatchMapping(BOOKS_PATH + "/{bookId}")
    public ResponseEntity<Book> updateBook(
            @RequestBody Book book) {
        Book updatedBook = service.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    /**
     * Удаление существующей книги
     *
     * @param bookId - Идентификатор книги, которую необходимо удалить
     */
    @DeleteMapping(BOOKS_PATH + "/{bookId}")
    public ResponseEntity<?> deleteBook(
            @PathVariable Long bookId) {
        service.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    /**
     * Получение всех книг пользователя
     *
     */
    @GetMapping(BOOKS_PATH)
    public ResponseEntity<Collection<Book>> listBooks() {
        Collection<Book> books = service.provideBooks();
        return ResponseEntity.ok(books);
    }
}