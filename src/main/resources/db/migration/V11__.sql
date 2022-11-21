CREATE TABLE post_ord
(
    owner_id BIGINT NOT NULL,
    ord      INTEGER
);

ALTER TABLE post_ord
    ADD CONSTRAINT fk_post_ord_on_post FOREIGN KEY (owner_id) REFERENCES post (id);