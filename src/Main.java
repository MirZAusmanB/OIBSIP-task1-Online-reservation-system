import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem reservationSystem = new ReservationSystem();

        System.out.println("!!!!!!!!!!!!!!!!!!!!! OASIS INFOBYTE TASK 1 !!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("----------- Welcome to the Online Reservation System --------------");

        boolean validCredentials = false;

        do {
            System.out.print("\nENTER USERNAME: ");
            String username = scanner.nextLine();
            System.out.print("ENTER PASSWORD: ");
            String password = scanner.nextLine();

            Login login = new Login(username, password);
            if (login.userAuthentication()) {
                validCredentials = true;
                System.out.println("Login successful! Proceeding to the Reservation System...");

                boolean makeAnotherReservation = false;

                do {
                    System.out.print("Enter your full name: ");
                    String fullName = scanner.nextLine();

                    System.out.print("Enter your age: ");
                    int age;
                    try {
                        age = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age format. Please enter a valid number.");
                        continue;
                    }

                    System.out.print("Enter train type (economy or business): ");
                    String className = scanner.nextLine();

                    System.out.print("Enter date of journey (DD/MM/YYYY): ");
                    String dateOfJourney = scanner.nextLine();

                    System.out.print("Enter Departure station: ");
                    String departure = scanner.nextLine();

                    System.out.print("Enter Arrival station: ");
                    String arrival = scanner.nextLine();

                    reservationSystem.addReservation(fullName, age, className, dateOfJourney, departure, arrival);
                    reservationSystem.displayAllReservations();

                    System.out.print("Do you want to make another reservation? (Type 'yes' or 'no'): ");
                    String anotherReservationChoice = scanner.nextLine();
                    makeAnotherReservation = anotherReservationChoice.equalsIgnoreCase("yes");

                } while (makeAnotherReservation);

                reservationSystem.displayAllReservations();

                // Cancellation process
                System.out.print("\nDo you want to cancel a reservation? (Type 'yes' or 'no'): ");
                String cancellationChoice = scanner.nextLine();
                if (cancellationChoice.equalsIgnoreCase("yes")) {
                    System.out.print("Enter the PNR number of the reservation you want to cancel: ");
                    String pnrToCancel = scanner.nextLine();
                    reservationSystem.cancelReservation(pnrToCancel);
                    reservationSystem.displayAllReservations();
                }

            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        } while (!validCredentials);

        // Close the Scanner
        scanner.close();
    }
}

