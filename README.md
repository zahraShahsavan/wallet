User Wallet Microservice
Welcome to the User Wallet Microservice, a Java and Spring Boot-based service designed to manage and track user wallet transactions. This service provides APIs to get the current balance of a user and to add money to the user's wallet.

Features
•  Get Balance API: Retrieve the current balance of a user.

•  Add Money API: Add money to a user's wallet and return a transaction reference number.

•  Transaction Logging: All transactions are logged for audit purposes with a logging aspect and manual log messages

•  Daily Transaction Total: A scheduled job calculates the total amount of transactions daily.

•  Dockerized Application: The service is containerized with Docker for easy deployment.

•  MySQL and H2 Database: User and transaction data are persisted in a MySQL database.

API Endpoints
Get Balance
•  URL: /api/wallet/get-balance

•  Method: GET

•  URL Params: user_id=[integer]

•  Success Response:

•  Code: 200 OK

•  Sample Content: { "balance": 4000 }

Add Money
•  URL: /api/wallet/add-money

•  Method: POST

•  URL Params:

•  user_id=[integer]

•  amount=[integer]

•  Success Response:

•  Code: 200 OK

•  Sample Content: { "reference_id": 12312312312 }

Getting Started
To get the microservice up and running on your local machine for development and testing purposes, follow these steps:

1. 
Clone the repository:

git clone https://github.com/your-username/user-wallet-microservice.git

2. 
Navigate to the project directory:

cd user-wallet-microservice

3. 
Build the Docker image:

docker build -t uwallet-microservice .

4. 
Run the Docker container:

docker run -p 8080:8080 user-wallet-microservice

Testing
The project includes six test cases to ensure the main functionalities work as expected. Run the tests using the following command:

./mvnw test

Scheduled Jobs
The service includes a daily job that calculates the total amount of transactions and prints it to the terminal. This job is scheduled to run every 24 hours.
