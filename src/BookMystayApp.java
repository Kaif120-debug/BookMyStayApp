import java.util.*;

class Hotel {
    int id;
    String name;
    int totalRooms;
    int availableRooms;

    Hotel(int id, String name, int rooms) {
        this.id = id;
        this.name = name;
        this.totalRooms = rooms;
        this.availableRooms = rooms;
    }

    boolean bookRoom() {
        if (availableRooms > 0) {
            availableRooms--;
            return true;
        }
        return false;
    }

    void display() {
        System.out.println(id + " - " + name + " | Available Rooms: " + availableRooms);
    }
}

class Booking {
    String customerName;
    int hotelId;

    Booking(String name, int hotelId) {
        this.customerName = name;
        this.hotelId = hotelId;
    }
}

public class BookMyStayApp {

    static Map<Integer, Hotel> hotels = new HashMap<>();
    static Queue<Booking> bookingQueue = new LinkedList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Initial hotel data
        hotels.put(1, new Hotel(1, "Sea View Resort", 5));
        hotels.put(2, new Hotel(2, "Mountain Lodge", 3));
        hotels.put(3, new Hotel(3, "City Palace Hotel", 4));

        while (true) {

            System.out.println("\n==== BOOK MY STAY ====");
            System.out.println("1. View Hotels");
            System.out.println("2. Add Booking Request");
            System.out.println("3. Process Booking (FIFO)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nAvailable Hotels:");
                    for (Hotel h : hotels.values()) {
                        h.display();
                    }
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Hotel ID: ");
                    int hotelId = sc.nextInt();

                    bookingQueue.add(new Booking(name, hotelId));
                    System.out.println("Booking request added to queue.");
                    break;

                case 3:

                    if (bookingQueue.isEmpty()) {
                        System.out.println("No booking requests.");
                        break;
                    }

                    Booking request = bookingQueue.poll();
                    Hotel hotel = hotels.get(request.hotelId);

                    if (hotel == null) {
                        System.out.println("Invalid Hotel ID.");
                    }
                    else if (hotel.bookRoom()) {
                        System.out.println("Booking confirmed for " + request.customerName +
                                " at " + hotel.name);
                    }
                    else {
                        System.out.println("No rooms available at " + hotel.name);
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