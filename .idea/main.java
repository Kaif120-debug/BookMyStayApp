import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookingSystem bookingSystem = new BookingSystem();

        while (true) {

            System.out.println("\n===== BOOK MY STAY APP =====");
            System.out.println("1. View Hotels");
            System.out.println("2. Add Booking Request");
            System.out.println("3. Process Booking (FIFO)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    bookingSystem.showHotels();
                    break;

                case 2:
                    scanner.nextLine(); // clear buffer

                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Hotel ID: ");
                    int hotelId = scanner.nextInt();

                    bookingSystem.addBookingRequest(name, hotelId);
                    break;

                case 3:
                    bookingSystem.processBooking();
                    break;

                case 4:
                    System.out.println("Thank you for using Book My Stay!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}