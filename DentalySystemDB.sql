/*CREATE DATABASE dentistDB;
CREATE TABLE Dentist (
DentistId int,
LastName varchar(255),
FirstName varchar(255),
AwardingBody varchar(255),
Specialty varchar(255),
DateOfBirth varchar(255),
PhoneNo int,

PRIMARY KEY(DentistId)
);



CREATE TABLE Paitent (
    PaitentID int,
    LastName varchar(255),
    FirstName varchar(255),
    DateOfBirth varchar(255),
    Email varchar(255),
    Street varchar(255),
	Town varchar(255),
	County varchar(255),
    Eircode varchar(255),
    MedicalCard boolean,
    
    PRIMARY KEY (PaitentID)
);	

CREATE TABLE Appointment (
	AppointmentID int,
    DateOfAppointment date,
    TimeOfAppointment time,
	Attended Boolean,
    
    TreatmentID int,
    PaitentID int,
    DentistID int,
    
    PRIMARY KEY (AppointmentID),
    
    FOREIGN KEY (treatmentID) REFERENCES Treatment(treatmentID),
    FOREIGN KEY (PaitentID) REFERENCES Paitent(PaitentID),
    FOREIGN KEY (DentistID) REFERENCES Dentist(DentistID)
);

CREATE TABLE treatment (
	treatmentID INT AUTO_INCREMENT PRIMARY KEY ,
    treatmentType VARCHAR(50),
    price INT ,
    length INT
);

*/
