--liquibase formatted sql

-- changeset dwolz:v4-1
CREATE TABLE IF NOT EXISTS discrimination_entity (
                                                        id BIGSERIAL PRIMARY KEY,
                                                        descriptor VARCHAR(255) UNIQUE
    );

-- changeset dwolz:v4-2
INSERT INTO discrimination_entity(descriptor) VALUES
                                                     ('Ja'),
                                                     ('Nein'),
                                                     ('keine Angabe');

-- changeset dwolz:v4-3
ALTER TABLE profile_entity ADD COLUMN IF NOT EXISTS discrimination_id BIGINT
    NOT NULL DEFAULT 3
    CONSTRAINT fk_discrimination_background
    REFERENCES discrimination_entity (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
