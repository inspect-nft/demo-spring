package demo.library.jsonGeneratorUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

/**
 * This Book Json generation utility could be used to generate random Book request json data
 */
public class BookJsonGenerator {
    private static final char LIMIT = 100;
    private static final String BOOK_JSON_GENERATED_FILE = "GeneratedBookValues.json";
    private static final char COMMA = ',';
    private static final String START_SQUARE_BRACKET = "[";
    private static final String END_SQUARE_BRACKET = "[";

    public static void main(String[] args) throws Exception {

        Faker faker = new Faker(); //Use Fake library to generate random fake data

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOK_JSON_GENERATED_FILE))) {
            writer.write(START_SQUARE_BRACKET);
            writer.newLine();
            //Generate 100 Author Request Data and please adjust the values according to your need
            for (int i = 1; i <= LIMIT; i++) {

                Set<AuthorJson> authJsons = generateAuthorJsons(faker);

                BookJson book = new BookJson();
                book.setId(faker.number().randomNumber());
                book.setIsbn(faker.code().isbn10());
                book.setTitle(faker.book().title());
                book.setPublisher(faker.book().publisher());
                book.setPublishDate(faker.date().birthday().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate());
                book.setAuthors(authJsons);

                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());

                //Print Prettier
                mapper.enable(SerializationFeature.INDENT_OUTPUT);

                String json = mapper.writeValueAsString(book);
                System.out.println(json);

                writer.write(json);
                if (i != LIMIT) {
                    writer.write(COMMA);
                }
                writer.newLine();
            }

            writer.write(END_SQUARE_BRACKET);
            writer.newLine();
        }
    }

    private static Set<AuthorJson> generateAuthorJsons(Faker faker) {
        Set<AuthorJson> authors = new HashSet<>();
        authors.add(new AuthorJson(faker.number().randomNumber(),
                faker.book().author(), faker.address().country(),
                faker.date().birthday().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate(),
                new HashSet<>()));
        authors.add(new AuthorJson(faker.number().randomNumber(),
                faker.book().author(), faker.address().country(),
                faker.date().birthday().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate(),
                new HashSet<>()));
        return authors;
    }
}
