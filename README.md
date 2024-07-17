# BookingPlatform

### Task description
An event booking management system in which you can manage event venues  and bookings . Multiple bookings can correspond to one venue, which demonstrates the one-to-many attitude.

### Technologies:
- Spring Boot
- Maven
- Postgres
- Spring Data Jpa
- Postman

### Capabilities:
1. Create new venue.

  To create a new venue, you will need:
  - Path: <http://localhost:8080/venues>
  - Method: Post
  - Body:
  
  ```json
  
     {
      "name":"Claude Monet",
      "address": "Moscow",
      "capacity":10
     }
  
  ```
- Response: 201 CREATED, and id new venue. Or 409 CONFLICT if incorrect data was entered.

2. Get all venues.

   To get all venues, you will need:
   - Path: <http://localhost:8080/venues>
   - Method: Get
   - Response: 200 OK, and json all venues.

3. Get venue by id.

   To get venue by id, you will need:
   - Path: <http://localhost:8080/venues/1>
   - Method: Get
   - Response: 200 OK and json venue. Or 404 NOT_FOUND if venue with tis id not found.

4. Change venue.

   To update venue, you will need:
   - Path: <http://localhost:8080/venues/1>
   - Method: Post
   - Body:

      ```json
  
     {
      "name":"Conference Hall",
      "address": "Moscow",
      "capacity":100
     }
  
  ```
  - Response: 204 NO_CONTENT. Or 409 CONFLICT if incorrect data was entered.

 5. 

   
