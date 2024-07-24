
CREATE DATABASE car_rental;

USE car_rental;

-- Create the cars table
CREATE TABLE cars (
  car_id INT PRIMARY KEY AUTO_INCREMENT,
  make VARCHAR(50) NOT NULL,
  model VARCHAR(50) NOT NULL,
  year INT NOT NULL,
  daily_rate DECIMAL(10, 2) NOT NULL,
  is_rented BOOLEAN DEFAULT FALSE
);

-- Create the customers table
CREATE TABLE customers (
  customer_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  phone_number VARCHAR(20) NOT NULL,
  address VARCHAR(200) NOT NULL
);

-- Create the rentals table
CREATE TABLE rentals (
  rental_id INT PRIMARY KEY AUTO_INCREMENT,
  car_id INT NOT NULL,
  customer_id INT NOT NULL,
  rental_start_date DATE NOT NULL,
  rental_end_date DATE,
  total_charge DECIMAL(10, 2) DEFAULT 0.00,
  FOREIGN KEY (car_id) REFERENCES cars(car_id),
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);