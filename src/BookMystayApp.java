import java.util.Scanner;

public class BookMyStayApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Application Entry
        System.out.println("=================================");
        System.out.println("      WELCOME TO BOOK MY STAY    ");
        System.out.println("   Hotel Booking Management App  ");
        System.out.println("=================================");

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();

        // Main Menu
        System.out.println("\nMain Menu");
        System.out.println("1. View Hotels");
        System.out.println("2. Book Room");
        System.out.println("3. Exit");

        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Hotel list feature coming soon...");
                break;

            case 2:
                System.out.println("Room booking feature coming soon...");
                break;

            case 3:
                System.out.println("Thank you for using Book My Stay!");
                break;

            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}