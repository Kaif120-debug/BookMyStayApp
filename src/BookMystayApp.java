import java.util.Scanner;

public class BookMyStayApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CentralInventory inventory = new CentralInventory();

        System.out.println("===== WELCOME TO BOOK MY STAY =====");

        while (true) {

            System.out.println("\nMenu:");
            System.out.println("1. View All Rooms");
            System.out.println("2. Search Room");
            System.out.println("3. Check Availability");
            System.out.println("4. Book Room");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    inventory.showInventory();
                    break;

                case 2:
                    System.out.print("Enter room name to search: ");
                    String searchQuery = scanner.nextLine();
                    inventory.searchRoom(searchQuery);
                    break;

                case 3:
                    System.out.print("Enter room type to check availability: ");
                    String checkRoom = scanner.nextLine();
                    if (inventory.isAvailable(checkRoom)) {
                        System.out.println(checkRoom + " is available!");
                    } else {
                        System.out.println(checkRoom + " is NOT available.");
                    }
                    break;

                case 4:
                    System.out.print("Enter room type to book: ");
                    String bookRoom = scanner.nextLine();
                    if (inventory.bookRoom(bookRoom)) {
                        System.out.println("Booking confirmed for " + bookRoom + "!");
                    } else {
                        System.out.println("Room not available or invalid type.");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using Book My Stay!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}