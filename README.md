# Online Banking Management System
- An online banking management system that allows users to manage their accounts, 
perform transactions, and access various financial services securely. 
- The application features a robust backend built with Spring Boot, efficient data storage managed using MySQL 
and is deployed using Docker containers to ensure consistency across environments.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Installation](#installation)
- [Usage](#usage)
- [Database Design](#database-design)
- [Contributing](#contributing)
- [License](#license)


## Features
- User Management: Users can create accounts, log in, and manage their profiles.
- Account Management: Users can view account details, check balances, and manage multiple accounts.
- Transactions: Users can perform deposits, withdrawals, and transfers between accounts.
- Transaction History: Users can view a history of all transactions.

## Tech Stack
- Backend: Spring Boot
- Database: MySQL
- Containerization: Docker

## Architecture
The system is designed with a focus on scalability and security, using a layered architecture:
- Backend: Built using Spring Boot, the backend handles all business logic, including user authentication, account management, and transaction processing. It exposes RESTful APIs for communication.
- Database: MySQL is used to securely store user, account, and transaction data, ensuring data integrity and efficient access.
- Docker: The application is containerized using Docker to provide consistent environments across development, testing, and production.

## Installation
Follow these steps to set up the project locally:

## Prerequisites
- Java Development Kit (JDK)
- MySQL
- Docker

## Backend Setup
1. Clone the repository:
`git clone https://github.com/your-username/online-banking-management-system.git
cd online-banking-management-system`

2.Navigate to the backend directory and build the Spring Boot application:
`cd backend
./mvnw clean install`

3. Start the backend server:
`./mvnw spring-boot:run`

## Database Setup
1. Create a MySQL database:
`CREATE DATABSE banking;`

2. Update the database configuration in the application.properties:
`spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password`

## Usage
- Use an API testing tool like Postman to interact with the backend services.
- Register a new user account or log in with existing credentials via the API.
- Use API endpoints to manage accounts, perform transactions, and view transaction history.

## Database Design
![DB Image](https://res.cloudinary.com/dxfn9epwh/image/upload/v1725342370/v/cip7wwfsdvati6gvdgrz.png)

The MySQL database consists of the following tables:
- users: Stores user information such as user ID, username, password, and contact details.
- accounts: Stores account details including account ID, user ID, account type, and balance.
- transactions: Stores transaction records with transaction ID, account ID, transaction type, amount, and timestamp.

## Contributing
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch: git checkout -b feature-name.
3. Make your changes and commit them: git commit -m 'Add new feature'.
4. Push to the branch: git push origin feature-name.
5. Submit a pull request.
