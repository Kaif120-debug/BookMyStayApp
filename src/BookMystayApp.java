import java.util.*;

// Room class
class Room {
    String type;
    int available;
    double price;

    Room(String type, int available, double price) {
        this.type = type;
        this.available = available;
        this.price = price;
    }

    void display() {
        System.out.println(type + " | Available: " + available + " | Price: ₹" + price);
    }
}

// Booking request class
class BookingRequest {
    String customerName;
    String roomType;

    BookingRequest(String name, String roomType) {
        this.customerName = name;
        this.roomType = roomType;
    }
}

public class BookMyStayApp {

    static Map<String, Room> rooms = new HashMap<>();
    static Queue<BookingRequest> bookingQueue = new LinkedList<>();
    static Set<String> bookedCustomers = new HashSet<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Static room availability
        rooms.put("Single", new Room("Single Room", 5, 1500));
        rooms.put("Double", new Room("Double Room", 3, 2500));
        rooms.put("Suite", new Room("Suite Room", 2, 5000));

        // Welcome message
        System.out.println("==================================");
        System.out.println("      WELCOME TO BOOK MY STAY     ");
        System.out.println("  Hotel Booking Management System ");
        System.out.println("==================================");

        while (true) {

            System.out.println("\nMenu:");
            System.out.println("1. View Room Types");
            System.out.println("2. Add Booking Request");
            System.out.println("3. Process Booking (FIFO)");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\nAvailable Rooms:");
                    for (Room r : rooms.values()) {
                        r.display();
                    }
                    break;

                case 2:
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Room Type (Single/Double/Suite): ");
                    String type = sc.nextLine();

                    bookingQueue.add(new BookingRequest(name, type));
                    System.out.println("Booking request added.");
                    break;

                case 3:

                    if (bookingQueue.isEmpty()) {
                        System.out.println("No booking requests.");
                        break;
                    }

                    BookingRequest req = bookingQueue.poll();

                    if (bookedCustomers.contains(req.customerName)) {
                        System.out.println("Customer already has a booking.");
                        break;
                    }

                    Room room = rooms.get(req.roomType);

                    if (room == null) {
                        System.out.println("Invalid room type.");
                    }
                    else if (room.available > 0) {
                        room.available--;
                        bookedCustomers.add(req.customerName);
                        System.out.println("Booking confirmed for " + req.customerName +
                                " (" + room.type + ")");
                    }
                    else {
                        System.out.println("No rooms available for " + room.type);
                    }

                    break;

                case 4:
                    System.out.println("Thank you for using Book My Stay!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}