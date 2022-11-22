ALTER TABLE sub_keywords
DROP
CONSTRAINT fk_sub_keywords_on_sub;

DROP TABLE sub_keywords CASCADE;