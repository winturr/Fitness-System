package fitness;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Welcome to the Fitness Management System!");
            System.out.println("Please choose an option:");
            System.out.println("1. Manage Classes");
            System.out.println("2. Exit");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    Class.manageClassFromUserInput();
                    break;         
                case 2:
                    // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
}
