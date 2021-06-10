
INSERT INTO
    users (first_name,
           last_name,
           email,
           password,
           birthdate,
           balance)

     VALUES ('noemie',
             'menu',
             'noemie@gmail.com',
             '$2y$12$TZI/bomqG1vVBYbUQ/vvKeBX4MKkZWdC0IqmNHQXrIV3sb6/XBYty',
             '1996-05-12',
             '100.00');



INSERT INTO
    users (first_name,
            last_name,
            email,
            password,
            birthdate,
           balance)
    VALUES ('paul',
            'P',
            'paul@gmail.com',
            '$2y$12$aZHEDAkG00BBwbzAei.2WeZ/m7epH9meIeML2lqcxwE2bGwSxt/3e',
            '1996-05-12',
            '100.00');




INSERT INTO
    friends(USER_ID,
            FRIEND_USER_ID)
    VALUES ((SELECT id
             FROM users
             WHERE email ='noemie@gmail.com'),
            (SELECT id
            FROM users
            WHERE email = 'paul@gmail.com'))
