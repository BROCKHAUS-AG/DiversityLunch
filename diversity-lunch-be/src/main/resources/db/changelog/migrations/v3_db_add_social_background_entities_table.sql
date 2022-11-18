--liquibase formatted sql

-- changeset adianati:v3-1
CREATE TABLE IF NOT EXISTS social_background_entity (
    id BIGSERIAL PRIMARY KEY,
    descriptor VARCHAR(255) UNIQUE
);

-- changeset adianati:v3-2
INSERT INTO social_background_entity(descriptor) VALUES
        ('Akademikerfamilie'),
        ('Nichtakademisches Elternhaus'),
        ('keine Angabe');

-- changeset adianati:v3-3
ALTER TABLE profile_entity ADD COLUMN IF NOT EXISTS social_background_id BIGINT
    NOT NULL DEFAULT 3
    CONSTRAINT fk_social_background
    REFERENCES social_background_entity (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
