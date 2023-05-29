import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Library {
    private Map<String, Book> books;
    private Map<String, Patron> patrons;

    public Library() {
        this.books = new HashMap<>();
        this.patrons = new HashMap<>();
    }

    // Book operations

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public void removeBook(String isbn) {
        books.remove(isbn);
    }

    public List<Book> findBooks(String searchTerm, SearchType searchType) {
        List<Book> foundBooks = new ArrayList<>();
        if (searchTerm.isEmpty()) {
            return foundBooks;
        }
    
        for (Book book : books.values()) {
            switch (searchType) {
                case TITLE:
                    if (book.getTitle().equalsIgnoreCase(searchTerm)) {
                        foundBooks.add(book);
                    }
                    break;
                case AUTHOR:
                    if (book.getAuthor().equalsIgnoreCase(searchTerm)) {
                        foundBooks.add(book);
                    }
                    break;
                case ISBN:
                    if (book.getIsbn().equalsIgnoreCase(searchTerm)) {
                        foundBooks.add(book);
                    }
                    break;
            }
        }
    
        return foundBooks;
    }
    

    // Patron operations

    public void addPatron(Patron patron) {
        patrons.put(patron.getContactInfo(), patron);
    }

    public void removePatron(String contactInfo) {
        patrons.remove(contactInfo);
    }

    public Patron findPatron(String contactInfo) {
        return patrons.get(contactInfo);
    }

    // Other operations

    public void borrowBook(String isbn, String contactInfo) {
        List<Book> books = findBooks(isbn, SearchType.ISBN);
        Patron patron = findPatron(contactInfo);
    
        boolean bookBorrowed = false;
    
        for (Book book : books) {
            if (book.isAvailable() && patron != null) {
                book.setAvailable(false);
                patron.getBorrowedBooks().add(book);
                bookBorrowed = true;
            }
        }
    
        if (bookBorrowed) {
            System.out.println("Book successfully borrowed.");
        } else {
            System.out.println("No available books found or patron not found.");
        }
    }

    public void returnBook(String isbn, String contactInfo) {
        List<Book> books = findBooks(isbn, SearchType.ISBN);
        Patron patron = findPatron(contactInfo);
    
        boolean bookReturned = false;
    
        for (Book book : books) {
            if (!book.isAvailable() && patron != null && patron.getBorrowedBooks().contains(book)) {
                book.setAvailable(true);
                patron.getBorrowedBooks().remove(book);
                bookReturned = true;
            }
        }
    
        if (bookReturned) {
            System.out.println("Book successfully returned.");
        } else {
            System.out.println("No borrowed books found or patron not found.");
        }
    }
    
}

