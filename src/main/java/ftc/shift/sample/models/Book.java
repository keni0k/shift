package ftc.shift.sample.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {
    /**
     * Уникальный идентификатор книги
     */
    @Id
    private long id;

    /**
     * Название книги
     */
    private String name;

    /**
     * Автор
     */
    private String author;

    /**
     * Количество страниц
     */
    private Integer pages;

    /**
     * Список жанров
     */

    // private List<String> genre;

    public Book() {
    }

    public Book(int id, String name, String author, Integer pages) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
//        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

}
