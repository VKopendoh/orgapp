DROP TABLE IF EXISTS department_history;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS payroll;
DROP TABLE IF EXISTS position;

CREATE TABLE payroll
(
    id              SERIAL PRIMARY KEY,
    department_name VARCHAR,
    sum             INTEGER
);

CREATE TABLE department
(
    id          SERIAL PRIMARY KEY,
    create_date TIMESTAMP DEFAULT now(),
    name        VARCHAR UNIQUE NOT NULL,
    parent_id   INTEGER,
    payroll_id  INTEGER,
    FOREIGN KEY (parent_id) REFERENCES department (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (payroll_id) REFERENCES payroll (id)
);

CREATE UNIQUE INDEX department_unique_name_idx
    ON department (name);

CREATE TABLE history
(
    id          SERIAL PRIMARY KEY,
    action      varchar(20) NOT NULL,
    created     TIMESTAMP   NOT NULL DEFAULT now(),
    description VARCHAR
);

CREATE TABLE department_history
(
    history_id    INTEGER NOT NULL,
    department_id INTEGER NOT NULL,
    FOREIGN KEY (history_id) REFERENCES history (id),
    FOREIGN KEY (department_id) REFERENCES department (id)
);

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
    birth_date      date                               NOT NULL,
    email           VARCHAR                            NOT NULL,
    employment_date date                               NOT NULL,
    manager         BOOLEAN DEFAULT FALSE,
    name            VARCHAR                            NOT NULL,
    surname         VARCHAR                            NOT NULL,
    patronymic      VARCHAR,
    phone           VARCHAR                            NOT NULL,
    salary          INTEGER
        CONSTRAINT positive_salary CHECK (salary >= 0) NOT NULL,
    sex             VARCHAR                            NOT NULL,
    retire_date     date,
    department_id   INTEGER,
    position_id     INTEGER,
    FOREIGN KEY (department_id) REFERENCES department (id) ON UPDATE CASCADE ,
    FOREIGN KEY (position_id) REFERENCES position (id)
);


