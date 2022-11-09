--liquibase formatted sql

-- changeset dfuerst:v2-1
CREATE TABLE IF NOT EXISTS sexual_orientation_entity (
   id BIGSERIAL PRIMARY KEY,
   descriptor VARCHAR(255) UNIQUE
);
-- changeset dfuerst:v2-2
INSERT INTO sexual_orientation_entity(descriptor) VALUES
        ('Heterosexuell'),
        ('Homosexuell'),
        ('Bisexuell'),
        ('Asexuell'),
        ('Pansexuell'),
        ('weitere Orientierungen'),
        ('keine Angabe');

-- changeset dfuerst:v2-3
ALTER TABLE profile_entity ADD COLUMN sexual_orientation_id BIGINT NOT NULL DEFAULT 7 CONSTRAINT fk_sexual_orientation
    REFERENCES sexual_orientation_entity (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
