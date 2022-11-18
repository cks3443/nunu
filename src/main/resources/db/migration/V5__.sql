CREATE TABLE sub_files
(
    owner_id BIGINT NOT NULL,
    file     VARCHAR(500)
);

ALTER TABLE sub_files
    ADD CONSTRAINT fk_sub_files_on_sub FOREIGN KEY (owner_id) REFERENCES sub (id);