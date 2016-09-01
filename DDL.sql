DROP DATABASE IF EXISTS AirTicketReservationSystem;
-- SET ANSI_WARNINGS OFF
CREATE DATABASE AirTicketReservationSystem;
USE AirTicketReservationSystem;
-- SET ANSI_WARNINGS OFF

-- DROP TABLE IF EXISTS User;
CREATE TABLE User (
	user_id int(8) NOT NULL auto_increment,
	name varchar(20) NOT NULL,  
	username varchar(20) NOT NULL,  
	password varchar(60) NOT NULL,  
	rol_id int(8) NOT NULL,
    active binary(1) NOT NULL default '1',
	CONSTRAINT cliente_pkey PRIMARY KEY (user_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE User
	MODIFY COLUMN password VARCHAR(60);

-- DROP TABLE IF EXISTS Roles;
CREATE TABLE Roles (
	rol_id int(4) NOT NULL auto_increment,
	name varchar(60) NOT NULL,  
    active binary(1) NOT NULL default '1',
	CONSTRAINT rol_pkey PRIMARY KEY (rol_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE User
	ADD CONSTRAINT userRol_fkey FOREIGN KEY (rol_id) REFERENCES Roles(rol_id) ON DELETE CASCADE ON UPDATE CASCADE;
    -- DROP FOREIGN KEY userRol_fkey;

-- DROP TABLE IF EXISTS Flight;
CREATE TABLE Flight (
	flight_id int(8) NOT NULL auto_increment,
	airline_id int(8) NOT NULL,
	from_location varchar(60) NOT NULL,  
	to_location varchar(60) NOT NULL,  
    departure_time datetime default NULL,
    arrival_time datetime default NULL,
    duration int(2) default NULL,
	total_seats int(2) NOT NULL,
    active binary(1) NOT NULL default '1',
	CONSTRAINT flight_pkey PRIMARY KEY (flight_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

-- DROP TABLE IF EXISTS Airline;
CREATE TABLE Airline (
	airline_id int(4) NOT NULL auto_increment,
	name varchar(60) NOT NULL,  
    active binary(1) NOT NULL default '1',
	CONSTRAINT airline_pkey PRIMARY KEY (airline_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE Flight
	ADD CONSTRAINT flightAirline_fkey FOREIGN KEY (airline_id) REFERENCES Airline(airline_id) ON DELETE CASCADE ON UPDATE CASCADE;
    -- DROP FOREIGN KEY flightAirline_fkey;

-- DROP TABLE IF EXISTS FlightDetails;
CREATE TABLE FlightDetails (
	flight_id int(8) NOT NULL,
    flight_departure_date date NOT NULL,
    price float(5,2) NOT NULL default '0.00',
	available_seats int(2) NOT NULL,
	CONSTRAINT flightDetails_pkey PRIMARY KEY (flight_id, flight_departure_date)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

ALTER TABLE FlightDetails
	ADD CONSTRAINT flightDetailsFlight_fkey FOREIGN KEY (flight_id) REFERENCES Flight(flight_id) ON DELETE CASCADE ON UPDATE CASCADE;
    -- DROP FOREIGN KEY flightDetailsFlight_fkey

-- DROP TABLE IF EXISTS Ticket;
CREATE TABLE Ticket (
	ticket_id int(8) NOT NULL auto_increment,
	passenger_id int(8) NOT NULL,
    flight_id int(8) NOT NULL,
    flight_departure_date date NOT NULL,
	status int(8) NOT NULL,  
	CONSTRAINT flightDetails_pkey PRIMARY KEY (ticket_id, passenger_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

-- DROP TABLE IF EXISTS Status;
CREATE TABLE Status (
	status_id int(8) NOT NULL auto_increment,
	name varchar(20) NOT NULL,
	description varchar(60) NOT NULL,
    active binary(1) NOT NULL default '1',
	CONSTRAINT status_pkey PRIMARY KEY (status_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE Ticket
	ADD CONSTRAINT ticketStatus_fkey FOREIGN KEY (status) REFERENCES Status(status_id) ON DELETE CASCADE ON UPDATE CASCADE;
    -- DROP FOREIGN KEY ticketStatus_fkey;

-- DROP TABLE IF EXISTS Passenger;
CREATE TABLE Passenger (
	passenger_id int(8) NOT NULL auto_increment,
	firstName varchar(60) NOT NULL,
	lastName varchar(60) NOT NULL,
	address varchar(60) NOT NULL,
	password varchar(60) NOT NULL,  
	tel_no varchar(30) NOT NULL, 
    email varchar(30) NOT NULL,
	CONSTRAINT passenger_pkey PRIMARY KEY (passenger_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE Ticket
	ADD CONSTRAINT ticketPassenger_fkey FOREIGN KEY (passenger_id) REFERENCES Passenger(passenger_id) ON DELETE CASCADE ON UPDATE CASCADE,
    -- DROP FOREIGN KEY ticketPassenger_fkey,
	ADD CONSTRAINT ticketFlightDetails_fkey FOREIGN KEY (flight_id) REFERENCES FlightDetails(flight_id) ON DELETE CASCADE ON UPDATE CASCADE;
    -- DROP FOREIGN KEY ticketFlightDetails_fkey;

-- DROP TABLE IF EXISTS Passenger;
CREATE TABLE CreditCardDetails (
	passenger_id int(8) NOT NULL auto_increment,
	cardNumber int(8) NOT NULL,
	cardType varchar(60) NOT NULL,
	expirationMonth int(2) NOT NULL,
	expirationYear int(4) NOT NULL,
	CONSTRAINT creditCardDetails_pkey PRIMARY KEY (passenger_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE CreditCardDetails
	ADD CONSTRAINT creditCardDetailsPassenger_fkey FOREIGN KEY (passenger_id) REFERENCES Passenger(passenger_id) ON DELETE CASCADE ON UPDATE CASCADE;
    -- DROP FOREIGN KEY creditCardDetailsPassenger_fkey;
