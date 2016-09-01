USE AirTicketReservationSystem;

INSERT INTO roles(name)
	SELECT 'Public User'
UNION ALL
	SELECT 'Airline Staff';

INSERT INTO user(name, username, password, rol_id)
	SELECT 'Cesar Chavez', 'admin', 'ge9aFH4nd5sxpkctQZ7apA==', 2
UNION ALL
	SELECT 'Cesar Manuel', 'user', 'ge9aFH4nd5sxpkctQZ7apA==', 1

