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

// import java.net.URL;
import javax.swing.*;
import java.awt.*;

public class LibraryManagementUI {

    // private LibraryManagementSystem libraryManagementSystem;
    private Library library;
    private JFrame frame;
    JComboBox<String> searchTypeDropdown;
    JTextField bookTitleField;

    public LibraryManagementUI(Library library) {
        this.library = library;
    }

    public void createAndShowUI() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());


        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // JPanel inputPanel = new JPanel(new BorderLayout());
        // JLabel bookTitleLabel = new JLabel("Book Title:");
        // bookTitleField = new JTextField(20);
        // inputPanel.add(bookTitleLabel, BorderLayout.WEST);
        // inputPanel.add(bookTitleField, BorderLayout.CENTER);


        JPanel searchPanel = new JPanel(new BorderLayout());
        JLabel searchTypeLabel = new JLabel("Search Type:");
        String[] searchTypes = {"Title", "Author", "ISBN"};
        searchTypeDropdown = new JComboBox<>(searchTypes);
        JButton searchBookButton = new JButton("Search Book");
        searchPanel.add(searchTypeLabel, BorderLayout.WEST);
        searchPanel.add(searchTypeDropdown, BorderLayout.CENTER);
        searchPanel.add(searchBookButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");
        buttonPanel.add(borrowButton, BorderLayout.NORTH);
        buttonPanel.add(returnButton, BorderLayout.SOUTH);

        JPanel bookPanel = new JPanel(new BorderLayout());
        JButton addBookButton = new JButton("Add Book");
        // JButton searchBookButton = new JButton("Search Book");
        JButton removeBookButton = new JButton("Remove Book");
        bookPanel.add(addBookButton, BorderLayout.NORTH);
        // patronPanel.add(searchPatronButton, BorderLayout.CENTER);
        bookPanel.add(removeBookButton, BorderLayout.SOUTH);

        JPanel patronPanel = new JPanel(new BorderLayout());
        JButton addPatronButton = new JButton("Add Patron");
        JButton searchPatronButton = new JButton("Search Patron");
        JButton removePatronButton = new JButton("Remove Patron");
        patronPanel.add(addPatronButton, BorderLayout.NORTH);
        patronPanel.add(searchPatronButton, BorderLayout.CENTER);
        patronPanel.add(removePatronButton, BorderLayout.SOUTH);

        panel.add(titleLabel, BorderLayout.NORTH);
        // panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(searchPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(bookPanel, BorderLayout.WEST);
        panel.add(patronPanel, BorderLayout.EAST);

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String bookTitle = bookTitleField.getText();
                addBook();
            }
        });

        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String searchTerm = bookTitleField.getText();
                // SearchType searchType = getSelectedSearchType(); // Implement this method to retrieve the selected search type
                
                searchBook();
            }
        });

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrowBook();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String searchTerm = bookTitleField.getText();
                // SearchType searchType = getSelectedSearchType(); // Implement this method to retrieve the selected search type
                
                removeBook();
            }
        });

        addPatronButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                addPatron();
            }
        });

        searchPatronButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPatron();
            }
        });

        removePatronButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                removePatron();
            }
        });

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    

    private void addBook() {
        JFrame addBookFrame = new JFrame("Add Book");
        addBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel addBookPanel = new JPanel();
        addBookPanel.setLayout(new GridLayout(4, 2));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField();
        JLabel isbnLabel = new JLabel("ISBN:");
        JTextField isbnField = new JTextField();
    
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String isbn = isbnField.getText();

                Book book = new Book(title, author, isbn);
                library.addBook(book);

                JOptionPane.showMessageDialog(addBookFrame, "Book added successfully!");

                addBookFrame.dispose();
            }
        });
    
        addBookPanel.add(titleLabel);
        addBookPanel.add(titleField);
        addBookPanel.add(authorLabel);
        addBookPanel.add(authorField);
        addBookPanel.add(isbnLabel);
        addBookPanel.add(isbnField);
        addBookPanel.add(saveButton);

        addBookFrame.getContentPane().add(addBookPanel, BorderLayout.CENTER);
        addBookFrame.pack();
        addBookFrame.setVisible(true);
    }    

    private void searchBook(/*String searchTerm, SearchType searchType*/) {
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
    
                if (searchValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Search value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Retry input
                }
                if (searchType == SearchType.TITLE && isNumeric(searchValue)) {
                    JOptionPane.showMessageDialog(null, "Invalid search term for title. Please enter a string.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
            } else {
                searchType = SearchType.ISBN;
                searchValue = JOptionPane.showInputDialog(null, "Enter book ISBN:");
    
                if (searchValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Search value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Retry input
                }
                if (searchType == SearchType.ISBN && !isNumeric(searchValue)) {
                    JOptionPane.showMessageDialog(null, "Invalid search term for ISBN. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
            }
    
            break; // Exit the loop if valid input is provided
        }


        // Perform search logic
        List<Book> foundBooks = library.findBooks(searchValue, searchType);
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
    
                if (searchValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Search value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Retry input
                }
            } else {
                searchType = SearchType.ISBN;
                searchValue = JOptionPane.showInputDialog(null, "Enter book ISBN:");
    
                if (searchValue.isEmpty()) {
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

    private void removeBook(/*String title, SearchType searchType*/){
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
    
                if (searchValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Search value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Retry input
                }
                if (searchType == SearchType.TITLE && isNumeric(searchValue)) {
                    JOptionPane.showMessageDialog(null, "Invalid search term for title. Please enter a string.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
            } else {
                searchType = SearchType.ISBN;
                searchValue = JOptionPane.showInputDialog(null, "Enter book ISBN:");
    
                if (searchValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Search value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Retry input
                }
                if (searchType == SearchType.ISBN && !isNumeric(searchValue)) {
                    JOptionPane.showMessageDialog(null, "Invalid search term for ISBN. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
            }
    
            break; // Exit the loop if valid input is provided
        }


        // if title is not empty and searcg type is ISBN, check whether book is exist with ISBN.
        List<Book> foundBooks = library.findBooks(searchValue, searchType);
        if(!foundBooks.isEmpty()){
            library.removeBook(searchValue); // remove by ISBN
            JOptionPane.showMessageDialog(null, "Remove Book successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Book not found. Please retype the ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPatron() {
        String name = JOptionPane.showInputDialog(frame, "Enter patron name:");
        String contactInfo = JOptionPane.showInputDialog(frame, "Enter patron contact information:");
        if (name != null && contactInfo != null) {
            Patron patron = new Patron(name, contactInfo);
            library.addPatron(patron);
            JOptionPane.showMessageDialog(frame, "Patron added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void searchPatron() {
        String contactInfo = JOptionPane.showInputDialog(frame, "Enter patron contact information:");
        if (contactInfo != null) {
            Patron patron = library.findPatron(contactInfo);
            if (patron != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Patron Details:\n");
                sb.append("Name: ").append(patron.getName()).append("\n");
                sb.append("Contact Information: ").append(patron.getContactInfo()).append("\n");
                sb.append("Borrowed Books:\n");
                for (Book book : patron.getBorrowedBooks()) {
                    sb.append(book.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, sb.toString(), "Patron Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Patron not found.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void removePatron() {
        String contactInfo = JOptionPane.showInputDialog(frame, "Enter patron contact information:");
        if (contactInfo != null) {
            Patron patron = library.findPatron(contactInfo);
            if (patron != null) {
                library.removePatron(contactInfo);
                JOptionPane.showMessageDialog(frame, "Patron removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Patron not found.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }
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


    public static void main(String[] args) {
        Library library = new Library();
        // LibraryManagementUI ui = new LibraryManagementUI(library);
        // ui.createAndShowUI();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryManagementUI(library).createAndShowUI();
            }
        });
    }
}