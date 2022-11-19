CREATE TABLE sub_keywords
(
    owner_id BIGINT NOT NULL,
    keyword  VARCHAR(1000)
);

ALTER TABLE sub_keywords
    ADD CONSTRAINT fk_sub_keywords_on_sub FOREIGN KEY (owner_id) REFERENCES sub (id);