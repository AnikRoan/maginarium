CREATE TABLE images
(
    id         serial PRIMARY KEY NOT NULL,
    uuid       uuid UNIQUE        NOT NULL,
    s3_link    text,
    is_private bool DEFAULT false,
    is_deleted bool DEFAULT false,
    gallery_id integer
);