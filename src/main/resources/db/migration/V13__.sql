CREATE TABLE sub_ord2
(
    owner_id BIGINT NOT NULL,
    ord_2    INTEGER
);

ALTER TABLE sub_ord2
    ADD CONSTRAINT fk_sub_ord2_on_sub FOREIGN KEY (owner_id) REFERENCES sub (id);