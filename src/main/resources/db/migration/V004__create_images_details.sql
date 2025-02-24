CREATE TABLE images_details
(
    id         serial PRIMARY KEY NOT NULL,
    images_id  integer,
    title      varchar,
    prompt     text,
    width      integer,
    height     integer,
    styles     varchar,
    created_at timestamp,
    FOREIGN KEY ("images_id") REFERENCES "images" ("id")
        ON UPDATE CASCADE ON DELETE CASCADE
);