package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Book;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Интерфейс для получения данных по книгам
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  /*Book fetchBook(String userId, String bookId);

  Book updateBook(String userId, String bookId, Book book);

  void deleteBook(String userId, String bookId);

  Book createBook(String userId, Book book);
*/
}
