package demo.library.jsonGeneratorUtil;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Set;

public class AuthorJson {

    @JsonProperty("id")
    Long id;

    @JsonProperty("name")
    String name;

    @JsonProperty("country")
    String country;

    @JsonProperty("dateBirth")
    LocalDate dateBirth;

    @JsonProperty("bookSet")
    Set<BookJson> bookSet;

    public AuthorJson(Long id, String name, String country, LocalDate dateBirth, Set<BookJson> bookSet) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.dateBirth = dateBirth;
        this.bookSet = bookSet;
    }

    public AuthorJson() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Set<BookJson> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<BookJson> bookSet) {
        this.bookSet = bookSet;
    }
}
