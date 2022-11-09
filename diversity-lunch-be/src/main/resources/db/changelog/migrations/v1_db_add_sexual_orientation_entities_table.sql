--liquibase formatted sql

-- changeset dfuerst:v1-1
CREATE TABLE IF NOT EXISTS sexual_orientation_entity (
   id BIGSERIAL PRIMARY KEY,
   descriptor VARCHAR(255)
);
-- changeset dfuerst:v1-2
INSERT INTO sexual_orientation_entity(descriptor) VALUES
        ('Heterosexuell'),
        ('Homosexuell'),
        ('Bisexuell'),
        ('Asexuell'),
        ('Pansexuell'),
        ('weitere Orientierungen'),
        ('keine Angabe');

-- changeset dfuerst:v1-3
ALTER TABLE profile_entity ADD COLUMN sexual_orientation_id BIGINT CONSTRAINT fk_sexual_orientation REFERENCES sexual_orientation_entity (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
