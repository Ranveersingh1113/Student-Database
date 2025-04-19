CREATE DATABASE student_db;
USE student_db;
CREATE TABLE students (
  prn   INT PRIMARY KEY,
  name  VARCHAR(100) NOT NULL,
  dob   DATE,
  marks FLOAT
);
