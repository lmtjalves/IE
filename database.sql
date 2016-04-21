# Sequence for client id
CREATE sequence seq_clientId start with 1 increment by 1;

# Stores clients information
CREATE TABLE client (
	id NUMBER(10),
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	district VARCHAR(255) NOT NULL,
	county VARCHAR(255) NOT NULL,
	street VARCHAR(255) NOT NULL,
	door_number VARCHAR(255) NOT NULL,
	zip_code VARCHAR(255) NOT NULL,
	phone_number VARCHAR(20) NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT email_unique UNIQUE (email)
);

# Sequence for transporters id
CREATE sequence seq_transporterId start with 1 increment by 1;

# Stores transporters information
CREATE TABLE transporter (
	id NUMBER(10),
	name VARCHAR(255) NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT name_unique UNIQUE (name)
);

# Sequence for the transportation id
CREATE sequence seq_transportationId start with 1 increment by 1;

# Stores all transportations
CREATE TABLE transportation (
	id NUMBER(10),
	transporterId NUMBER(10) NOT NULL,
	clientId NUMBER(10) NOT NULL,
	quotation NUMBER(10) NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_transporter 
		FOREIGN KEY (transporterId)
		REFERENCES transporter(id),
	CONSTRAINT fk_client
		FOREIGN KEY (clientId)
		REFERENCES client(id)
);

INSERT INTO transporter (id, name) VALUES (seq_transporterId.nextval, 'TransporterA');
INSERT INTO transporter (id, name) VALUES (seq_transporterId.nextval, 'TransporterX');


