import java.sql.*;
import java.util.*;

// Dentist Model Class
public class Dentist {
    private int dentistID;
    private String lastName;
    private String firstName;
    private String awardingBody;
    private String specialty;
    private String dateOfBirth;
    private int phoneNo;

    // Constructor
    public Dentist(int dentistID, String lastName, String firstName, String awardingBody, String specialty, String dateOfBirth, int phoneNo) {
        this.dentistID = dentistID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.awardingBody = awardingBody;
        this.specialty = specialty;
        this.dateOfBirth = dateOfBirth;
        this.phoneNo = phoneNo;
    }

    // Getters and Setters
    public int getDentistID() {
         return dentistID; 
        }

    public void setDentistID(int dentistID) {
         this.dentistID = dentistID; 
        }
        
    
    public String getLastName() {
         return lastName; 
        }

    public void setLastName(String lastName) {
         this.lastName = lastName; 
        }

    
    public String getFirstName() {
         return firstName; 
        }

    public void setFirstName(String firstName) {
         this.firstName = firstName; 
        }

    
    public String getAwardingBody() {
         return awardingBody; 
        }

    public void setAwardingBody(String awardingBody) {
         this.awardingBody = awardingBody; 
        }

    
    public String getSpecialty() {
         return specialty; 
        }

    public void setSpecialty(String specialty) {
         this.specialty = specialty; 
        }

    
    public String getDateOfBirth() {
         return dateOfBirth; 
        }

    public void setDateOfBirth(String dateOfBirth) {
         this.dateOfBirth = dateOfBirth; 
        }

    
    public int getPhoneNo() {
         return phoneNo; 
        }

    public void setPhoneNo(int phoneNo) {
         this.phoneNo = phoneNo; 
        }

}

// Dentist DAO Class
 class DentistDAO {
    // Database connection setup
    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/dentalsystem";
        String username = "root";
        String password = "password";
        return DriverManager.getConnection(url, username, password);
    }

    // Add a new dentist
    public void addDentist(Dentist dentist) {
        String query = "INSERT INTO dentist (lastName, firstName, awardingBody, specialty, dateOfBirth, phoneNo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, dentist.getLastName());
            statement.setString(2, dentist.getFirstName());
            statement.setString(3, dentist.getAwardingBody());
            statement.setString(4, dentist.getSpecialty());
            statement.setString(5, dentist.getDateOfBirth());
            statement.setInt(6, dentist.getPhoneNo());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    dentist.setDentistID(generatedKeys.getInt(1));
                    System.out.println("Dentist added successfully! ID: " + dentist.getDentistID());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all dentist
    public List<Dentist> getAllDentists() {
        List<Dentist> dentists = new ArrayList<>();
        String query = "SELECT * FROM dentist";
        
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Dentist dentist = new Dentist(
                    resultSet.getInt("dentistID"),
                    resultSet.getString("lastName"),
                    resultSet.getString("firstName"),
                    resultSet.getString("awardingBody"),
                    resultSet.getString("specialty"),
                    resultSet.getString("dateOfBirth"),
                    resultSet.getInt("phoneNo")
                );
                dentists.add(dentist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dentists;
    }

    // Search dentist by ID
    public Dentist getDentistById(int dentistID) {
        String query = "SELECT * FROM dentist WHERE dentistID = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, dentistID);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return new Dentist(
                    resultSet.getInt("dentistID"),
                    resultSet.getString("lastName"),
                    resultSet.getString("firstName"),
                    resultSet.getString("awardingBody"),
                    resultSet.getString("specialty"),
                    resultSet.getString("dateOfBirth"),
                    resultSet.getInt("phoneNo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update dentist details
    public void updateDentist(Dentist dentist) {
        String query = "UPDATE dentist SET lastName = ?, firstName = ?, awardingBody = ?, specialty = ?, dateOfBirth = ?, phoneNo = ? WHERE dentistID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, dentist.getLastName());
            statement.setString(2, dentist.getFirstName());
            statement.setString(3, dentist.getAwardingBody());
            statement.setString(4, dentist.getSpecialty());
            statement.setString(5, dentist.getDateOfBirth());
            statement.setInt(6, dentist.getPhoneNo());
            statement.setInt(7, dentist.getDentistID());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dentist updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        // Delete a dentist by ID
        public void deleteDentist(int dentistID) {
            String query = "DELETE FROM dentist WHERE dentistID = ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
    
                statement.setInt(1, dentistID);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Dentist deleted successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
