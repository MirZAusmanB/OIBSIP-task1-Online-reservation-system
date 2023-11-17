import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

class Reservation {
    private String fullName;
    private int age;
    private String trainNumber;
    private String classType;
    private String dateOfJourney;
    private String sourceStation;
    private String destinationStation;
    private String PNR;

    public Reservation(String name, int age, String type, String date, String from, String to) {
        this.fullName = name;
        this.age = age;
        this.classType = type;
        this.dateOfJourney = date;
        this.sourceStation = from;
        this.destinationStation = to;
        this.PNR = generatePNR();
        this.trainNumber = generateTrainNumber();
    }

    private static Random random = new Random();

    private String generateTrainNumber() {
        return "PK-EXPRESS-" + random.nextInt(10);
    }

    private String generatePNR() {
        return "PK-" + random.nextInt(1000) + "-" + fullName.substring(0, 2) + "-" + random.nextInt(500);
    }

    public String getPNR() {
        return PNR;
    }

    static boolean isValidDate(String inputDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date currentDate = new Date();
            Date inputDateObject = dateFormat.parse(inputDate);

            // Check if the input date is today or in the future
            return !inputDateObject.before(currentDate);
        } catch (ParseException e) {
            // Invalid date format
            return false;
        }
    }

    public void displayTicket() {
        System.out.println("----Thank you for Booking a ticket----");
        System.out.println("PNR: " + PNR + "\nTRAIN NAME: PK-EXPRESS " + "\nTRAIN NUMBER: " + trainNumber);
        System.out.println("YOUR PERSONAL DETAILS ARE AS FOLLOWS:");
        System.out.println("Fullname: " + fullName +
                "\nAge: " + age +
                "\nDate: " + dateOfJourney +
                "\nDeparture: " + sourceStation +
                "\nArrival: " + destinationStation +
                "\nTrainType: " + classType);
    }
}

class ReservationSystem {
    private static List<Reservation> reservations = new ArrayList<>();
    private Random random = new Random();

    public void addReservation(String name, int age, String type, String date, String from, String to) {
        // Check if the entered date is today or in the future
        if (Reservation.isValidDate(date)) {
            Reservation reservation = new Reservation(name, age, type, date, from, to);
            reservations.add(reservation);
            System.out.println("Reservation successful!");
        } else {
            System.out.println("Invalid date. Please enter today's date or a future date.");
        }
    }

    public void cancelReservation(String pnr) {
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getPNR().equalsIgnoreCase(pnr)) {
                System.out.println("Reservation found!");
                reservation.displayTicket();

                System.out.print("Do you want to cancel this reservation? (type 'ok' to confirm): ");
                String confirmation = new Scanner(System.in).nextLine();
                if (confirmation.equalsIgnoreCase("ok")) {
                    iterator.remove();
                    System.out.println("Reservation canceled successfully!");
                } else {
                    System.out.println("Reservation not canceled.");
                }
                return;
            }
        }
        System.out.println("Reservation with PNR " + pnr + " not found.");
    }

    public void displayAllReservations() {
        System.out.println("\nAll Reservations:");
        for (Reservation reservation : reservations) {
            reservation.displayTicket();
            System.out.println("------------------------");
        }
    }
}