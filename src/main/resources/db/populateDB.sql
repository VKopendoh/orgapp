DELETE
FROM employee;
DELETE
FROM department;
DELETE
FROM position;


INSERT INTO position (name)
VALUES ('manager'),
       ('account manager'),
       ('boss');

INSERT INTO department (name, parent_id)
VALUES ('Main department', null),
       ('branch 1', 1),
       ('branch 2', 1),
       ('branch 1 1', 2),
       ('branch 2 1', 3),
       ('branch 1 2', 2),
       ('branch 1 2 1', 6),
       ('branch 1 2 2', 6),
       ('branch 1 2 1 1', 8);

INSERT INTO employee (birth_date, email, employment_date, name, surname,
                      patronymic, phone, salary, sex, retire_date,
                      department_id, position_id, manager)
VALUES ('1990-05-30', 'mail@one', '2019-05-30', 'Иван', 'Иванов',
        'Иванович', '123456789', 100000, 'MALE', null, 1, 3, true),
       ('1995-05-30', 'mail@two', '2019-05-30', 'Петр', 'Петров',
        'Иванович', '23456789', 90000, 'MALE', null, 2, 2, true),
       ('1991-05-30', 'mail@three', '2019-05-30', 'Сергей', 'Иванов',
        'Иванович', '3456789', 90000, 'MALE', null, 6, 2, true),
       ('1992-05-30', 'mail@five', '2019-05-30', 'Анна', 'Иванова',
        'Ивановна', '456789', 20000, 'FEMALE', null, 2, 1, false),
       ('1980-05-30', 'mail@six', '2019-05-30', 'Полина', 'Петрова',
        'Ивановна', '56789123', 30000, 'FEMALE', null, 2, 1, false),
       ('1993-05-30', 'mail@seven', '2019-05-30', 'Иван', 'Иванов',
        'Иванович', '67894444', 30000, 'MALE', null, 6, 1, false),
       ('1994-05-30', 'mail@eight', '2019-05-30', 'Иван', 'Иванов',
        'Иванович', '78933333', 30000, 'MALE', null, 6, 1, false),
       ('1997-05-30', 'mail@nine', '2019-05-30', 'Иван', 'Иванов',
        'Иванович', '89234324', 30000, 'MALE', null, 1, 1, false);
