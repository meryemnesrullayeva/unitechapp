# UnitechApp Application Assignment for Backend Developer Interview

You are selected backend developer for a new product named UniTech. It is fintech solution for bank products. In first version we have 5 functionalities. Every functionality described below. You as a backend developer must implement all functionalities and write unit tests for each case.
1. Register API
Description: As a user I want to register in UniTech app.
Definition of done:
1.1. I am able to register with my pin
2.2. I get proper error message when I try to register with already registered pin 
3.3. All unit tests pass

2. Login API
Description: As a user I want to login to my UniTech account
Definition of done:
2.1. I am able to login to my account with my pin and password 
2.2. I get proper error message when I type wrong credentials 
2.3. All unit tests pass


3. Get accounts API
Description: As a user I want to see my accounts
Definition of done:
3.1. I can only see my active accounts 
3.2. All unit tests pass

4. Account to account API
Description: As a user I want to make transfers from my accounts to other accounts (also to my other accounts)
Definition of done:
4.1. I am able to successfully make transfers
4.2. I get a proper error message when there is no enough money in my account balance 
4.3. I get a proper error message when I try to make transfer to same account
4.4. I get a proper error message when I try to make transfer to deactive account
4.5. I get a proper error message when I try to make transfer to non existing account
4.6. All unit tests pass

5. Currency rates API
Description: As a user I want to see selected currency rate. Currency rate is a currency pair. For example: USD/AZN = 1.7, AZN/TL = 8. Assume that currencies come from third party service and every request has some cost (You can mock this service). Currency rates change every 1 minute in third party side. Apply optimal solution for both performance and cost.
Definition of done:
4.1. I am able to see selected currency rate. It must be up to date. 
4.2. It should cost to company as low as possible
4.3. All unit tests pass


Technology Stack:

• Java language.

• Spring Boot framework.

• Spring Data JPA.

• Maven - Dependency Management

• Swagger for API documentation.

• JWT for authentication services.

• Microservice architecture.

• PostgreSQL for database

• Liquibase






URLS:


Auth API : http://localhost:8081/swagger-ui/index.html

Account API : http://localhost:8082/swagger-ui/index.html

Currency API : http://localhost:8080/swagger-ui/index.html



Here is main methods for operations:

1. Create user method is  : http://localhost:8081/swagger-ui/index.html#/register-controller/insertUser

2. Get auth JWT token first need to login(created test user name : admin, password : Admin@123): http://localhost:8081/swagger-ui/index.html#/auth-controller/login 

 Create user account is : http://localhost:8082/swagger-ui/index.html#/UserAccount/add
3.Get User Accounts is : http://localhost:8082/swagger-ui/index.html#/UserAccount/getByUserId

 Top up balance by accountnumber : http://localhost:8082/swagger-ui/index.html#/Payment/topUp
4. Transfer money  : http://localhost:8082/swagger-ui/index.html#/Payment/transfer

For currency we have third party service : http://localhost:8080/swagger-ui/index.html#/Currency/getCurrency
we can get currency rate by sending like 'AZN/TL'

5. Currency check method which cost everytime is : http://localhost:8082/swagger-ui/index.html#/Payment/currency


