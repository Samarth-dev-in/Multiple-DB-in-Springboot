<h1>Multiple Database Configuration in Spring Boot</h1>
This project demonstrates how to configure and use multiple databases in a Spring Boot application. It shows how to set up separate databases for primary application data and logging purposes.<br/>
Technologies Used<br/>

Java 21<br/>
Spring Boot 3.4.0<br/>
MySQL<br/>
Spring Data JPA<br/>
Lombok<br/>

<h2> Project Structure </h2> <br/>
src/main/java <br/>
└── com.example.Multiple.DB<br/>
    ├── config<br/>
    │   ├── PrimaryDataSourceConfig.java<br/>
    │   └── SecondaryDataSourceConfig.java<br/>
    ├── controller<br/>
    ├── primary.entity<br/>
    ├── primary.repository<br/>
    ├── secondary.entity<br/>
    ├── secondary.repository<br/>
    ├── service<br/>
    └── MultipleDbApplication.java<br/>
<h2> Prerequisites </h2>
JDK 21
Maven
MySQL Server

<h2>Database Setup</h2>

Create two MySQL databases:
sqlCopyCREATE DATABASE primary;
CREATE DATABASE secondary;

Update database configurations in application.properties if needed

Running the Application

<h3>Clone the repository:</h3>
bashCopygit clone https://github.com/Samarth-dev-in/Multiple-DB-in-Springboot.git

Navigate to project directory:
bashCopycd Multiple-DB-in-Springboot

Run the application:
bashCopymvn spring-boot:run


<h3>Configuration</h3>
The application uses separate configuration classes for each database:

PrimaryDataSourceConfig.java - Configures the main application database
SecondaryDataSourceConfig.java - Configures the logging database

<h3>Features</h3>

Multiple database configuration
Separate entity management for each database
Independent transaction management
Logging service implementation
REST API endpoints for testing


<h3>Author</h3>
<strong>Samarth Umbare</strong> <br/>
For a detailed explanation about this project, check out my blog post on Medium.
Related Articles

Configuring Multiple Databases in Spring Boot (Medium)
