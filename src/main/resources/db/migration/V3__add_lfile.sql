CREATE TABLE lfile
(
    id           VARCHAR(255) NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    name         VARCHAR(255),
    type         VARCHAR(255),
    data         OID,
    CONSTRAINT pk_lfile PRIMARY KEY (id)
);