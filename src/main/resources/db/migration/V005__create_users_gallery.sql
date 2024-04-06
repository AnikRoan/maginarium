CREATE TABLE users_gallery
(
    id         serial PRIMARY KEY NOT NULL,
    title      varchar,
    users_id   integer,

    created_at timestamp,
    FOREIGN KEY ("users_id") REFERENCES "users" ("id")
        ON UPDATE CASCADE ON DELETE CASCADE,

);