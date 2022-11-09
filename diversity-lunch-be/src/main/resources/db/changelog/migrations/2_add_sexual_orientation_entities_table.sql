--liquibase formatted sql

-- changeset dfuerst:17
CREATE TABLE IF NOT EXISTS sexual_orientation_entity (
   id BIGSERIAL PRIMARY KEY,
   descriptor VARCHAR(255)
);
-- changeset dfuerst:18
INSERT INTO sexual_orientation_entity(descriptor) VALUES
      ('Heterosexuell'),
      ('Homesexuell'),
    ('Bisexuell'),
       ('Asexuell'),
        ('Pansexuell'),
        ('weitere Orientierungen'),
        ('keine Angabe');

-- changeset dfuerst:19
ALTER TABLE profile_entity ADD COLUMN sexual_orientation_id BIGINT CONSTRAINT fk_sexual_orientation REFERENCES sexual_orientation_entity (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
