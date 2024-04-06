CREATE TABLE users_details
(
    users_id   integer,
    first_name varchar,
    last_name  varchar,
    login      varchar,
    created_at timestamp,
    FOREIGN KEY (users_id) REFERENCES users (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);