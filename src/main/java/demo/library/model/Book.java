package demo.library.model;

import java.time.LocalDate;
import java.util.Set;

//TODO: Implement the class by defining database related models
public class Book {
    private Long id; //TODO: This is the primary key of table
    private String isbn;
    private String title;
    private String publisher;
    private LocalDate publishDate;
    private Set<Author> authors;
}
