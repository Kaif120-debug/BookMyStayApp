import java.util.*;

// Booking Request class
class BookingRequest {
    String customerName;
    String roomType;

    BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

// Central Inventory class
class CentralInventory {
    private Map<String, Integer> roomInventory;

    CentralInventory() {
        roomInventory = new HashMap<>();
        roomInventory.put("Single Room", 5);
        roomInventory.put("Double Room", 3);
        roomInventory.put("Suite Room", 2);
    }

    public void showInventory() {
        System.out.println("\n--- Room Inventory ---");
        for (String roomType : roomInventory.keySet()) {
            System.out.println(roomType + " : " + roomInventory.get(roomType) + " available");
        }
    }

    public boolean bookRoom(String roomType) {
        if (roomInventory.containsKey(roomType) && roomInventory.get(roomType) > 0) {
            roomInventory.put(roomType, roomInventory.get(roomType) - 1);
            return true;
        }
        return false;
    }

    public void cancelRoom(String roomType) {
        if (roomInventory.containsKey(roomType)) {
            roomInventory.put(roomType, roomInventory.get(roomType) + 1);
        }
    }

    public boolean isAvailable(String roomType) {
        return roomInventory.containsKey(roomType) && roomInventory.get(roomType) > 0;
    }
}

// Main App
public class BookMyStayApp {

    static Queue<BookingRequest> bookingQueue = new LinkedList<>();
    static Map<String, String> confirmedBookings = new HashMap<>(); // customerName -> roomType

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CentralInventory inventory = new CentralInventory();

        System.out.println("===== WELCOME TO BOOK MY STAY =====");

        while (true) {

            System.out.println("\nMenu:");
            System.out.println("1. View Rooms");
            System.out.println("2. Add Booking Request");
            System.out.println("3. Process Booking & Confirm Reservation");
            System.out.println("4. View Confirmed Reservations");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    inventory.showInventory();
                    break;

                case 2:
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Room Type (Single Room/Double Room/Suite Room): ");
                    String roomType = sc.nextLine();

                    bookingQueue.add(new BookingRequest(name, roomType));
                    System.out.println("Booking request added to queue.");
                    break;

                case 3:
                    processBooking(inventory);
                    break;

                case 4:
                    showConfirmedBookings();
                    break;

                case 5:
                    System.out.println("Thank you for using Book My Stay!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Process booking requests FIFO and allocate room
    static void processBooking(CentralInventory inventory) {

        if (bookingQueue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        BookingRequest req = bookingQueue.poll();

        // Check if customer already has a confirmed booking
        if (confirmedBookings.containsKey(req.customerName)) {
            System.out.println("Customer " + req.customerName + " already has a confirmed booking.");
            return;
        }

        // Check availability and allocate room
        if (inventory.bookRoom(req.roomType)) {
            confirmedBookings.put(req.customerName, req.roomType);
            System.out.println("Reservation confirmed for " + req.customerName +
                    " | Room Type: " + req.roomType);
        } else {
            System.out.println("Sorry, no " + req.roomType + " available for " + req.customerName);
        }
    }

    // Display confirmed reservations
    static void showConfirmedBookings() {
        System.out.println("\n--- Confirmed Reservations ---");
        if (confirmedBookings.isEmpty()) {
            System.out.println("No reservations yet.");
        } else {
            for (Map.Entry<String, String> entry : confirmedBookings.entrySet()) {
                System.out.println("Customer: " + entry.getKey() + " | Room: " + entry.getValue());
            }
        }
    }
}