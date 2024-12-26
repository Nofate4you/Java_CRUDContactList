# FullStack CRUD ContactList

## Overview
This project demonstrates CRUD-based operations. It features a detailed contact list that allows you to display and upload profile images, as well as add your full name, email, phone number, address, and more. The main purpose of this contact list is to manage contacts by adding new ones and storing them in an organized, centralized location.

## Features
- CRUD Operations: Allows you to CREATE, READ, UPDATE, and DELETE contact information.
- Profile Image Upload: Option to upload and display profile images for each contact.
- Detailed Contact Information: Add and store full name, email, phone number, address, and other details for each contact.
- User-Friendly Interface with Pagination: Designed for ease of use, allowing multiple pages to be added as the number of contacts grows, ensuring smooth navigation and efficient contact management.

## Technologies Used
- Backend: SpringBoot
- Frontend: React 
- Database: PostgreSQL
- Hosting: AWS Services
- Version Control: GitHub
- Backend Components: SpringBootStarterWeb, Lombok, CorsConfig, JPA(Java Persistence API)


# Getting Started

## Prerequisites
- **Download FrontEnd https://github.com/Nofate4you/React-CRUDContactList**
- **JDK 11+**
- **Maven** (or Gradle)
- **PostgreSQL**
- **Node.js & npm**

# Installation

## Step 1: Clone the Repository
```bash
git clone https://github.com/Nofate4you/Java_CRUDContactList.git
```

## Step 2: Backend Setup (Spring Boot)
Navigate to the backend folder:
cd contactlistapi
```
Update `application.properties` with your PostgreSQL details:
**properties**
spring.datasource.url=jdbc:postgresql://localhost:5432/contactlistdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```
Run the Spring Boot application:
```bash
mvn spring-boot:run
```
The backend is available at: [http://localhost:8080](http://localhost:8080)

## Step 3: Frontend Setup (React)
Navigate to the frontend folder:
cd contactlistweb
```
Install the dependencies:
```bash
npm install
```
Start the React development server:
```bash
npm start
```
The frontend is available at: [http://localhost:3000](http://localhost:3000)

## Step 4: Database Setup
Ensure PostgreSQL is installed and running. You can download it from the [PostgreSQL official site](https://www.postgresql.org/download/).
Create the database:
```sql
CREATE DATABASE contactlistdb;
```

## Step 5: Usage
**Backend**: Exposes a REST API for CRUD operations on contacts.
Example endpoint: [http://localhost:8080/contacts](http://localhost:8080/contacts)

**Frontend**: Provides a user-friendly interface to:
- Add, view, update, and delete contacts.
- Upload profile images.
- Save multiple contacts and navigate to 2nd, 3rd, etc., pages.

## Screenshots 
![image](https://github.com/user-attachments/assets/405c37a7-d838-4a3d-bdd5-f89da629477c)
![image](https://github.com/user-attachments/assets/ccde6110-f610-4b70-896a-9df1a7d3fc96)
![image](https://github.com/user-attachments/assets/edc5fb0c-c35b-49d6-b9c2-b6b67f70fc4c)
![image](https://github.com/user-attachments/assets/664a39f3-aae3-4500-87e7-2361971936a7)

