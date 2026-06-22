/**
 * Movie Seat Reservation System
 * Scenario: An array stores booked seat numbers. int seats[] = {101,102,103,104,105};
 * Task: Handle invalid access, return -1 on exception
 */
public class MovieSeatReservation {
    
    private int[] seats; // Booked seat numbers
    private boolean[] isBooked; // Track if seat is booked
    private int totalSeats;
    private int bookedCount;
    
    /**
     * Constructor - initialize seat array
     */
    public MovieSeatReservation(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seats = new int[totalSeats];
        this.isBooked = new boolean[totalSeats];
        this.bookedCount = 0;
        
        // Initialize seats with IDs 101, 102, 103, ...
        for (int i = 0; i < totalSeats; i++) {
            seats[i] = 100 + i + 1; // Seat IDs: 101, 102, 103, ...
            isBooked[i] = false;
        }
    }
    
    /**
     * Get seat number at given index - handles ArrayIndexOutOfBoundsException
     * Returns seat number if valid, -1 if exception occurs
     */
    public int getSeat(int index) {
        try {
            // Validate index
            if (index < 0 || index >= totalSeats) {
                throw new ArrayIndexOutOfBoundsException(
                    "Invalid seat index: " + index + ". Valid range: 0-" + (totalSeats - 1));
            }
            
            return seats[index];
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - Seat Access Failed: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Book a seat at given index
     * Returns true if booked successfully, false otherwise
     */
    public boolean bookSeat(int index) {
        try {
            // Validate index
            if (index < 0 || index >= totalSeats) {
                throw new ArrayIndexOutOfBoundsException(
                    "Invalid seat index: " + index + ". Valid range: 0-" + (totalSeats - 1));
            }
            
            // Check if already booked
            if (isBooked[index]) {
                System.out.println("❌ Seat " + seats[index] + " is already booked!");
                return false;
            }
            
            // Book the seat
            isBooked[index] = true;
            bookedCount++;
            System.out.println("✓ Seat " + seats[index] + " successfully booked!");
            return true;
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - Cannot book seat: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Cancel booking for a seat at given index
     */
    public boolean cancelBooking(int index) {
        try {
            // Validate index
            if (index < 0 || index >= totalSeats) {
                throw new ArrayIndexOutOfBoundsException(
                    "Invalid seat index: " + index + ". Valid range: 0-" + (totalSeats - 1));
            }
            
            // Check if seat is booked
            if (!isBooked[index]) {
                System.out.println("❌ Seat " + seats[index] + " is not booked!");
                return false;
            }
            
            // Cancel booking
            isBooked[index] = false;
            bookedCount--;
            System.out.println("✓ Booking for seat " + seats[index] + " cancelled!");
            return true;
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - Cannot cancel booking: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Check booking status at given index
     * Returns true if booked, false if available
     * Returns -1 (as int would be problematic, but we show the pattern)
     */
    public boolean isBookedAtIndex(int index) {
        try {
            // Validate index
            if (index < 0 || index >= totalSeats) {
                throw new ArrayIndexOutOfBoundsException(
                    "Invalid seat index: " + index + ". Valid range: 0-" + (totalSeats - 1));
            }
            
            return isBooked[index];
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - Cannot check booking status: " + e.getMessage());
            return false; // Default to false (available) on error
        }
    }
    
    /**
     * Display all available seats
     */
    public void displayAvailableSeats() {
        System.out.println("\n--- Available Seats ---");
        int count = 0;
        for (int i = 0; i < totalSeats; i++) {
            if (!isBooked[i]) {
                System.out.print(seats[i] + " ");
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No seats available!");
        } else {
            System.out.println("\nTotal available: " + count);
        }
    }
    
    /**
     * Display all booked seats
     */
    public void displayBookedSeats() {
        System.out.println("\n--- Booked Seats ---");
        int count = 0;
        for (int i = 0; i < totalSeats; i++) {
            if (isBooked[i]) {
                System.out.print(seats[i] + " ");
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No seats booked yet!");
        } else {
            System.out.println("\nTotal booked: " + count);
        }
    }
    
    /**
     * Display complete seating chart
     */
    public void displaySeatingChart() {
        System.out.println("\n========== Movie Seating Chart ==========");
        System.out.println("Legend: [X] = Booked, [ ] = Available\n");
        
        for (int i = 0; i < totalSeats; i++) {
            String status = isBooked[i] ? "[X]" : "[ ]";
            System.out.print(status + " " + seats[i] + "  ");
            
            if ((i + 1) % 5 == 0) {
                System.out.println(); // New line every 5 seats
            }
        }
        
        System.out.println("\n\nSeat Statistics:");
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Booked: " + bookedCount);
        System.out.println("Available: " + (totalSeats - bookedCount));
        System.out.println("=========================================\n");
    }
    
    /**
     * Get seat number by seat ID
     */
    public int getSeatIndexBySeatNumber(int seatNumber) {
        try {
            for (int i = 0; i < totalSeats; i++) {
                if (seats[i] == seatNumber) {
                    return i;
                }
            }
            throw new IllegalArgumentException("Seat number " + seatNumber + " not found!");
            
        } catch (IllegalArgumentException e) {
            System.out.println("❌ ERROR - " + e.getMessage());
            return -1;
        }
    }
    
    // Main method to demonstrate the system
    public static void main(String[] args) {
        MovieSeatReservation theater = new MovieSeatReservation(10);
        
        theater.displaySeatingChart();
        
        // Test 1: Valid seat booking (position 0-4)
        System.out.println("Test 1: Book seat at valid position 0");
        theater.bookSeat(0);
        
        // Test 2: Book another valid seat
        System.out.println("\nTest 2: Book seat at valid position 4");
        theater.bookSeat(4);
        
        // Test 3: Book already booked seat
        System.out.println("\nTest 3: Try to book already booked seat at position 0");
        theater.bookSeat(0);
        
        // Test 4: INVALID ACCESS - position 8 (as per problem statement)
        System.out.println("\nTest 4: Try to access invalid position 8");
        int seatNumber = theater.getSeat(8);
        System.out.println("Result: " + seatNumber);
        
        // Test 5: Check status at valid index
        System.out.println("\nTest 5: Check booking status at position 0");
        boolean isBooked = theater.isBookedAtIndex(0);
        System.out.println("Seat booked: " + isBooked);
        
        // Test 6: Check status at invalid index
        System.out.println("\nTest 6: Check booking status at invalid position 15");
        isBooked = theater.isBookedAtIndex(15);
        System.out.println("Result: " + isBooked);
        
        // Test 7: Get seat at valid position
        System.out.println("\nTest 7: Get seat at valid position 3");
        seatNumber = theater.getSeat(3);
        System.out.println("Seat number: " + seatNumber);
        
        // Test 8: Boundary test - last seat
        System.out.println("\nTest 8: Get last seat (position 9)");
        seatNumber = theater.getSeat(9);
        System.out.println("Seat number: " + seatNumber);
        
        // Test 9: Out of bounds - negative index
        System.out.println("\nTest 9: Try to access negative position -1");
        seatNumber = theater.getSeat(-1);
        System.out.println("Result: " + seatNumber);
        
        // Test 10: Out of bounds - beyond array
        System.out.println("\nTest 10: Try to access position 100 (beyond limit)");
        seatNumber = theater.getSeat(100);
        System.out.println("Result: " + seatNumber);
        
        // Test 11: Cancel booking
        System.out.println("\nTest 11: Cancel booking for position 0");
        theater.cancelBooking(0);
        
        theater.displaySeatingChart();
    }
}
