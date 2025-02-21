import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

class Patient{
    private int id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String street;
    private String town;
    private String county;
    private String eircode;
    private Boolean medCard;

    public class MySQLConnection {
        public static void main(String[] args) {
            String host = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "root";
            String password = "password";
            try {
                Connection conn = DriverManager.getConnection(host, username, password);
                System.out.println("Connected to MySQL database");
            } catch (SQLException e) {
                System.out.println("Failed to connect to MySQL database");
                e.printStackTrace();
            }
        }
    }

    public Patient(int id, String firstName, String lastName, String dateOfBirth, String email, String street, String town, String county, String eircode, Boolean medCard) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.street = street;
        this.town = town;
        this.county = county;
        this.eircode = eircode;
        this.medCard = medCard;    
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDOB(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDOB() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getTown() {
        return town;
    }

    public String getCounty() {
        return county;
    }

    public String getEircode() {
        return eircode;
    }

    public Boolean getMedCard() {
        return medCard;
    }

    @Override
    public String toString() {
        return "Patient{" +
           "id=" + id +
           ", firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           ", dateOfBirth='" + dateOfBirth + '\'' +
           ", email='" + email + '\'' +
           ", street='" + street + '\'' +
           ", town='" + town + '\'' +
           ", county='" + county + '\'' +
           ", eircode='" + eircode + '\'' +
           ", medCard=" + medCard +
           '}';
}

    
}

public class PatientCRUD {
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPatient CRUD System");
            System.out.println("1. Create Patient");
            System.out.println("2. Search Patients");
            System.out.println("3. Read Patients");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createPatient(scanner);
                    break;
                case 2:
                    searchPatient(scanner);
                    break;
                case 3:
                    readPatients();
                    break;
                case 4:
                    updatePatient(scanner);
                    break;
                case 5:
                    deletePatient(scanner);
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

    private static void readPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (Patient patient : patients) {
                System.out.println(patient);
            }
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
                patient.setLastName(street);
                patient.setLastName(town);
                patient.setLastName(county);
                patient.setLastName(eircode);
                
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
        
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                patients.remove(patient);
                System.out.println("Patient deleted successfully.");
                return;
            }
        }
        System.out.println("Patient not found.");
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
}