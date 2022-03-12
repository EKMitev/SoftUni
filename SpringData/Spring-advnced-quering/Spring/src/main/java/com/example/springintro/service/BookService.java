package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBookTitlesByAgeRestriction(String ageRestriction);

    List<String> findAllByEditionTypeAndCopies(String editionType, int copies);

    List<String> findAllByPriceLessThanAndGreaterThan(float lowBound, float upBound);

    List<Book> findNotReleasedIn(int releaseYear);

    List<Book> findBefore(String date);

}
