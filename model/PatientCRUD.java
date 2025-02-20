import java.util.ArrayList;
import java.util.Scanner;

public class PatientCRUD {
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPatient CRUD System");
            System.out.println("1. Create Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Search Patient by Name and Surname");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createPatient(scanner);
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    updatePatient(scanner);
                    break;
                case 4:
                    deletePatient(scanner);
                    break;
                case 5:
                    searchPatient(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    private static void createPatient(Scanner scanner) {
        System.out.print("Enter patient's first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter patient's last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter patient's D.O.B: ");
        String dateOfBirth = scanner.nextLine();
        System.out.print("Enter patient's email: ");
        String email = scanner.nextLine();
        System.out.print("Enter street name: ");
        String street = scanner.nextLine();
        System.out.print("Enter town name: ");
        String town = scanner.nextLine();
        System.out.print("Enter county name: ");
        String county = scanner.nextLine();
        System.out.print("Enter Eircode: ");
        String eircode = scanner.nextLine();
        System.out.print("Does the patient have a medical card? (yes/no): ");
        String medCardInput = scanner.nextLine();
        Boolean medCard = medCardInput.equalsIgnoreCase("yes");

        Patient patient = new Patient(nextId++, firstName, lastName, dateOfBirth, email, street, town, county, eircode, medCard);
        patients.add(patient);
        System.out.println("Patient created successfully.");
    }

    private static void viewAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-15s | %-15s | %-10s | %-25s | %-10s | %-10s | %-10s | %-10s | %-10s%n",
                "ID", "First Name", "Last Name", "DOB", "Email", "Street", "Town", "County", "Eircode", "MedCard");
        System.out.println("--------------------------------------------------------------------------------------");

        for (Patient patient : patients) {
            System.out.printf("%-5d | %-15s | %-15s | %-10s | %-25s | %-10s | %-10s | %-10s | %-10s | %-10s%n",
                    patient.getId(), patient.getFirstName(), patient.getLastName(), patient.getDOB(), patient.getEmail(),
                    patient.getStreet(), patient.getTown(), patient.getCounty(), patient.getEircode(),
                    patient.getMedCard() ? "Yes" : "No");
        }

        System.out.println("--------------------------------------------------------------------------------------");
    }

    private static void searchPatient(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().toLowerCase();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Patient patient : patients) {
            if (patient.getFirstName().toLowerCase().equals(firstName) &&
                patient.getLastName().toLowerCase().equals(lastName)) {
                System.out.println("\nPatient found:");
                System.out.println(patient);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No patient found with that name.");
        }
    }

    private static void updatePatient(Scanner scanner) {
        System.out.print("Enter patient ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Patient patient : patients) {
            if (patient.getId() == id) {
                System.out.print("Enter new first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter new last name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter new street: ");
                String street = scanner.nextLine();
                System.out.print("Enter new town: ");
                String town = scanner.nextLine();
                System.out.print("Enter new County: ");
                String county = scanner.nextLine();
                System.out.print("Enter new eircode: ");
                String eircode = scanner.nextLine();

                patient.setFirstName(firstName);
                patient.setLastName(lastName);
                patient.setStreet(street);
                patient.setTown(town);
                patient.setCounty(county);
                patient.setEircode(eircode);

                System.out.println("Patient updated successfully.");
                return;
            }
        }
        System.out.println("Patient not found.");
    }

    private static void deletePatient(Scanner scanner) {
        System.out.print("Enter patient ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Iterator<Patient> iterator = patients.iterator();
        while (iterator.hasNext()) {
            Patient patient = iterator.next();
            if (patient.getId() == id) {
                iterator.remove();
                System.out.println("Patient deleted successfully.");
                return;
            }
        }
        System.out.println("Patient not found.");
    }
}
