package edu.eci.cvds.tdd.library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.eci.cvds.tdd.library.Library;
import edu.eci.cvds.tdd.library.book.Book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TestLibrary {
    private Library library = new Library();
    @BeforeEach
    void setUp(){
        library = new Library();
    }
    @Test
    void shouldntAddaNullObject(){
        assertFalse(library.addBook(null));
    }
    @Test
    void shouldAddANewBook(){
        Book book = new Book("Matematicas", "Raul","1-123-456e");
        assertTrue(library.addBook(book));
    }
    @Test
    void shouldAddAnExistenBook(){
        Book book = new Book("Español", "Rosa","1-143-456f");
        library.addBook(book);
        assertTrue(library.addBook(book));

    }
}
