
# Receipt Processor

This project implements a receipt processing service using **Java** and **Spring Boot**. It allows users to process receipts and calculate points based on specific rules.

---

## Features
- Submit a receipt and get a unique receipt ID.
- Retrieve the points earned for a specific receipt.

---

## Running the Application

### Using Docker
1. **Build the Docker image**:
   ```bash
   docker build -t receipt-processor .
   ```

2. **Run the Docker container**:
   ```bash
   docker run -p 8080:8080 receipt-processor
   ```

3. Access the application at:
   ```
   http://localhost:8080
   ```

---

## API Endpoints

### **POST /receipts/process**
- **Description**: Submits a receipt for processing and returns a unique receipt ID.
- **Request Body Example**:
  ```json
  {
    "retailer": "Target",
    "purchaseDate": "2022-01-01",
    "purchaseTime": "13:01",
    "items": [
      { "shortDescription": "Mountain Dew 12PK", "price": "6.49" },
      { "shortDescription": "Emils Cheese Pizza", "price": "12.25" },
      { "shortDescription": "Knorr Creamy Chicken", "price": "1.26" },
      { "shortDescription": "Doritos Nacho Cheese", "price": "3.35" },
      { "shortDescription": "Klarbrunn 12-PK 12 FL OZ", "price": "12.00" }
    ],
    "total": "35.35"
  }
  ```

- **Response Example**:
  ```json
  {
    "id": "unique-receipt-id"
  }
  ```

---

### **GET /receipts/{id}/points**
- **Description**: Retrieves the points earned for a given receipt ID.
- **Path Parameter**: `{id}` is the unique receipt ID.
- **Response Example**:
  ```json
  {
    "points": 28
  }
  ```

---

## Points Calculation Rules
The following rules are applied to calculate points for a receipt:
1. **Retailer Name:** 1 point for every alphanumeric character.
2. **Round Total:** 50 points if the total is a round dollar amount with no cents.
3. **Multiple of 0.25:** 25 points if the total is a multiple of 0.25.
4. **Items Count:** 5 points for every two items.
5. **Item Description Length:** If the length of the item description is a multiple of 3, multiply the price by 0.2, round up, and add the result.
6. **Odd Purchase Day:** 6 points if the day of the purchase date is odd.
7. **Purchase Time:** 10 points if the time of purchase is between 2:00 PM and 4:00 PM.

---

## Local Setup (Optional)

If you prefer to build and run the application locally:
1. **Prerequisites**:
   - Java 21
   - Maven

2. **Steps**:
   - Build the application:
     ```bash
     mvn clean package
     ```
   - Run the application:
     ```bash
     java -jar target/demo-0.0.1-SNAPSHOT.jar
     ```

3. Access the application at:
   ```
   http://localhost:8080
   ```

---

## Technologies Used
- Java 21
- Spring Boot
- Maven
- Docker

---

## Instructions for Reviewers
1. Clone the repository:
   ```bash
   git clone https://github.com/mounikamaddi/Receipt-Processor-API.git
   ```
2. Build and run the application using Docker:
   ```bash
   docker build -t receipt-processor .
   docker run -p 8080:8080 receipt-processor
   ```
3. Test the endpoints using an API client like Postman.

---

## Author
- **Mounika Maddi**
- Email: mounikamaddi26@gmail.com
- LinkedIn: https://www.linkedin.com/in/mounika-maddi/
