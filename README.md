# car-rental-management-system

**Car Rental Management System**
================================

**Setup Instructions**
--------------------

### 1. Prerequisites

* Java 8 or later
* MySQL 8 or later
* MySQL JDBC driver (included in the project)

### 2. Database Setup

* Create a new MySQL database named `car_rental`
* Run the SQL script provided in the `sql` directory to create the tables and schema

### 3. Build and Run the Application

* Compile the Java code using `javac` or an IDE of your choice
* Run the `CarRentalManagementSystem` class using `java` or an IDE of your choice

### 4. Configuration

* Update the `database.properties` file with your MySQL username and password
* Update the `jdbc.properties` file with your JDBC connection settings

**Usage Instructions**
-------------------

### 1. Adding a Car

* Run the `addCar` method with the following parameters:
	+ `make`: the make of the car (e.g. Toyota)
	+ `model`: the model of the car (e.g. Corolla)
	+ `year`: the year of the car (e.g. 2020)
	+ `dailyRate`: the daily rental rate of the car (e.g. 50.00)

### 2. Viewing Cars

* Run the `viewCars` method to retrieve a list of all cars in the system

### 3. Updating a Car

* Run the `updateCar` method with the following parameters:
	+ `carId`: the ID of the car to update
	+ `newDailyRate`: the new daily rental rate of the car

### 4. Deleting a Car

* Run the `deleteCar` method with the following parameter:
	+ `carId`: the ID of the car to delete

**Troubleshooting**
-------------------

* Check the `logs` directory for error messages and stack traces
* Verify that the MySQL database is running and accessible
* Verify that the JDBC connection settings are correct

