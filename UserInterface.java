import java.util.Scanner;

class UserInterface {
    private Library library;
    private Scanner scanner;

    public UserInterface(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to the Library Management System!");

        boolean running = true;
        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Add a book");
            System.out.println("2. Remove a book");
            System.out.println("3. Add a patron");
            System.out.println("4. Remove a patron");
            System.out.println("5. Borrow a book");
            System.out.println("6. Return a book");
            System.out.println("7. Search for a book");
            System.out.println("8. Search for a patron");
            System.out.println("9. Exit");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    addPatron();
                    break;
                case 4:
                    removePatron();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    searchBook();
                    break;
                case 8:
                    searchPatron();
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for using the Library Management System. Goodbye!");
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter your choice as a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // private void addBook() {
    //     scanner.nextLine(); // Consume newline character
    //     System.out.print("Enter the title of the book: ");
    //     String title = scanner.nextLine();
    //     while(isEmptyInput(title))
    //     {
    //         System.out.println("Title cannot be empty!"); 
    //         System.out.print("Enter the title of the book: ");
    //         title = scanner.nextLine();
    //     }
    //     System.out.print("Enter the author of the book: ");
    //     String author = scanner.nextLine();
    //     while(isEmptyInput(author))
    //     {
    //         System.out.println("Author cannot be empty!"); 
    //         System.out.print("Enter the author of the book: ");
    //         author = scanner.nextLine();
    //     }
    //     System.out.print("Enter the ISBN of the book: ");
    //     String isbn = scanner.nextLine();
    //     while(isEmptyInput(isbn))
    //     {
    //         System.out.println("ISBN cannot be empty!"); 
    //         System.out.print("Enter the ISBN of the book: ");
    //         isbn = scanner.nextLine();
    //     }

    //     Book book = new Book(title, author, isbn);
    //     library.addBook(book);
    //     System.out.println("Book added successfully.");
    // }

    
    private boolean isEmptyInput(String input) {
        return input.trim().isEmpty();
    }

    private void removeBook() {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter the ISBN of the book to remove: ");
        String isbn = scanner.nextLine();
        while(isEmptyInput(isbn))
        {
            System.out.println("ISBN cannot be empty!"); 
            System.out.print("Enter the ISBN of the book to remove: ");
            isbn = scanner.nextLine();
        }
        Book book = library.findBook(isbn);
        if (book != null) {
            library.removeBook(isbn);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found. Please retype the ISBN.");
            removeBook(); // Recursively call removeBook() to re-prompt the user
        }
    }

    private void addPatron() {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter the name of the patron: ");
        String name = scanner.nextLine();
        while(isEmptyInput(name))
        {
            System.out.println("Name cannot be empty!"); 
            System.out.print("Enter the name of the patron: ");
            name = scanner.nextLine();
        }
        System.out.print("Enter the contact information of the patron: ");
        String contactInfo = scanner.nextLine();
        while(isEmptyInput(contactInfo))
        {
            System.out.println("Contact information cannot be empty!"); 
            System.out.print("Enter the contact information of the patron: ");
            contactInfo = scanner.nextLine();
        }

        Patron patron = new Patron(name, contactInfo);
        library.addPatron(patron);
        System.out.println("Patron added successfully.");
    }

    private void removePatron() {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter the contact information of the patron to remove: ");
        String contactInfo = scanner.nextLine();

        library.removePatron(contactInfo);
        System.out.println("Patron removed successfully.");
    }

    private void borrowBook() {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter the ISBN of the book to borrow: ");
        String isbn = scanner.nextLine();
        while(isEmptyInput(isbn))
        {
            System.out.println("ISBN cannot be empty!"); 
            System.out.print("Enter the ISBN of the book to borrow: ");
            isbn = scanner.nextLine();
        }
        System.out.print("Enter the contact information of the patron: ");
        String contactInfo = scanner.nextLine();
        while(isEmptyInput(contactInfo))
        {
            System.out.println("Contact information cannot be empty!"); 
            System.out.print("Enter the contact information of the patron: ");
            contactInfo = scanner.nextLine();
        }

        library.borrowBook(isbn, contactInfo);
    }

    private void returnBook() {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter the ISBN of the book to return: ");
        String isbn = scanner.nextLine();
        while(isEmptyInput(isbn))
        {
            System.out.println("ISBN cannot be empty!"); 
            System.out.print("Enter the ISBN of the book to borrow: ");
            isbn = scanner.nextLine();
        }
        System.out.print("Enter the contact information of the patron: ");
        String contactInfo = scanner.nextLine();
        while(isEmptyInput(contactInfo))
        {
            System.out.println("Contact information cannot be empty!"); 
            System.out.print("Enter the contact information of the patron: ");
            contactInfo = scanner.nextLine();
        }

        library.returnBook(isbn, contactInfo);
    }

    // private void searchBook() {
    //     scanner.nextLine(); // Consume newline character
    //     System.out.print("Enter the ISBN of the book to search: ");
    //     String isbn = scanner.nextLine();

    //     Book book = library.findBook(isbn);
    //     if (book != null) {
    //         System.out.println("Book found:");
    //         System.out.println("Title: " + book.getTitle());
    //         System.out.println("Author: " + book.getAuthor());
    //         System.out.println("ISBN: " + book.getIsbn());
    //         System.out.println("Availability: " + (book.isAvailable() ? "Available" : "Not available"));
    //     } else {
    //         System.out.println("Book not found.");
    //     }
    // }

    private void searchPatron() {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter the contact information of the patron to search: ");
        String contactInfo = scanner.nextLine();

        Patron patron = library.findPatron(contactInfo);
        if (patron != null) {
            System.out.println("Patron found:");
            System.out.println("Name: " + patron.getName());
            System.out.println("Contact Information: " + patron.getContactInfo());
            System.out.println("Borrowed Books: " + patron.getBorrowedBooks().size());
        } else {
            System.out.println("Patron not found.");
        }
    }
}