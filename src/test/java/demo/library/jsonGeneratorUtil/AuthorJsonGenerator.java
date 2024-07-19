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
 * This Author Json generation utility could be used to generate random Author request json data
 */
public class AuthorJsonGenerator {
    private static final char LIMIT = 100; //Generate 100 Book Request Data and please adjust the values according to your need

    private static final String AUTHOR_JSON_GENERATED_FILE = "GeneratedAuthValues.json";
    private static final char COMMA = ',';
    private static final String START_SQUARE_BRACKET = "[";
    private static final String END_SQUARE_BRACKET = "[";

    public static void main(String[] args) throws Exception {

        Faker faker = new Faker(); //Use Fake library to generate random fake data

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AUTHOR_JSON_GENERATED_FILE))) {
            writer.write(START_SQUARE_BRACKET);
            writer.newLine();

            for (int i = 1; i <= LIMIT; i++) {

                Set<BookJson> books = generateBookJsons(faker);

                AuthorJson authorJson = new AuthorJson();
                authorJson.setId(faker.number().randomNumber());
                authorJson.setName(faker.name().firstName());
                authorJson.setCountry(faker.country().name());
                authorJson.setDateBirth(faker.date().birthday().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate());
                authorJson.setBookSet(books);

                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());

                //Print Prettier
                mapper.enable(SerializationFeature.INDENT_OUTPUT);

                String json = mapper.writeValueAsString(authorJson);
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

    private static Set<BookJson> generateBookJsons(Faker faker) {
        Set<BookJson> bookJsons = new HashSet<>();
        bookJsons.add(new BookJson(faker.number().randomNumber(),
                faker.code().isbn10(), faker.book().title(), faker.book().publisher(),
                faker.date().birthday().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate(),
                new HashSet<>()));
        bookJsons.add(new BookJson(faker.number().randomNumber(),
                faker.code().isbn10(), faker.book().title(), faker.book().publisher(),
                faker.date().birthday().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate(),
                new HashSet<>()));
        return bookJsons;
    }
}
