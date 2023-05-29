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
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(bookTitleLabel, BorderLayout.WEST);
        inputPanel.add(bookTitleField, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(new JLabel("Search Type:"), BorderLayout.WEST);
        searchPanel.add(searchTypeDropdown, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(addButton, BorderLayout.CENTER);
        

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
            if (author.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Author cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isNumeric(author)) {
                JOptionPane.showMessageDialog(null, "Invalid search term for author. Please enter a string.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            String isbn = isbnField.getText();
            if (isbn.isEmpty()) {
                JOptionPane.showMessageDialog(null, "ISBN cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
                    
            if (!isNumeric(isbn)) {
                JOptionPane.showMessageDialog(null, "Invalid search term for ISBN. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
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
    

    public static void main(String[] args) {
        Library library = new Library();
        LibraryManagementUI ui = new LibraryManagementUI(library);
        ui.createAndShowUI();
    }
}
