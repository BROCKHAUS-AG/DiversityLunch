--liquibase formatted sql

-- changeset dwolz:v5-1
CREATE TABLE IF NOT EXISTS social_background_discrimination_entity (
        id BIGSERIAL PRIMARY KEY,
        descriptor VARCHAR(255) UNIQUE
    );

-- changeset dwolz:v5-2
INSERT INTO social_background_discrimination_entity(descriptor) VALUES
                                                     ('Ja'),
                                                     ('Nein'),
                                                     ('keine Angabe');

-- changeset dwolz:v5-3
ALTER TABLE profile_entity ADD COLUMN IF NOT EXISTS social_background_discrimination_id BIGINT
    NOT NULL DEFAULT 3
    CONSTRAINT fk_social_background_discrimination
    REFERENCES social_background_discrimination_entity (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
