
INSERT INTO
    users (first_name,
           last_name,
           email,
           password,
           birthdate)

     VALUES ('noemie',
             'menu',
             'noemie@gmail.com',
             '$2y$12$TZI/bomqG1vVBYbUQ/vvKeBX4MKkZWdC0IqmNHQXrIV3sb6/XBYty',
             '1996-05-12');


INSERT INTO
    internal_bank_account (balance,
                           user_id)
    VALUES (50,
            (select id
            from users
            where email = 'noemie@gmail.com'));

INSERT INTO
    external_bank_account (rib,
                           name,
                           user_id)
VALUES ('fr123456',
        'LCL',
        (select id
         from users
         where email = 'noemie@gmail.com'));

INSERT INTO
    users (first_name,
            last_name,
            email,
            password,
            birthdate)
    VALUES ('paul',
            'P',
            'paul@gmail.com',
            '$2y$12$aZHEDAkG00BBwbzAei.2WeZ/m7epH9meIeML2lqcxwE2bGwSxt/3e',
            '1996-05-12');


INSERT INTO
    internal_bank_account (balance,
                           user_id)
    VALUES (50,
            (select  id
            from users
            where email = 'paul@gmail.com'));

INSERT INTO
    external_bank_account (rib,
                           name,
                           user_id)
VALUES ('AB123456',
        'LCL',
        (select id
         from users
         where email = 'paul@gmail.com'));

INSERT INTO
    friends(USER_ID,
            FRIEND_USER_ID)
    VALUES ((SELECT id
             FROM users
             WHERE email ='noemie@gmail.com'),
            (SELECT id
            FROM users
            WHERE email = 'paul@gmail.com'))
