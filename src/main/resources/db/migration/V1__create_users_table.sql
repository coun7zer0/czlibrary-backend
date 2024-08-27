CREATE TABLE users (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       email VARCHAR(255),
       password VARCHAR(255),
       name VARCHAR(255),
       lastname VARCHAR(255),
       phone_number VARCHAR(255),
       gender enum('MALE', 'FEMALE', 'OTHER'),
       CONSTRAINT UK_email UNIQUE (email),
       CONSTRAINT UK_phone_number UNIQUE (phone_number)
);
