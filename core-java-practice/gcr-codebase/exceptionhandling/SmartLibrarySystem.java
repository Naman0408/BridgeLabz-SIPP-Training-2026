/**
 * Smart Library System - Handles separate exception handling for null and invalid index
 * Books are stored as strings in an array: String books[] = {"Java","Python",null,"C++"};
 * Method: getBookLength(int index) returns the length of the book name
 * Task: Handle invalid index and null book entry separately with custom messages
 */
public class SmartLibrarySystem {
    
    private static class BookRecord {
        String bookName;
        String author;
        String isbn;
        int copies;
        
        BookRecord(String name, String author, String isbn, int copies) {
            this.bookName = name;
            this.author = author;
            this.isbn = isbn;
            this.copies = copies;
        }
    }
    
    private BookRecord[] books;
    private int bookCount;
    private static final int MAX_BOOKS = 100;
    
    /**
     * Constructor - initialize library system
     */
    public SmartLibrarySystem() {
        this.books = new BookRecord[MAX_BOOKS];
        this.bookCount = 0;
    }
    
    /**
     * Add a book to the library
     */
    public void addBook(String name, String author, String isbn, int copies) {
        try {
            if (bookCount >= MAX_BOOKS) {
                throw new IllegalStateException("Library is full. Cannot add more books.");
            }
            
            // Allow null book names for testing purposes
            books[bookCount] = new BookRecord(name, author, isbn, copies);
            System.out.println("✓ Book added: " + (name != null ? name : "[Unknown]"));
            bookCount++;
            
        } catch (IllegalStateException e) {
            System.out.println("❌ ERROR - " + e.getMessage());
        }
    }
    
    /**
     * Get book length (name length) - handles exceptions separately
     * Returns length if valid, -1 if exception occurs
     * Demonstrates separate handling for:
     * 1. Invalid index (ArrayIndexOutOfBoundsException)
     * 2. Null book entry (NullPointerException)
     */
    public int getBookLength(int index) {
        try {
            // Check 1: Validate index
            if (index < 0 || index >= bookCount) {
                throw new ArrayIndexOutOfBoundsException(
                    "Invalid book index: " + index + ". Valid range: 0-" + (bookCount - 1));
            }
            
            // Check 2: Check for null book entry
            if (books[index] == null) {
                throw new NullPointerException("Book at index " + index + " is null (removed or not initialized)");
            }
            
            // Check 3: Check for null book name
            if (books[index].bookName == null) {
                throw new NullPointerException(
                    "Book name at index " + index + " is null. Cannot calculate length.");
            }
            
            // Get length if everything is valid
            return books[index].bookName.length();
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - Invalid Index: " + e.getMessage());
            System.out.println("   Possible solution: Verify the book index is within valid range.");
            return -1;
        } catch (NullPointerException e) {
            System.out.println("❌ ERROR - Null Reference: " + e.getMessage());
            System.out.println("   Possible solution: Try adding the book first or check if it's removed.");
            return -1;
        }
    }
    
    /**
     * Safe method to get book length with comprehensive error handling
     */
    public int safeGetBookLength(int index) {
        try {
            // Step 1: Check index validity
            if (index < 0 || index >= bookCount) {
                System.out.printf("Index %d is out of range (0-%d)%n", index, bookCount - 1);
                return -1;
            }
            
            // Step 2: Check if book record exists
            if (books[index] == null) {
                System.out.println("Book record at index " + index + " is null");
                return -1;
            }
            
            // Step 3: Check if book name exists
            if (books[index].bookName == null) {
                System.out.println("Book name at index " + index + " is null");
                return -1;
            }
            
            // Step 4: Return length
            return books[index].bookName.length();
            
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Get book details - handles null entries
     */
    public void getBookDetails(int index) {
        try {
            // Validate index
            if (index < 0 || index >= bookCount) {
                throw new ArrayIndexOutOfBoundsException(
                    "Invalid book index: " + index + ". Valid range: 0-" + (bookCount - 1));
            }
            
            // Check for null book record
            if (books[index] == null) {
                throw new NullPointerException("Book at index " + index + " is null");
            }
            
            BookRecord book = books[index];
            
            System.out.println("\n--- Book Details (Index " + index + ") ---");
            System.out.println("Name: " + (book.bookName != null ? book.bookName : "[NULL - Name not set]"));
            System.out.println("Author: " + (book.author != null ? book.author : "[NULL - Author not set]"));
            System.out.println("ISBN: " + (book.isbn != null ? book.isbn : "[NULL - ISBN not set]"));
            System.out.println("Copies Available: " + book.copies);
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - Array Index Out of Bounds: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("❌ ERROR - Null Pointer Exception: " + e.getMessage());
        }
    }
    
    /**
     * Search book by name - handles null entries during search
     */
    public int searchBook(String bookName) {
        System.out.println("\n--- Searching for: '" + bookName + "' ---");
        
        for (int i = 0; i < bookCount; i++) {
            try {
                // Check if book record is null
                if (books[i] == null) {
                    System.out.println("Index " + i + ": [NULL RECORD - Skipping]");
                    continue;
                }
                
                // Check if book name is null
                if (books[i].bookName == null) {
                    System.out.println("Index " + i + ": [NULL NAME - Skipping]");
                    continue;
                }
                
                // Compare names
                if (books[i].bookName.equalsIgnoreCase(bookName)) {
                    System.out.println("✓ Found at index " + i);
                    return i;
                }
                
            } catch (Exception e) {
                System.out.println("Index " + i + ": Error comparing - " + e.getMessage());
            }
        }
        
        System.out.println("✗ Book not found");
        return -1;
    }
    
    /**
     * Get book name length - handles both exceptions
     * Demonstrates the difference in exception handling
     */
    public void displayBookNameLength(int index) {
        System.out.println("\n--- Getting book name length for index " + index + " ---");
        
        int length = getBookLength(index);
        if (length != -1) {
            System.out.println("✓ Book name length: " + length + " characters");
        }
    }
    
    /**
     * Display all books with safe null checking
     */
    public void displayAllBooks() {
        System.out.println("\n========== Library Catalog ==========");
        
        if (bookCount == 0) {
            System.out.println("No books in the library.");
            return;
        }
        
        for (int i = 0; i < bookCount; i++) {
            try {
                System.out.print((i + 1) + ". ");
                
                // Check for null book record
                if (books[i] == null) {
                    System.out.println("[NULL RECORD]");
                    continue;
                }
                
                // Check for null book name
                String displayName = (books[i].bookName != null) ? books[i].bookName : "[NULL NAME]";
                String displayAuthor = (books[i].author != null) ? books[i].author : "[Unknown]";
                
                System.out.printf("%s by %s - Copies: %d%n", displayName, displayAuthor, books[i].copies);
                
            } catch (Exception e) {
                System.out.println("[ERROR: " + e.getMessage() + "]");
            }
        }
        
        System.out.println("======================================\n");
    }
    
    /**
     * Demonstrate different exception scenarios
     */
    public void demonstrateExceptions() {
        System.out.println("\n========== Exception Handling Demonstration ==========\n");
        
        // Scenario 1: Invalid index exception
        System.out.println("Scenario 1: Invalid Index Exception");
        System.out.println("Trying to access book at index 10 when only " + bookCount + " books exist:");
        displayBookNameLength(10);
        
        // Scenario 2: Null book entry exception
        System.out.println("\nScenario 2: Null Book Entry Exception");
        System.out.println("Trying to access book at index 2 which contains null:");
        displayBookNameLength(2);
        
        // Scenario 3: Valid access
        System.out.println("\nScenario 3: Valid Book Access");
        System.out.println("Accessing book at valid index 0:");
        displayBookNameLength(0);
        
        System.out.println("\n=====================================================\n");
    }
    
    // Main method to demonstrate the system
    public static void main(String[] args) {
        SmartLibrarySystem library = new SmartLibrarySystem();
        
        // Add books (similar to: String books[] = {"Java","Python",null,"C++"};)
        library.addBook("Java", "Herbert Schildt", "ISBN-001", 3);
        library.addBook("Python", "Guido van Rossum", "ISBN-002", 2);
        library.addBook(null, "Unknown Author", "ISBN-003", 0); // Null book name
        library.addBook("C++", "Bjarne Stroustrup", "ISBN-004", 4);
        library.addBook("JavaScript", "Kyle Simpson", "ISBN-005", 5);
        
        library.displayAllBooks();
        
        // Test 1: Valid book access
        System.out.println("Test 1: Get length of valid book at index 0");
        int length = library.getBookLength(0);
        System.out.println("Result: " + (length != -1 ? "Length = " + length : "Error occurred"));
        
        // Test 2: Invalid index - ArrayIndexOutOfBoundsException
        System.out.println("\nTest 2: Invalid index (ArrayIndexOutOfBoundsException)");
        length = library.getBookLength(8);
        System.out.println("Result: " + length);
        
        // Test 3: Null book entry - NullPointerException
        System.out.println("\nTest 3: Null book name (NullPointerException)");
        length = library.getBookLength(2);
        System.out.println("Result: " + length);
        
        // Test 4: Negative index
        System.out.println("\nTest 4: Negative index");
        length = library.getBookLength(-1);
        System.out.println("Result: " + length);
        
        // Test 5: Get book details with null handling
        System.out.println("\nTest 5: Get details of book with null name");
        library.getBookDetails(2);
        
        // Test 6: Get book details at invalid index
        System.out.println("\nTest 6: Get details at invalid index");
        library.getBookDetails(10);
        
        // Test 7: Search for books (handles nulls during search)
        System.out.println("\nTest 7: Search for books");
        library.searchBook("Java");
        library.searchBook("Python");
        library.searchBook("NonExistent");
        
        // Test 8: Safe method for getting length
        System.out.println("\nTest 8: Safe method for getting book length");
        System.out.println("Index 0 length: " + library.safeGetBookLength(0));
        System.out.println("Index 2 length: " + library.safeGetBookLength(2)); // Null name
        System.out.println("Index 10 length: " + library.safeGetBookLength(10)); // Out of bounds
        
        // Test 9: Comprehensive demonstration
        library.demonstrateExceptions();
    }
}
