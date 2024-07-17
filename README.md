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
#### Venue - entity for managing event venues
1. Create new venue.  
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
    - Method: Put
    - Body:

     ```json
     {
      "name":"Conference Hall",
      "address": "Moscow",
      "capacity":100
     }
     ```
    - Response: 204 NO_CONTENT. Or 409 CONFLICT if incorrect data was entered.

 5. Delete venue by id.

    To delete venue by id, you will need:
    - Path <http://localhost:8080/venues/1>
    - Method: Delete
    - Response: 204 NO_CONTENT. Or 404 NOT_FOUND if venue with this id not found. Also you cannot delete venue with booking.

#### Booking - entity for managing bookings.
1. Create new booking.
   - Path: <http://localhost:8080/booking>
   - Method: Post
   - Body:

     ```json
     {
      "name":"Pavel",
      "numberOfParticipants":1000,
      "bookingDate":"2024-10-01",
      "venueId":1
     }
     ```
   - Response: 201 CREATED, and id new venue. Or 409 CONFLICT if incorrect data was entered, for example: you cannot enter date in past, or you cannot book if venue already booked on this date, or number of participients will be not more than capacity.

2. Get all bookings.
   - Path <http://localhost:8080/booking>
   - Method: Get
   - Response: 200 OK

3. Get booking by id.
   - Path: <http://localhost:8080/booking/1>
   - Method: Get
   - Response: 200 OK with json booking. Or 404 NOT_FOUND.

4. Get all bookings by date.
   - Path: <http://localhost:8080/booking/date?bookingDate=2024-10-01>
   - Method: Get
   - Response: 200 OK. Or 404 NOT_FOUND if not found bookings on this date

5. Get all bookings in one venue.
   - Path: <http://localhost:8080/booking/venue/1>
   - Method: Get
   - Response: 200 OK. Or 404 NOT_FOUND if not found venue or venue doesn't have any bookings.

6. Change booking.
   - Path: <http://localhost:8080/booking/1>
   - Method: Put
   - Body:

     ```json
     {
      "name":"Svetlana",
      "numberOfParticipants":100,
      "bookingDate":"2024-10-03",
      "venueId":1
     }
     ```
   - Response: 204 NO_CONTENT. Or 409 CONFLICT if incorrect data was entered.

7. Delete booking.
   - Path: <http://localhost:8080/booking/1>
   - Method: Delete
   - Response: 204 NO_CONTENT. Or 404 NOT_FOUND if venue with this id not found.
   
