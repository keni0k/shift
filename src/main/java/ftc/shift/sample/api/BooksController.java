package ftc.shift.sample.api;


import ftc.shift.sample.models.Book;
import ftc.shift.sample.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v001/books")
public class BooksController {
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
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book result = service.createBook(book);
        return ResponseEntity.ok(result);
    }

    /**
     * Получение книги с указанным идентификатором
     *
     * @param bookId - Идентификатор книги
     */
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> readBook(
            @PathVariable Long bookId) {
        Book book = service.provideBook(bookId);
        return ResponseEntity.ok(book);
    }

    /**
     * Получение всех книг
     *
     */
    @GetMapping
    public ResponseEntity<Collection<Book>> listBooks() {
        Collection<Book> books = service.provideBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * Обновление существующей книги
     *
     * @param book   - Новые данные для книги (Название, автор, количество страниц, жанры)
     */
    @PatchMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book updatedBook = service.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    /**
     * Удаление существующей книги
     *
     * @param bookId - Идентификатор книги, которую необходимо удалить
     */
    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        service.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
}