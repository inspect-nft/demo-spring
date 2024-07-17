package demo.library.model;

import java.time.LocalDate;
import java.util.Set;

//TODO: Implement the class by defining database related models
public class Author {
    Long id; //TODO: This is the primary key of table
    String name;
    String country;
    LocalDate dateBirth;
    Set<Book> bookSet;
}
