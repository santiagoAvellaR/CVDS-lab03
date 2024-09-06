package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.Library;
import edu.eci.cvds.tdd.library.book.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryTest {
    private Library library = new Library();

    @BeforeEach
    void setUp(){
        library = new Library();
    }

    // addBook Tests
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
    @Test
    void ShouldNotAddTwoDifferentlyTitledBooksWithTheSameID(){
        Book book = new Book("El amor en los tiempos del cólera", " Gabriel García Márquez","1-412-142d");
        Book book1 = new Book("Cien años de soledad", " Gabriel García Márquez","1-412-142d");
        library.addBook(book);
        assertFalse(library.addBook(book1));
    }
    @Test
    void ShouldNotAddTwoBooksFromDifferentAuthorsWithTheSameID(){
        Book book = new Book("Matematicas para ingenieros", "Pedro Sanchez","1-412-114d");
        Book book1 = new Book("Matematicas para ingenieros", "Oscar Rodriguez","1-412-114d");
        library.addBook(book);
        assertFalse(library.addBook(book1));
    }

    // loanBook Tests
    @Test
    void shouldNotCreateALoanForANonExistentBook(){
        assertNull(library.loanABook("123456789", "123-123-123-1"));
    }
    @Test
    void shouldNotCreateALoanForANonExistentUser(){
        assertNull(library.loanABook("123456789", "123-123-123-1"));
    }
    @Test
    void shouldNotCreateALoanForAnUnavailableBook(){
        Book book = new Book("Español", "Rosa","1-143-456f");
        library.addBook(book);
        User user1 = new User("Daniel Fernando Aldana Bueno", "1234567890");
        library.addUser(user1);
        User user2 = new User("Santiago Avellaneda Rodriguez", "9876543210");
        library.addUser(user2);
        library.loanABook("1234567890", "1-143-456f");
        assertNull(library.loanABook("9876543210", "1-143-456f"));
    }
    @Test
    void shouldNotCreateALoanForABookThatUserAlreadyLoaned(){
        Book book = new Book("Español", "Rosa","1-143-456f");
        library.addBook(book);
        User user1 = new User("Daniel Fernando Aldana Bueno", "1234567890");
        library.addUser(user1);
        library.loanABook("1234567890", "1-143-456f");
        assertNull(library.loanABook("1234567890", "1-143-456f"));
    }
    @Test
    void shouldCreateALoan(){
        Book book = new Book("Español", "Rosa","1-143-456f");
        library.addBook(book);
        User user1 = new User("Daniel Fernando Aldana Bueno", "1234567890");
        library.addUser(user1);
        assertNotNull(library.loanABook("1234567890", "1-143-456f"));
    }

    // returnLoan Tests
    @Test
    void shouldReturnLoan() {
        Book book = new Book("Español", "Rosa","1-143-456f");
        library.addBook(book);
        User user1 = new User("Daniel Fernando Aldana Bueno", "1234567890");
        library.addUser(user1);
        Loan loan = library.loanABook("1234567890", "1-143-456f");
        assertNotNull(library.returnLoan(loan));
    }
    @Test
    void shouldReturnNull() {
        Book book = new Book( "El amor en los tiempos del cólera",  "Gabriel García Márquez", "1-412-142d");
        library.addBook(book);
        User user1 = new User( "Santiago Avellaneda Rodríguez",  "1234567890");
        library.addUser(user1);
        Loan loan = library.loanABook(  "1234567890", "1-143-456f");
        assertNull(library.returnLoan(loan));
    }

    @Test
    void shouldReturnLoanStatusReturned() {
        Book book = new Book( "El amor en los tiempos del cólera",  "Gabriel García Márquez",  "1-412-142d");
        library.addBook(book);
        User user1 = new User("Santiago Avellaneda Rodríguez",  "1234567890");
        library.addUser(user1);
        Loan loan = library.loanABook(  "1234567890",  "1-412-142d");
        library.returnLoan(loan);
        assertEquals(loan.getStatus(), LoanStatus.RETURNED);
    }
    @Test
    void shouldIncreaseTheNumberOfBooks() {
        Book book = new Book("Matemáticas para ingenieros", "Pedro Sanchez",  "1-412-114d");
        library.addBook(book);
        library.addBook(book);
        User user1 = new User(  "Santiago Avellaneda Rodríguez",  "1234567890");
        library.addUser(user1);
        Loan loan = library.loanABook(  "1234567890", "1-412-114d");
        assertEquals(library.availableBooks(book),  1);
        library.returnLoan(loan);
        assertEquals(library.availableBooks(book),  2);
    }
}
