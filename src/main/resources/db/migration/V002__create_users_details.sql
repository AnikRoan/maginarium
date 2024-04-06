CREATE TABLE users_details
(
    users_id   integer,
    full_name  varchar,
    login      varchar,
    created_at timestamp,
    FOREIGN KEY (users_id) REFERENCES users (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);