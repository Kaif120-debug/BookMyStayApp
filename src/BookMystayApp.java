import java.util.Scanner;

public class BookMyStayApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CentralInventory inventory = new CentralInventory();

        System.out.println("===== BOOK MY STAY =====");

        while (true) {

            System.out.println("\n1. View Inventory");
            System.out.println("2. Book Room");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    inventory.showInventory();
                    break;

                case 2:
                    System.out.print("Enter room type (Single Room/Double Room/Suite Room): ");
                    String type = scanner.nextLine();

                    if (inventory.bookRoom(type)) {
                        System.out.println("Room booked successfully!");
                    } else {
                        System.out.println("Room not available.");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using Book My Stay.");
                    System.exit(0);

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}