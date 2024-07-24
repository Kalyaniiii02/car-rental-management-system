package car_rental_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CarRentalManagementSystem {
    private static Connection connection;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        connectToDatabase();

        while (true) {
            System.out.println("Car Rental Management System");
            System.out.println("1. Car Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Rental Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    carManagement();
                    break;
                case 2:
                    customerManagement();
                    break;
                case 3:
                    rentalManagement();
                    break;
                case 4:
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void connectToDatabase() {
        try {
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rental", "root", "ketan4455");
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    private static void carManagement() {
        while (true) {
            System.out.println("Car Management");
            System.out.println("1. Add a new car");
            System.out.println("2. View car details");
            System.out.println("3. Update car information");
            System.out.println("4. Delete a car");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    viewCarDetails();
                    break;
                case 3:
                    updateCarInformation();
                    break;
                case 4:
                    deleteCar();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void customerManagement() {
        while (true) {
            System.out.println("Customer Management");
            System.out.println("1. Register a new customer");
            System.out.println("2. View customer details");
            System.out.println("3. Update customer information");
            System.out.println("4. Delete a customer");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    viewCustomerDetails();
                    break;
                case 3:
                    updateCustomerInformation();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void rentalManagement() {
        while (true) {
            System.out.println("Rental Management");
            System.out.println("1. Rent a car to a customer");
            System.out.println("2. Return a rented car");
            System.out.println("3. View rental details");
            System.out.println("4. Calculate rental charges");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    rentCar();
                    break;
                case 2:
                    returnRentedCar();
                    break;
                case 3:
                    viewRentalDetails();
                    break;
                case 4:
                    calculateRentalCharges();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Implementations of car management methods
    private static void addCar() {
        System.out.print("Enter car make: ");
        String make = scanner.next();
        System.out.print("Enter car model: ");
        String model = scanner.next();
        System.out.print("Enter car year: ");
        int year = scanner.nextInt();
        System.out.print("Enter daily rate: ");
        double dailyRate = scanner.nextDouble();

        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO cars (make, model, year, daily_rate) VALUES (?, ?, ?, ?)")) {
            pstmt.setString(1, make);
            pstmt.setString(2,model);
            pstmt.setInt(3, year);
            pstmt.setDouble(4, dailyRate);
            pstmt.executeUpdate();
            System.out.println("Car added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding car: " + e.getMessage());
        }
    }

    private static void viewCarDetails() {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM cars"); ResultSet rs = pstmt.executeQuery()) {
            System.out.println("Car ID\tMake\tModel\tYear\tDaily Rate");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4) + "\t" + rs.getDouble(5));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching car details: " + e.getMessage());
        }
    }

    private static void updateCarInformation() {
        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();
        System.out.print("Enter new daily rate: ");
        double newDailyRate = scanner.nextDouble();

        try (PreparedStatement pstmt = connection.prepareStatement("UPDATE cars SET daily_rate = ? WHERE car_id = ?")) {
            pstmt.setDouble(1, newDailyRate);
            pstmt.setInt(2, carId);
            pstmt.executeUpdate();
            System.out.println("Car information updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating car information: " + e.getMessage());
        }
    }

    private static void deleteCar() {
        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();

        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM cars WHERE car_id = ?")) {
            pstmt.setInt(1, carId);
            pstmt.executeUpdate();
            System.out.println("Car deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting car: " + e.getMessage());
        }
    }

    // Implementations of customer management methods
    private static void registerCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.next();
        System.out.print("Enter customer email: ");
        String email = scanner.next();
        System.out.print("Enter customer phone number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter customer address: ");
        String address = scanner.next();

        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO customers (name, email, phone_number, address) VALUES (?, ?, ?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, address);
            pstmt.executeUpdate();
            System.out.println("Customer registered successfully.");
        } catch (SQLException e) {
            System.out.println("Error registering customer: " + e.getMessage());
        }
    }

    private static void viewCustomerDetails() {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM customers"); ResultSet rs = pstmt.executeQuery()) {
            System.out.println("Customer ID\tName\tEmail\tPhone Number\tAddress");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching customer details: " + e.getMessage());
        }
    }

    private static void updateCustomerInformation() {
        System.out.print("Enter customer ID:");
        int customerId = scanner.nextInt();
        System.out.print("Enter new address: ");
        String newAddress = scanner.next();

        try (PreparedStatement pstmt = connection.prepareStatement("UPDATE customers SET address =? WHERE customer_id =?")) {
            pstmt.setString(1, newAddress);
            pstmt.setInt(2, customerId);
            pstmt.executeUpdate();
            System.out.println("Customer information updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating customer information: " + e.getMessage());
        }
    }

    private static void deleteCustomer() {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();

        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM customers WHERE customer_id =?")) {
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
            System.out.println("Customer deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

    // Implementations of rental management methods
    private static void rentCar() {
        System.out.print("Enter car ID: ");
        int carId = scanner.nextInt();
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter rental start date (yyyy-mm-dd): ");
        String rentalStartDate = scanner.next();
        System.out.print("Enter rental end date (yyyy-mm-dd): ");
        String rentalEndDate = scanner.next();

        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO rentals (car_id, customer_id, rental_start_date, rental_end_date) VALUES (?,?,?,?)")) {
            pstmt.setInt(1, carId);
            pstmt.setInt(2, customerId);
            pstmt.setString(3, rentalStartDate);
            pstmt.setString(4, rentalEndDate);
            pstmt.executeUpdate();
            System.out.println("Car rented successfully.");
        } catch (SQLException e) {
            System.out.println("Error renting car: " + e.getMessage());
        }
    }

    private static void returnRentedCar() {
        System.out.print("Enter rental ID: ");
        int rentalId = scanner.nextInt();

        try (PreparedStatement pstmt = connection.prepareStatement("UPDATE rentals SET rental_end_date = NOW() WHERE rental_id =?")) {
            pstmt.setInt(1, rentalId);
            pstmt.executeUpdate();
            System.out.println("Car returned successfully.");
        } catch (SQLException e) {
            System.out.println("Error returning car: " + e.getMessage());
        }
    }

    private static void viewRentalDetails() {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM rentals"); ResultSet rs = pstmt.executeQuery()) {
            System.out.println("Rental ID\tCar ID\tCustomer ID\tRental Start Date\tRental End Date");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching rental details: " + e.getMessage());
        }
    }

    private static void calculateRentalCharges() {
        System.out.print("Enter rental ID: ");
        int rentalId = scanner.nextInt();

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT daily_rate, DATEDIFF(rental_end_date, rental_start_date) AS rental_duration FROM rentals INNER JOIN cars ON rentals.car_id = cars.car_id WHERE rental_id =?")) {
            pstmt.setInt(1, rentalId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double dailyRate = rs.getDouble(1);
                    int rentalDuration = rs.getInt(2);
                    double totalCharge = dailyRate * rentalDuration;
                    System.out.println("Total rental charge: " + totalCharge);
                } else {
                    System.out.println("Rental not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error calculating rental charges: " + e.getMessage());
        }
    }
}