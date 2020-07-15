CREATE TABLE hospital (
    id int NOT NULL,
    name varchar(255) NOT NULL,
    address varchar(255) NOT NULL,
    CONSTRAINT PK_hospital PRIMARY KEY (id)
);

CREATE TABLE doctor (
    id int NOT NULL,
    name varchar(255) NOT NULL,
    education varchar(255) NOT NULL,
	experience NUMERIC (2, 2),
	previous_employer varchar(255),
	acievements varchar(300),
	specialization varchar(300),
	current_employer_id int FOREIGN KEY REFERENCES hospital(id),
	CONSTRAINT FK_hospital_doctor FOREIGN KEY (current_employer_id) REFERENCES hospital(id),
    CONSTRAINT PK_doctor PRIMARY KEY (id)
);

CREATE TABLE patient (
    token_no int NOT NULL,
	name varchar(100) not null,
    visit_date date not null,
    purpose varchar(50) NOT NULL,
	mobule_no varchar(15),
	doctor_id int,
	address varchar(300),
	hospital_id int FOREIGN KEY REFERENCES hospital(id),
	CONSTRAINT FK_patient_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    CONSTRAINT PK_patient PRIMARY KEY (token_no)
);

CREATE TABLE login (
	id int not null PRIMARY KEY,
	user_name varchar(100) not null,
    name varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
	password NVARCHAR(1000) NOT NULL,
	mobile_no varchar(15),
	address varchar(300)
);

CREATE SEQUENCE login_seq
  AS BIGINT
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 99999
  NO CYCLE
  CACHE 10;
  
  CREATE TABLE user_role (
	id int not null PRIMARY KEY,
    role varchar(15) not null,
	user_id int not null,
	CONSTRAINT FK_user_role FOREIGN KEY (user_id) REFERENCES login(id),
);

CREATE SEQUENCE user_role_seq
  AS BIGINT
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 99999
  NO CYCLE
  CACHE 10;
  
  
  ALTER SEQUENCE user_role_seq RESTART WITH 1 ;  