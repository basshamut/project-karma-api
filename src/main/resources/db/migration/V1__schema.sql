CREATE TABLE karma (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    number  INT,
    missing VARCHAR(255),
    situation VARCHAR(255),
    improve VARCHAR(255)
);

CREATE TABLE birthyear (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    year_preffix VARCHAR(255),
    letter_code  VARCHAR(255),
    year_suffix  VARCHAR(255),
    current_sex  VARCHAR(255)
);

CREATE TABLE birthmounth (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    mounth           VARCHAR(255),
    profesion        VARCHAR(255),
    simbol_type      VARCHAR(255),
    orientation_type VARCHAR(255),
    letter_code      VARCHAR(255),
    sex_in_past      VARCHAR(255),
    current_sex      VARCHAR(255)
);

CREATE TABLE birthday (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    simbol_type VARCHAR(255),
    sex_in_past VARCHAR(255),
    location    VARCHAR(255),
    birth_day   VARCHAR(255),
    current_sex VARCHAR(255)
);

CREATE TABLE encarnationdate (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    orientation_type VARCHAR(255),
    letter_code      VARCHAR(255),
    year_encarnation VARCHAR(255)
);

CREATE TABLE encarnationlocation (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    location      VARCHAR(255),
    location_name VARCHAR(255)
);

CREATE TABLE profession_lookup (
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    code               VARCHAR(10)   NOT NULL,
    description_male   VARCHAR(500),
    description_female VARCHAR(500)
);

CREATE TABLE personality_lookup (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    simbol_code      VARCHAR(10)  NOT NULL,
    simbol_name      VARCHAR(100),
    description_odd  VARCHAR(500),
    description_even VARCHAR(500)
);
