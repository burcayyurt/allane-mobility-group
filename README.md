README for Allane Project
=========================

This README provides essential information on running the Allane project, including the preconditions, instructions for starting the application, and an explanation of the chosen solution.

Preconditions
--------------
Before running the Allane application, ensure that you have the following prerequisites installed on your system:

1. Java Development Kit (JDK) 11 or higher
2. Gradle
3. Docker (for Dockerized database setup)


Dockerized Database Setup
-------------------------
If you prefer to use Docker for the database setup, follow these additional steps:

1. Install Docker on your system and ensure it is running.
2. Open the command line or terminal and navigate to the project directory.
3. Build the Docker image for the PostgreSQL database using the provided Dockerfile:
   ```
   docker build -t allane-db -f DockerFile .
   ```
4. Run the Docker container with the following command:
   ```
   docker run -p 3306:3306 --name allane-db-container -d allane-db
   ```
   This will start the MySQL container and map port 3306 on the container to the same port on your local machine.
5. Update the `application.properties` file with the appropriate database connection properties:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:3306/allane-mobility-group
   spring.datasource.username=myuser
   spring.datasource.password=password
   ```
6. Save the changes made to the `application.properties` file.
7. Proceed with the steps mentioned in the "How to Start the Application" section to run the Allane application.

How to Start the Application
----------------------------
To start the Allane application, follow these steps:

1. Clone the repository from GitHub: https://github.com/burcayyurt/allane-mobility-group
2. Navigate to the project directory using the command line or terminal.
3. Open the `application.properties` file located in the `src/main/resources` directory.
4. Configure the database connection properties according to your setup. If you have Docker, you can use the Dockerized database setup instructions provided later in this README.
5. Save the changes made to the `application.properties` file.
6. Build the application using Gradle by executing the following command:
   ```
   gradle clean build
   ```
7. Once the build process completes successfully, run the application using the following command:
   ```
   gradle bootRun
   ```
8. The Allane application will start, and you should see the application logs in the console.


Frontend Hierarchy and Pages
============================

The frontend hierarchy of the Allane project consists of multiple HTML pages, including `index.html`, `contractOverview.html`, `leasingContract.html`, `customer.html`, and `vehicle.html`. Each page serves a specific purpose and provides distinct functionality to the users.

1. **index.html**
   This is the main landing page of the Allane application. It serves as the entry point and provides navigation to other pages through different buttons.

2. **contractOverview.html**
   The `contractOverview.html` page displays an overview of all the contracts in the system. It presents information such as contract numbers, customers, vehicles, VINs, monthly rates, and vehicle prices. Users can view and interact with the contract overview data on this page.

3. **leasingContract.html**
   The `leasingContract.html` page allows users to create or edit leasing contracts. It provides input fields to enter contract details, such as contract number, monthly rate, customer ID, and vehicle ID. Users can save the leasing contract by submitting the form on this page.

4. **customer.html**
   The `customer.html` page is dedicated to managing customer information. Users can view, create, update customer records on this page. It may include features like searching for customers, displaying customer details, and performing CRUD operations.

5. **vehicle.html**
   The `vehicle.html` page focuses on managing vehicle data. Users can view, create, update vehicle records on this page. It may offer functionality like searching for vehicles, displaying vehicle details, and performing CRUD operations.

Reason for Chosen Solution
--------------------------
The chosen solution for the Allane project is based on Java Spring Boot with Gradle as the build tool. This combination offers several advantages:

1. **Framework Support**: Spring Boot provides a comprehensive framework for developing enterprise-grade Java applications. It offers features like dependency injection, integrated testing support, and simplified configuration.
2. **Gradle Build Tool**: Gradle is a powerful and flexible build tool that offers efficient dependency management, easy project configuration, and a wide range of plugins for various tasks.
3. **Dockerized Database**: Dockerizing the database allows for easy setup and management, ensuring consistent environments across different development machines and simplifying deployment to production environments.

By using Spring Boot with Gradle and Docker, the Allane project benefits from a robust and efficient development and deployment workflow, enabling easier collaboration, scalability, and consistent database environments.