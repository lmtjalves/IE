# Stores client accounts
CREATE TABLE client (
	id NUMERIC(10),
	email VARCHAR(255),
	password VARCHAR(255),
	district VARCHAR(255),
	county VARCHAR(255),
	street VARCHAR(255),
	door_number VARCHAR(20),
	zip_code VARCHAR(255),
	phone_number VARCHAR(20),

	PRIMARY KEY(id)
);
