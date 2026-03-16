import java.util.*;

public class BookMyStayApp {

    static Queue<BookingRequest> bookingQueue = new LinkedList<>();
    static Set<String> bookedCustomers = new HashSet<>(); // Prevent duplicate booking

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CentralInventory inventory = new CentralInventory();

        System.out.println("===== WELCOME TO BOOK MY STAY =====");

        while (true) {

            System.out.println("\nMenu:");
            System.out.println("1. View Rooms");
            System.out.println("2. Search Room");
            System.out.println("3. Check Availability");
            System.out.println("4. Add Booking Request");
            System.out.println("5. Process Next Booking");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    inventory.showInventory();
                    break;

                case 2:
                    System.out.print("Enter room name to search: ");
                    String query = sc.nextLine();
                    inventory.searchRoom(query);
                    break;

                case 3:
                    System.out.print("Enter room type to check availability: ");
                    String checkRoom = sc.nextLine();
                    if (inventory.isAvailable(checkRoom)) {
                        System.out.println(checkRoom + " is available!");
                    } else {
                        System.out.println(checkRoom + " is NOT available.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Customer Name: ");
                    String customer = sc.nextLine();
                    System.out.print("Enter Room Type (Single Room/Double Room/Suite Room): ");
                    String roomType = sc.nextLine();

                    bookingQueue.add(new BookingRequest(customer, roomType));
                    System.out.println("Booking request added to queue.");
                    break;

                case 5:
                    processBooking(inventory);
                    break;

                case 6:
                    System.out.println("Thank you for using Book My Stay!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Process bookings FIFO
    static void processBooking(CentralInventory inventory) {

        if (bookingQueue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        BookingRequest request = bookingQueue.poll();

        if (bookedCustomers.contains(request.customerName)) {
            System.out.println("Customer " + request.customerName + " already has a booking.");
            return;
        }

        if (inventory.bookRoom(request.roomType)) {
            bookedCustomers.add(request.customerName);
            System.out.println("Booking confirmed for " + request.customerName +
                    " (" + request.roomType + ")");
        } else {
            System.out.println("No rooms available for " + request.roomType);
        }
    }
}