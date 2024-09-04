package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {
    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
        //TODO Implement the logic to add a new book into the map.
        if(book == null)
        {
            return false;
        }
        for(Book b : books.keySet()){
            if (book.equals(b) && (!(book.getTittle().equals(b.getTittle())) || !(book.getAuthor().equals(b.getAuthor())))){
                return false;
            }
        }

        if(books.containsKey(book)){
            Integer newValue = books.get(book) + 1;
            books.put(book, newValue);
            return true;
        }
        books.put(book, 1);
        return true;

    }
    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        //TODO Implement the login of loan a book to a user based on the UserId and the isbn.
        // verify user existance
        User user = searchUser(userId);
        if (user == null){return null;}
        Book book = searchBook(isbn);
        if (book == null){return null;}
        if (userHasALoanOfBook(userId, book)){return null;}
        if (availableBooks(book)<=0){return null;}
        books.put(book, books.get(book) - 1);
        Loan loan = new Loan(book, user);
        loans.add(loan);
        return loan;
    }

    /**
     * Searches for a user in the list of users by their user ID.
     *
     * @param userId The ID of the user to search for.
     * @return The user object if found; null otherwise.
     */
    private User searchUser(String userId) {
        User user = null;
        for (User u : users) {
            if (u.getId().equals(userId)) {
                user = u;
                break;
            }
        }
        return user;
    }

    /**
     * Searches for a book in the collection of books by its ISBN.
     *
     * @param isbn The ISBN of the book to search for.
     * @return The book object if found; null otherwise.
     */
    private Book searchBook(String isbn) {
        Book book = null;
        for (Book b : books.keySet()) {
            if (b.getIsbn().equals(isbn)) {
                book = b;
                break;
            }
        }
        return book;
    }

    /**
     * Checks if a user already has a loan for a specific book.
     *
     * @param userId The ID of the user.
     * @param book   The book to check for an existing loan.
     * @return True if the user already has a loan for the book; false otherwise.
     */
    private boolean userHasALoanOfBook(String userId, Book book) {
        for (Loan l : loans) {
            User lOwner = l.getUser();
            if (lOwner.getId().equals(userId) && l.getBook().equals(book)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Gets the number of available copies of a specific book.
     *
     * @param book The book to check for available copies.
     * @return The number of available copies of the book; 0 if the book is not in the collection.
     */

    public Integer availableBooks(Book book) {
        if (books.containsKey(book)){
            return books.get(book);
        }
        return 0;
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        //TODO Implement the login of loan a book to a user based on the UserId and the isbn.
        return null;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

}