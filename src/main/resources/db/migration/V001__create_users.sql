CREATE TABLE users
(
    id         serial PRIMARY KEY NOT NULL,
    login      varchar            NOT NULL,
    password   varchar            NOT NULL,
    role       varchar            NOT NULL,
    is_deleted boolean DEFAULT false
);