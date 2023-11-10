CREATE TABLE IF NOT EXISTS sector
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    name      VARCHAR(150)          NOT NULL,
    parent_id BIGINT
    );

CREATE TABLE IF NOT EXISTS form
(
    id             BIGSERIAL PRIMARY KEY NOT NULL,
    username       VARCHAR(100)          NOT NULL,
    sector_id      BIGINT                NOT NULL,
    agree_to_terms BOOLEAN               NOT NULL
    );

ALTER TABLE sector
    ADD CONSTRAINT sector_parent_sector_fk FOREIGN KEY (parent_id) REFERENCES sector;
ALTER TABLE form
    ADD CONSTRAINT form_sector_fk FOREIGN KEY (sector_id) REFERENCES sector;
