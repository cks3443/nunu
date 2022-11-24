CREATE TABLE people
(
    id                      VARCHAR(36)  NOT NULL,
    dtype                   VARCHAR(31),
    email                   VARCHAR(255) NOT NULL,
    pwd                     VARCHAR(255),
    first_name              VARCHAR(255),
    last_name               VARCHAR(255),
    roles                   VARCHAR(255),
    enabled                 BOOLEAN      NOT NULL,
    account_non_expired     BOOLEAN      NOT NULL,
    account_non_locked      BOOLEAN      NOT NULL,
    credentials_non_expired BOOLEAN      NOT NULL,
    created                 TIMESTAMP WITHOUT TIME ZONE,
    modified                TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_people PRIMARY KEY (id)
);

ALTER TABLE people
    ADD CONSTRAINT uc_people_email UNIQUE (email);

DROP TABLE "user" CASCADE;