DROP TABLE IF EXISTS sector CASCADE;
CREATE TABLE sector
(
    id          SERIAL   NOT NULL    UNIQUE
        CONSTRAINT sectors_pkey PRIMARY KEY,
    name        TEXT     NOT NULL,
    parent_id   INTEGER  NULL,
    indent      INTEGER  NOT NULL,

    FOREIGN KEY (parent_id) REFERENCES sector(id)
);