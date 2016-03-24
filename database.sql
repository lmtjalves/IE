# Sequence for client id
CREATE sequence seq_clientId start with 1 increment by 1;

# Stores clients information
CREATE TABLE client (
	id NUMBER(10),
	email VARCHAR(255),
	password VARCHAR(255),
	district VARCHAR(255),
	county VARCHAR(255),
	street VARCHAR(255),
	door_number VARCHAR(255),
	zip_code VARCHAR(255),
	phone_number VARCHAR(20),
	PRIMARY KEY(id)
);
