DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS position;

CREATE TABLE department
(
    id          SERIAL PRIMARY KEY      NOT NULL,
    create_date TIMESTAMP DEFAULT now() NOT NULL,
    name        VARCHAR UNIQUE          NOT NULL,
    parent_id   INTEGER   DEFAULT NULL,
    FOREIGN KEY (parent_id) REFERENCES department (id)
);

CREATE UNIQUE INDEX department_unique_name_idx
    ON department (name);

CREATE TABLE position
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL
);
CREATE UNIQUE INDEX position_unique_name_idx
    ON position (name);

CREATE TABLE employee
(
    id              SERIAL PRIMARY KEY,
    birth_date      TIMESTAMP                         NOT NULL,
    email           VARCHAR                           NOT NULL,
    employment_date TIMESTAMP                         NOT NULL,
    manager         BOOLEAN DEFAULT FALSE,
    name            VARCHAR                           NOT NULL,
    surname         VARCHAR                           NOT NULL,
    patronymic      VARCHAR,
    phone           VARCHAR                           NOT NULL,
    salary          INTEGER
        CONSTRAINT positive_salary CHECK (salary > 0) NOT NULL,
    sex             VARCHAR                           NOT NULL,
    retire_date     TIMESTAMP,
    department_id   INTEGER,
    position_id     INTEGER,
    FOREIGN KEY (department_id) REFERENCES department (id),
    FOREIGN KEY (position_id) REFERENCES position (id)
);


