CREATE TABLE sub_imgframe
(
    owner_id BIGINT NOT NULL,
    imgframe VARCHAR(1000)
);

ALTER TABLE sub
    ADD textarea VARCHAR(3000);

ALTER TABLE sub_imgframe
    ADD CONSTRAINT fk_sub_imgframe_on_sub FOREIGN KEY (owner_id) REFERENCES sub (id);