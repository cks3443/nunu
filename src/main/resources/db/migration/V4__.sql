CREATE TABLE sub_images
(
    owner_id BIGINT NOT NULL,
    image    VARCHAR(255)
);

ALTER TABLE sub_images
    ADD CONSTRAINT fk_sub_images_on_sub FOREIGN KEY (owner_id) REFERENCES sub (id);