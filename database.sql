-- Sequence for client id
CREATE sequence seq_clientId start with 1 increment by 1;

-- Stores clients information
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

-- Sequence for transporters id
CREATE sequence seq_transporterId start with 1 increment by 1;

-- Stores transporters information
CREATE TABLE transporter (
	id NUMBER(10),
	name VARCHAR(255) NOT NULL,
        url VARCHAR(500) NOt NULL,
        removed VARCHAR(1) DEFAULT 'N' NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT name_unique UNIQUE (name)
);

-- Sequence for the transportation id
CREATE sequence seq_transportationId start with 1 increment by 1;

-- Stores all transportations
-- Paid may be N - Not paid, P - Paid, W - waiting (some process is managing the payment
CREATE TABLE transportation (
	id NUMBER(10),
	transporterId NUMBER(10) NOT NULL,
	clientId NUMBER(10) NOT NULL,
	quotation BINARY_DOUBLE NOT NULL,
	paid VARCHAR(1) DEFAULT 'N' NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_transporter 
		FOREIGN KEY (transporterId)
		REFERENCES transporter(id),
	CONSTRAINT fk_client
		FOREIGN KEY (clientId)
		REFERENCES client(id)
);

INSERT INTO transporter (id, name, url) VALUES (seq_transporterId.nextval, 'TransporterA', 'http://soabpm-vm.site:7001/RequestQuotationTransporterA-RequestQuotationTransporterAWS-context-root/RequestQuotationTransporterAServiceSoap12HttpPort?wsdl');
INSERT INTO transporter (id, name, url) VALUES (seq_transporterId.nextval, 'TransporterZ', 'http://soabpm-vm.site:7001/RequestQuotationTransporterZ-RequestQuotationTransporterZWS-context-root/RequestQuotationTransporterZServiceSoap12HttpPort?wsdl');

-- Stores counters for the twitter clients conversations
-- they will be used for the correlation with BPEL processes
CREATE TABLE twitter_conversations (
	senderId Number(20) NOT NULL,
	count Number(10) NOT NULL,
	PRIMARY KEY(senderId)
);


CREATE OR REPLACE PROCEDURE missingPaymentsFor(transporter_id IN NUMERIC, total OUT NUMERIC, first_id OUT NUMERIC, last_id OUT NUMERIC)
IS
BEGIN
	SELECT SUM(quotation), MIN(id), MAX(id) 
	INTO total, first_id, last_id
	FROM transportation
	WHERE paid='N' AND transporterId=transporter_id;
	UPDATE transportation SET paid='W' WHERE paid='N' AND transporterId=transporter_id;
END missingPaymentsFor;
/	
