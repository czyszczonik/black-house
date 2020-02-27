USE blackhouse;

CREATE TABLE Participants (
	ID INT unsigned NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(30),
	last_name VARCHAR(50),
	age INT(2) unsigned,
	email VARCHAR(50),
	phone INT(9) unsigned,
	PRIMARY KEY (ID)
);

CREATE TABLE Extras (
	participantID INT unsigned,
	tax BOOLEAN,
	beding BOOLEAN,
	cardre BOOLEAN,
	tshirt BOOLEAN,
	PRIMARY KEY (participantID),
	 CONSTRAINT `fk_extras`
    FOREIGN KEY (participantID) REFERENCES Participants(ID)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
);

CREATE TABLE Tshirts (
	participantID INT unsigned,
	color VARCHAR(25),
	size VARCHAR(5),
	type VARCHAR(15),
	received BOOLEAN,
	CONSTRAINT `fk_tshirt`
    FOREIGN KEY (participantID) REFERENCES Participants(ID)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
);

CREATE TABLE Rooms (
	roomID INT unsigned NOT NULL AUTO_INCREMENT,
	capacity INT unsigned,
	floor INT unsigned,
	bathroom BOOLEAN,
	PRIMARY KEY (roomID)
);

CREATE TABLE Visits (
	visitID INT unsigned NOT NULL AUTO_INCREMENT,
	arrivalDate DATE,
	leavingDate DATE,
  participantID INT unsigned,
	roomID INT unsigned,
	PRIMARY KEY (visitID),
	CONSTRAINT `fk_visits_part`
    FOREIGN KEY (participantID) REFERENCES Participants(ID)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_visits_room`
    FOREIGN KEY (roomID) REFERENCES Rooms(roomID)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
);


CREATE TABLE Payments (
	paymentID INT unsigned NOT NULL AUTO_INCREMENT,
	date DATE,
	ammount INT,
	title VARCHAR(50),
  participantID INT unsigned,
	paymentMethod VARCHAR(20),
	PRIMARY KEY (paymentID),
	 CONSTRAINT `fK_payments`
    FOREIGN KEY (participantID) REFERENCES Participants(ID)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
);

