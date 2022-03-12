package com.example.springintro;

import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //   seedData();

        //printAllBooksAfterYear(2000);
//         printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        //   printAllAuthorsAndNumberOfTheirBooks();
        //   pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

        //printTitlesByAgeRestriction("teEn");
       // printTitlesByEditionTypeAndCopies("GOLD", 5000);
        //findAllByPriceLessThanAndGreaterThan(5, 40);
        //findByYearNot(1998);
        //printBookBefore("10-04-1992");
        printAuthorNamesBy("dy");
    }

    private void printAuthorNamesBy(String partOfName){
        this.authorService.getAuthorNamesWith(partOfName).forEach(System.out::println);
    };

    private void printBookBefore(String date){
        this.bookService.findBefore(date)
            .forEach(b -> System.out.printf("%s %s %.2f%n",
                b.getTitle(), b.getEditionType(), b.getPrice()));
    }

    private void findByYearNot(int year){
        this.bookService.findNotReleasedIn(year).forEach(b -> System.out.println(b.getTitle()));
    }

    private void findAllByPriceLessThanAndGreaterThan(float a, float b) {
        this.bookService.findAllByPriceLessThanAndGreaterThan(a, b).forEach(System.out::println);
    }

    private void printTitlesByEditionTypeAndCopies(String editionType, int copies) {
        this.bookService.findAllByEditionTypeAndCopies(editionType, copies).forEach(System.out::println);
    }

    private void printTitlesByAgeRestriction(String ageRestriction) {
        this.bookService.findAllBookTitlesByAgeRestriction(ageRestriction.toUpperCase()).forEach(System.out::println);
    }

//    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
//        bookService.findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName).forEach(System.out::println);
//    }
//
//    private void printAllAuthorsAndNumberOfTheirBooks() {
//        authorService.getAllAuthorsOrderByCountOfTheirBooks().forEach(System.out::println);
//    }
//
//    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
//        bookService.findAllAuthorsWithBooksWithReleaseDateBeforeYear(year).forEach(System.out::println);
//    }
//
//    private void printAllBooksAfterYear(int year) {
//        bookService.findAllBooksAfterYear(year).stream().map(Book::getTitle).forEach(System.out::println);
//    }
//
//    private void seedData() throws IOException {
//        categoryService.seedCategories();
//        authorService.seedAuthors();
//        bookService.seedBooks();
//    }
}
