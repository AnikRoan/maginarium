CREATE TABLE users
(
    id         serial PRIMARY KEY NOT NULL,
    email      varchar            NOT NULL,
    password   varchar            NOT NULL,
    role       varchar            NOT NULL,

);