CREATE TABLE images_details
(
    images_id  integer,
    title      varchar,
    promt      text,
    width      integer,
    height     integer,
    styles     varchar,
    created_at timestamp,
    FOREIGN KEY ("images_id") REFERENCES "images" ("id")
        ON UPDATE CASCADE ON DELETE CASCADE
);