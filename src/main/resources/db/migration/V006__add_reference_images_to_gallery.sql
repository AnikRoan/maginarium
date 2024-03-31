ALTER TABLE images
    ADD CONSTRAINT images_users_gallery_id_fk
        FOREIGN KEY (gallery_id) REFERENCES users_gallery;