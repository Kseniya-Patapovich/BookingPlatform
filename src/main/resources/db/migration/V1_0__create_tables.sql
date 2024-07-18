CREATE TABLE IF NOT EXISTS venues
(
    id bigserial PRIMARY KEY ,
    name varchar(200) NOT NULL ,
    address varchar(150) NOT NULL ,
    capacity INT NOT NULL
);

CREATE TABLE IF NOT EXISTS booking
(
    id BIGSERIAL PRIMARY KEY ,
    name varchar(100) NOT NULL ,
    start_date DATE NOT NULL CHECK ( start_date >= CURRENT_DATE ),
    end_date   DATE NOT NULL CHECK (end_date >= CURRENT_DATE),
    number_of_participants INTEGER NOT NULL CHECK (number_of_participants >= 1),
    booking_date DATE NOT NULL DEFAULT CURRENT_DATE,
    venue_id INTEGER NOT NULL, FOREIGN KEY (venue_id) REFERENCES venues (id)
);