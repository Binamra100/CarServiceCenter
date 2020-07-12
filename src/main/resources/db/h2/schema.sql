DROP TABLE cars IF EXISTS;
DROP TABLE owners IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE mechanic_specialties IF EXISTS;
DROP TABLE mechanics IF EXISTS;
DROP TABLE specialties IF EXISTS;

CREATE TABLE mechanics (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);
CREATE INDEX mechanics_last_name ON mechanics (last_name);

CREATE TABLE specialties (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX specialties_name ON specialties (name);

CREATE TABLE mechanic_specialties (
  mechanic_id       INTEGER NOT NULL,
  specialty_id INTEGER NOT NULL
);
ALTER TABLE mechanic_specialties ADD CONSTRAINT fk_mechanic_specialties_mechanics FOREIGN KEY (mechanic_id) REFERENCES mechanics (id);
ALTER TABLE mechanic_specialties ADD CONSTRAINT fk_mechanic_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);


CREATE TABLE owners (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR_IGNORECASE(30),
  address    VARCHAR(255),
  city       VARCHAR(80),
  telephone  VARCHAR(20)
);
CREATE INDEX owners_last_name ON owners (last_name);

CREATE TABLE types (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE cars (
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(30),
  manufacture_date DATE,
  type_id    INTEGER NOT NULL,
  owner_id   INTEGER NOT NULL
);
ALTER TABLE cars ADD CONSTRAINT fk_cars_owners FOREIGN KEY (owner_id) REFERENCES owners (id);
ALTER TABLE cars ADD CONSTRAINT fk_cars_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX cars_name ON cars (name);

CREATE TABLE visits (
  id          INTEGER IDENTITY PRIMARY KEY,
  car_id      INTEGER NOT NULL,
  visit_date  DATE,
  description VARCHAR(255)
);
ALTER TABLE visits ADD CONSTRAINT fk_visits_cars FOREIGN KEY (car_id) REFERENCES cars (id);
CREATE INDEX visits_car_id ON visits (car_id);

