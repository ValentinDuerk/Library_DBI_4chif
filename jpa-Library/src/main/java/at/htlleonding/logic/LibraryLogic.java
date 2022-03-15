package at.htlleonding.logic;

import at.htlleonding.persistence.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LibraryLogic {

    @Inject
    private LibraryRepository repository;

    @Transactional
    public void importLibraryData(String[][] authorData, String[][] bookData, String[][] topicData) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");

        for (String[] adata : authorData) {
            Author a = repository.getAuthorByLastName(adata[1]);
            if (a == null) {
                a = new Author();
                a.setFirstName(adata[0]);
                a.setLastName(adata[1]);
                a.setDateBirth(LocalDate.parse(adata[2], dtf));
                if (adata[3] != null && adata[3] != "") {
                    a.setDateDeath(LocalDate.parse(adata[3], dtf));
                }
                repository.add(a);
            }
        }

        for (String[] bdata : bookData) {
            Author a = repository.getAuthorByLastName(bdata[0]);
            Book b = repository.getBook(bdata[0], bdata[1]);
            if (b == null) {
                b = new Book();
                b.setTitle(bdata[1]);

                repository.add(b, a, true);

                Genre g = repository.getGenre(bdata[2]);
                if (g == null) {
                    g = new Genre(bdata[2]);
                }
                repository.add(b, g);

                for (int i = 3; i < bdata.length; i++) {
                    Author adda = repository.getAuthorByLastName(bdata[i]);
                    if (adda != null)
                        repository.add(b, adda, false);
                }
            }
        }

        for (String[] tdata : topicData) {
            Author a = repository.getAuthorByLastName(tdata[0]);
            Book b = repository.getBook(tdata[0], tdata[1]);
            for (int i = 2; i < tdata.length; i++) {
                Topic t = repository.getTopic(tdata[i]);
                if (t == null) {
                    t = new Topic(tdata[i]);
                }
                repository.add(b, t);
            }
        }
    }

    @Transactional
    public String[][] getAuthors() {
        var aa = repository.getAllAuthors();
        var result = new ArrayList<String[]>();
        var dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        for (var a : aa) {
            result.add(new String[] {a.getFirstName(), a.getLastName(), a.getDateBirth().format(dtf), a.getDateDeath() != null ? a.getDateDeath().format(dtf) : ""});
        }
        return result.toArray(new String[aa.size()][]);
    }

    @Transactional
    public String[][] getBooks() {
        var bb = repository.getAllBooks(true);
        var result = new ArrayList<String[]>();
        for(var b: bb) {
            result.add(new String[] { b.getAuthors().iterator().next().getLastName(), b.getTitle(), b.getGenre().getKeyword() });
        }
        return result.toArray(new String[bb.size()][]);
    }

    //returns an array of string-arrays of book-data: {"author-last-name", "title", ["topic"]+}
    // (only one author is reported, preferably the primary one)
    // one of the reported topics must be equal to the keyword argument
    @Transactional
    public String[][] getBooksOfTopic(String keyword)
    {
        List<Book> bb = repository.getBooksOfTopic(keyword);
        var result = new ArrayList<String[]>();
        for(var b : bb) {
            var bookData = new ArrayList<String>();
            bookData.add(b.getAuthors().iterator().next().getLastName());
            bookData.add(b.getTitle());
            for(var t : b.getTopics()) {
                bookData.add(t.getKeyword());
            }
            result.add(bookData.toArray(new String[bookData.size()]));
        }
        return result.toArray(new String[result.size()][]);
    }

    //returns an array of string-arrays of book-data: {"author-last-name", "title", "genre"}
    // (only one author is reported, preferably the primary one)
    // the genre.keyword must be equal to the keyword argument
    @Transactional
    public String[][] getBooksOfGenre(String keyword)
    {
        List<Book> bb = repository.getBooksOfGenre(keyword);
        var result = new ArrayList<String[]>();
        for(var b : bb) {
            var bookData = new ArrayList<String>();
            bookData.add(b.getAuthors().iterator().next().getLastName());
            bookData.add(b.getTitle());
            bookData.add(b.getGenre().getKeyword());

            result.add(bookData.toArray(new String[bookData.size()]));
        }
        return result.toArray(new String[result.size()][]);
    }

    //returns an array of string-arrays of book-data: {"author-last-name", "author-first-name", "title", "genre", ["topic"]+}
    // (only one author is reported, preferably the primary one)
    @Transactional
    public String[][] getInventory() {
        List<Book> bb = repository.getInventory();
        var result = new ArrayList<String[]>();
        for(var b : bb) {
            var bookData = new ArrayList<String>();
            var a =b.getAuthors().iterator().next();
            bookData.add(a.getLastName());
            bookData.add(a.getFirstName());
            bookData.add(b.getTitle());
            bookData.add(b.getGenre().getKeyword());
            for(var t : b.getTopics()) {
                bookData.add(t.getKeyword());
            }
            result.add(bookData.toArray(new String[bookData.size()]));
        }
        return result.toArray(new String[result.size()][]);
    }
}
