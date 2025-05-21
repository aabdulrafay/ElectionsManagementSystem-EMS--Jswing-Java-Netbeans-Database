-- Create Database
CREATE DATABASE IF NOT EXISTS ElectionDB;
USE ElectionDB;

SELECT * FROM Voter;

-- Drop existing tables if needed (for clean re-run)
DROP TABLE IF EXISTS Voter;
DROP TABLE IF EXISTS PA_Constituencies;
DROP TABLE IF EXISTS NA_Constituencies;
DROP TABLE IF EXISTS Candidate;
DROP TABLE IF EXISTS Administrators;
DROP TABLE IF EXISTS ElectionControl;
DROP TABLE IF EXISTS ElectionNews;

-- National Assembly Constituencies Table
CREATE TABLE NA_Constituencies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    na_code VARCHAR(20) UNIQUE NOT NULL,
    total_voters INT DEFAULT 0
);

INSERT INTO NA_Constituencies (na_code)
VALUES ('NA-59');

SELECT * from NA_Constituencies;
SELECT * FROM PA_Constituencies;

-- Provincial Assembly Constituencies Table
CREATE TABLE PA_Constituencies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pa_code VARCHAR(20) UNIQUE NOT NULL,
    na_id INT NOT NULL,
    total_voters INT DEFAULT 0,
    FOREIGN KEY (na_id) REFERENCES NA_Constituencies(id) ON DELETE CASCADE
);
INSERT INTO PA_Constituencies (pa_code, na_id)
VALUES ('PA-23', 2);

DELETE FROM NA_Constituencies WHERE na_code = 'PP-25';


SELECT * FROM NA_Constituencies;

UPDATE PA_Constituencies 
SET pa_code = 'PP-23' 
WHERE id = 3;


-- Voter Table
CREATE TABLE Voter (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    cnic VARCHAR(20) UNIQUE NOT NULL,
    na_constituency_id INT NOT NULL,
    pa_constituency_id INT NOT NULL,
    has_voted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (na_constituency_id) REFERENCES NA_Constituencies(id) ON DELETE CASCADE,
    FOREIGN KEY (pa_constituency_id) REFERENCES PA_Constituencies(id) ON DELETE CASCADE
);
INSERT INTO Voter (name, cnic, na_constituency_id, pa_constituency_id)
VALUES ('Shahzain', '5555555555555', 1, 2),
('Zainab Bibi', '6666666666666', 1, 1),
('Zain Ul Abdeen', '3333333333333', 1, 2),
('Bilawal Sharjeel', '1111111111111', 1, 1),
('Rafay Khan', '2222222222222', 1, 1);

SELECT v.name, v.cnic, na.na_code AS na_constituency, pa.pa_code AS pa_constituency, v.has_voted 
FROM voter v 
JOIN NA_Constituencies na ON v.na_constituency_id = na.id 
JOIN PA_Constituencies pa ON v.pa_constituency_id = pa.id;

SELECT * FROM Voter;

UPDATE Voter SET has_voted = FALSE where id = 7;

-- Candidate Table
CREATE TABLE Candidates (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    cnic VARCHAR(20) UNIQUE NOT NULL,
    party VARCHAR(100) NOT NULL,
    seat_type VARCHAR(5) NOT NULL, -- 'NA' or 'PA'
    na_id INT DEFAULT NULL,
    pa_id INT DEFAULT NULL,
    votes INT DEFAULT 0,
    outcome VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (na_id) REFERENCES NA_Constituencies(id) ON DELETE SET NULL,
    FOREIGN KEY (pa_id) REFERENCES PA_Constituencies(id) ON DELETE SET NULL
);

SELECT * FROM Candidates;
DELETE FROM Candidates where id = 1;

UPDATE Candidates 
SET name = 'Abdul Rafay' 
WHERE id = 12;







-- Administrators Table
CREATE TABLE Administrators (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    login_time TIMESTAMP
);


SELECT * FROM Administrators;
INSERT INTO Administrators (username, password, login_time)
VALUES ('admin', 'admin', CURRENT_TIMESTAMP);



-- Election Control Table
CREATE TABLE ElectionControl (
    id INT PRIMARY KEY AUTO_INCREMENT,
    status BOOLEAN,
    start_date VARCHAR(50),
    start_time VARCHAR(50),
    end_date VARCHAR(50),
    end_time VARCHAR(50)
);

SELECT * FROM ElectionControl;

-- Election News Table
CREATE TABLE ElectionNews (
    id INT PRIMARY KEY AUTO_INCREMENT,
    news VARCHAR(255)
);
INSERT INTO ElectionNews (news) VALUES ('New election dates announced for 2025!');
SELECT * FROM ElectionNews;



-- Constituency Election Summary
CREATE TABLE constituency_results (
    id INT PRIMARY KEY AUTO_INCREMENT,
    constituency VARCHAR(100),
    candidate_name VARCHAR(100),
    party VARCHAR(100),
    votes INT DEFAULT 0,
    total_voters INT DEFAULT 0,
    outcome VARCHAR(50)
);

SELECT * FROM constituency_results;

-- Overall Election Summary
CREATE TABLE election_summary (
    id INT PRIMARY KEY AUTO_INCREMENT,
    registered_voters INT DEFAULT 0,
    voted_voters INT DEFAULT 0,
    not_voted_voters INT DEFAULT 0,
    start_date VARCHAR(20),
    start_time VARCHAR(20),
    end_date VARCHAR(20),
    end_time VARCHAR(20),
    overall_turnout VARCHAR(10)
);

SELECT * FROM election_summary;