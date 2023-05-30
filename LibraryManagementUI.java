import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import java.net.URL;
import javax.swing.*;
import java.awt.*;

public class LibraryManagementUI {

    // private LibraryManagementSystem libraryManagementSystem;
    private Library library;
    JComboBox<String> searchTypeDropdown;
    JTextField bookTitleField;

    public LibraryManagementUI(Library library) {
        this.library = library;
    }

    public void createAndShowUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        JLabel titleLabel = new JLabel("Library Management System");
        JLabel bookTitleLabel = new JLabel("Book Title:");
        bookTitleField = new JTextField(20);
        JButton addButton = new JButton("Add Book");
        JButton searchButton = new JButton("Search Book");
        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");
        JButton removeBookButton = new JButton("Remove Book");


        
        searchTypeDropdown = new JComboBox<>();
        searchTypeDropdown.addItem("Title");
        searchTypeDropdown.addItem("Author");
        searchTypeDropdown.addItem("ISBN");
                


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = bookTitleField.getText();
                addBook(bookTitle);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = bookTitleField.getText();
                SearchType searchType = getSelectedSearchType(); // Implement this method to retrieve the selected search type
                
                searchBook(searchTerm, searchType);
            }
        });

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = bookTitleField.getText();
                // SearchType searchType = getSelectedSearchType(); // Implement this method to retrieve the selected search type
                
                borrowBook();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = bookTitleField.getText();
                // SearchType searchType = getSelectedSearchType(); // Implement this method to retrieve the selected search type
                
                returnBook();
            }
        });

        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = bookTitleField.getText();
                SearchType searchType = getSelectedSearchType(); // Implement this method to retrieve the selected search type
                
                removeBook(searchTerm, searchType);
            }
        });
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(bookTitleLabel, BorderLayout.WEST);
        inputPanel.add(bookTitleField, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(new JLabel("Search Type:"), BorderLayout.WEST);
        searchPanel.add(searchTypeDropdown, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(addButton, BorderLayout.NORTH);
        buttonPanel.add(borrowButton, BorderLayout.WEST);
        buttonPanel.add(returnButton, BorderLayout.SOUTH);
        buttonPanel.add(removeBookButton, BorderLayout.EAST);
        

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(searchButton, BorderLayout.SOUTH);
        panel.add(searchPanel, BorderLayout.EAST);
        panel.add(buttonPanel, BorderLayout.WEST);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    

    private void addBook(String title) {
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Title cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        JTextField authorField = new JTextField(20);
        JTextField isbnField = new JTextField(20);
    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(new JLabel("Author:"), BorderLayout.WEST);
        inputPanel.add(authorField, BorderLayout.CENTER);
        inputPanel.add(new JLabel("ISBN:"), BorderLayout.EAST);
        inputPanel.add(isbnField, BorderLayout.SOUTH);
    
        int option = JOptionPane.showOptionDialog(null, inputPanel, "Add Book",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
    

        if (option == JOptionPane.OK_OPTION) {
            String author = authorField.getText();
            // if (author.isEmpty()) {
            //     JOptionPane.showMessageDialog(null, "Author cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            //     return;
            // }

            // if (isNumeric(author)) {
            //     JOptionPane.showMessageDialog(null, "Invalid search term for author. Please enter a string.", "Error", JOptionPane.ERROR_MESSAGE);
            //     return;
            // }

            // String isbn = isbnField.getText();
            // if (isbn.isEmpty()) {
            //     JOptionPane.showMessageDialog(null, "ISBN cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            //     return;
            // }
                    
            // if (!isNumeric(isbn)) {
            //     JOptionPane.showMessageDialog(null, "Invalid search term for ISBN. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            //     return;
            // }

            while (author.isEmpty() && option == JOptionPane.OK_OPTION){
                JOptionPane.showMessageDialog(null, "Author cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                
                authorField.setText("");

                inputPanel = new JPanel();
                inputPanel.setLayout(new BorderLayout());
                inputPanel.add(new JLabel("Author:"), BorderLayout.WEST);
                inputPanel.add(authorField, BorderLayout.CENTER);
                inputPanel.add(new JLabel("ISBN:"), BorderLayout.EAST);
                inputPanel.add(isbnField, BorderLayout.SOUTH);
            
                option = JOptionPane.showOptionDialog(null, inputPanel, "Add Book",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                author = authorField.getText();

                if(option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION){
                    return;
                }
            }

            
            while (isNumeric(author) && option == JOptionPane.OK_OPTION){
                JOptionPane.showMessageDialog(null, "Invalid search term for author. Please enter a string.", "Error", JOptionPane.ERROR_MESSAGE);
                
                authorField.setText("");

                inputPanel = new JPanel();
                inputPanel.setLayout(new BorderLayout());
                inputPanel.add(new JLabel("Author:"), BorderLayout.WEST);
                inputPanel.add(authorField, BorderLayout.CENTER);
                inputPanel.add(new JLabel("ISBN:"), BorderLayout.EAST);
                inputPanel.add(isbnField, BorderLayout.SOUTH);
            
                option = JOptionPane.showOptionDialog(null, inputPanel, "Add Book",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                author = authorField.getText();

                if(option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION){
                    return;
                }
            }
    
            String isbn = isbnField.getText();
            while (isbn.isEmpty()) {
                JOptionPane.showMessageDialog(null, "ISBN cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                
                isbnField.setText("");
                isbn = isbnField.getText();

                inputPanel = new JPanel();
                inputPanel.setLayout(new BorderLayout());
                inputPanel.add(new JLabel("Author:"), BorderLayout.WEST);
                inputPanel.add(authorField, BorderLayout.CENTER);
                inputPanel.add(new JLabel("ISBN:"), BorderLayout.EAST);
                inputPanel.add(isbnField, BorderLayout.SOUTH);
            
                option = JOptionPane.showOptionDialog(null, inputPanel, "Add Book",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                isbn = isbnField.getText();
            
                if(option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION){
                    return;
                }
            }
                    
            while (!isNumeric(isbn)) {
                JOptionPane.showMessageDialog(null, "Invalid search term for ISBN. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                
                isbnField.setText("");
                
                inputPanel = new JPanel();
                inputPanel.setLayout(new BorderLayout());
                inputPanel.add(new JLabel("Author:"), BorderLayout.WEST);
                inputPanel.add(authorField, BorderLayout.CENTER);
                inputPanel.add(new JLabel("ISBN:"), BorderLayout.EAST);
                inputPanel.add(isbnField, BorderLayout.SOUTH);
            
                option = JOptionPane.showOptionDialog(null, inputPanel, "Add Book",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                isbn = isbnField.getText();
            
                if(option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION){
                    return;
                }
            }
    
            Book book = new Book(title, author, isbn);
            library.addBook(book);
            JOptionPane.showMessageDialog(null, "Book added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }    

    private SearchType getSelectedSearchType() {
        String selectedSearchType = (String) searchTypeDropdown.getSelectedItem();

        if (selectedSearchType.equals("Title")) {
            return SearchType.TITLE;
        } else if (selectedSearchType.equals("Author")) {
            return SearchType.AUTHOR;
        } else if (selectedSearchType.equals("ISBN")) {
            return SearchType.ISBN;
        } else {
            throw new IllegalArgumentException("Invalid search type selected");
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private void searchBook(String searchTerm, SearchType searchType) {
        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Search term cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (searchType == SearchType.AUTHOR && isNumeric(searchTerm)) {
            JOptionPane.showMessageDialog(null, "Invalid search term for author. Please enter a string.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (searchType == SearchType.ISBN && !isNumeric(searchTerm)) {
            JOptionPane.showMessageDialog(null, "Invalid search term for ISBN. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Perform search logic
        List<Book> foundBooks = library.findBooks(searchTerm, searchType);
        // Book foundBook = null;
        // for (Book book : library.getBooks()) {
        //     if (book.getTitle().equalsIgnoreCase(bookTitle)) {
        //         foundBook = book;
        //         break;
        //     }
        // }
        
        // Display search results in a dialog box
        if (!foundBooks.isEmpty()) {
            StringBuilder resultMessage = new StringBuilder("Books found:\n");
            for (Book book : foundBooks) {
                resultMessage.append("Title: ").append(book.getTitle()).append("\n")
                        .append("Author: ").append(book.getAuthor()).append("\n")
                        .append("ISBN: ").append(book.getIsbn()).append("\n")
                        .append("Availability: ").append(book.isAvailable() ? "Available" : "Not available").append("\n\n");
            }

            JOptionPane.showMessageDialog(null, resultMessage.toString(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No books found.", "Search Result", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void borrowBook() {
        Object[] options = {"Title", "ISBN"};
        int choice = JOptionPane.showOptionDialog(null, "Choose search method:", "Borrow Book",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    
        if (choice == JOptionPane.CLOSED_OPTION) {
            return; // Return to main window if "Cancel" is clicked
        }
    
        String searchValue = "";
        SearchType searchType;
    
        while (true) {
            if (choice == 0) {
                searchType = SearchType.TITLE;
                searchValue = JOptionPane.showInputDialog(null, "Enter book title:");
    
                if (searchValue == null || searchValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Search value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Retry input
                }
            } else {
                searchType = SearchType.ISBN;
                searchValue = JOptionPane.showInputDialog(null, "Enter book ISBN:");
    
                if (searchValue == null || searchValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Search value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Retry input
                }
            }
    
            break; // Exit the loop if valid input is provided
        }
    
        List<Book> books = library.findBooks(searchValue, searchType);
    
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books available with the provided search value.", "Unavailable", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        JTextField contactInfoField = new JTextField(20);
    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(new JLabel("Contact Information:"), BorderLayout.WEST);
        inputPanel.add(contactInfoField, BorderLayout.CENTER);
    
        int option = JOptionPane.showOptionDialog(null, inputPanel, "Borrow Book",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
    
        if (option == JOptionPane.CANCEL_OPTION) {
            return; // Return to main window if "Cancel" is clicked
        }
    
        String contactInfo = contactInfoField.getText();
    
        while (contactInfo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Contact information cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            option = JOptionPane.showOptionDialog(null, inputPanel, "Borrow Book",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
    
            if (option == JOptionPane.CANCEL_OPTION) {
                return; // Return to main window if "Cancel" is clicked
            }
    
            contactInfo = contactInfoField.getText();
        }
    
        Patron patron = library.findPatron(contactInfo);
        boolean bookBorrowed = false;
    
        for (Book book : books) {
            if (book.isAvailable() && patron != null) {
                book.setAvailable(false);
                patron.getBorrowedBooks().add(book);
                bookBorrowed = true;
                break; // Exit the loop after borrowing the first available book
            }
        }
    
        if (bookBorrowed) {
            JOptionPane.showMessageDialog(null, "Book borrowed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (patron == null) {
                JOptionPane.showMessageDialog(null, "Patron not found with the provided contact information.", "Not Found", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No available books found.", "Unavailable", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private void returnBook() {
        Object[] options = {"Title", "ISBN"};
        int choice = JOptionPane.showOptionDialog(null, "Choose search method:", "Return Book",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    
        if (choice == JOptionPane.CLOSED_OPTION) {
            return; // Return to main window if "Cancel" is clicked
        }
    
        String searchValue = "";
        SearchType searchType;
    
        while (true) {
            if (choice == 0) {
                searchType = SearchType.TITLE;
                searchValue = JOptionPane.showInputDialog(null, "Enter book title:");
    
                if (searchValue == null || searchValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Search value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Retry input
                }
            } else {
                searchType = SearchType.ISBN;
                searchValue = JOptionPane.showInputDialog(null, "Enter book ISBN:");
    
                if (searchValue == null || searchValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Search value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Retry input
                }
            }
    
            break; // Exit the loop if valid input is provided
        }
    
        List<Book> books = library.findBooks(searchValue, searchType);
    
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books found with the provided search value.", "Not Found", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        JTextField contactInfoField = new JTextField(20);
    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(new JLabel("Contact Information:"), BorderLayout.WEST);
        inputPanel.add(contactInfoField, BorderLayout.CENTER);
    
        int option = JOptionPane.showOptionDialog(null, inputPanel, "Return Book",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
    
        if (option == JOptionPane.CANCEL_OPTION) {
            return; // Return to main window if "Cancel" is clicked
        }
    
        String contactInfo = contactInfoField.getText();
    
        while (contactInfo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Contact information cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            option = JOptionPane.showOptionDialog(null, inputPanel, "Return Book",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
    
            if (option == JOptionPane.CANCEL_OPTION) {
                return; // Return to main window if "Cancel" is clicked
            }
    
            contactInfo = contactInfoField.getText();
        }
    
        Patron patron = library.findPatron(contactInfo);
        boolean bookReturned = false;
    
        for (Book book : books) {
            if (!book.isAvailable() && patron != null && patron.getBorrowedBooks().contains(book)) {
                book.setAvailable(true);
                patron.getBorrowedBooks().remove(book);
                bookReturned = true;
            }
        }
    
        if (bookReturned) {
            JOptionPane.showMessageDialog(null, "Book returned successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (books.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No books found with the provided search value.", "Not Found", JOptionPane.WARNING_MESSAGE);
            } else if (patron == null) {
                JOptionPane.showMessageDialog(null, "Patron not found with the provided contact information.", "Not Found", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No borrowed books found.", "Unavailable", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void removeBook(String title, SearchType searchType){
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Title cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (searchType != SearchType.ISBN) {
            JOptionPane.showMessageDialog(null, "Please enter the ISBN of the book to remove", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // if title is not empty and searcg type is ISBN, check whether book is exist with ISBN.
        List<Book> foundBooks = library.findBooks(title, searchType);
        if(!foundBooks.isEmpty()){
            library.removeBook(title); // remove by ISBN
            JOptionPane.showMessageDialog(null, "Remove Book successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Book not found. Please retype the ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    public static void main(String[] args) {
        Library library = new Library();
        LibraryManagementUI ui = new LibraryManagementUI(library);
        ui.createAndShowUI();
    }
}