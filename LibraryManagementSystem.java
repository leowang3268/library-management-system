import java.util.Scanner;

public class LibraryManagementSystem {
    // public static void main(String[] args) {
    //     Library library = new Library();
    //     UserInterface userInterface = new UserInterface(library);
    //     userInterface.run();
    // }

    private Scanner scanner;
    private Library library;

    public LibraryManagementSystem(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    private void addBook() {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        while(isEmptyInput(title))
        {
            System.out.println("Title cannot be empty!"); 
            System.out.print("Enter the title of the book: ");
            title = scanner.nextLine();
        }
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        while(isEmptyInput(author))
        {
            System.out.println("Author cannot be empty!"); 
            System.out.print("Enter the author of the book: ");
            author = scanner.nextLine();
        }
        System.out.print("Enter the ISBN of the book: ");
        String isbn = scanner.nextLine();
        while(isEmptyInput(isbn))
        {
            System.out.println("ISBN cannot be empty!"); 
            System.out.print("Enter the ISBN of the book: ");
            isbn = scanner.nextLine();
        }

        Book book = new Book(title, author, isbn);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }

    private boolean isEmptyInput(String input) {
        return input.trim().isEmpty();
    }
}