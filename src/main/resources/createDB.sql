DROP TABLE IF EXISTS sectors CASCADE;
CREATE TABLE sectors
(
    id      SERIAL   NOT NULL    UNIQUE
        CONSTRAINT sectors_pkey PRIMARY KEY,
    name    TEXT     NOT NULL,
    parent  INTEGER  NULL
);