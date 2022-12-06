--liquibase formatted sql


-- changeset jklein:v8-1
CREATE TABLE profile_hobby (
    hobby_id    int REFERENCES hobby_entity (id) ON UPDATE no action ON DELETE CASCADE,
    profile_id int REFERENCES profile_entity (id) ON UPDATE no action ON DELETE CASCADE
);

-- changeset jklein:v8-2
INSERT INTO profile_hobby (hobby_id, profile_id) SELECT hobby_id, id FROM profile_entity;

-- changeset dfuerst:v8-3
DELETE FROM hobby_entity WHERE LOWER(descriptor) = LOWER('keine Angabe');

-- changeset dfuerst:v8-4
ALTER TABLE profile_entity DROP COLUMN hobby_id;

-- changeset dfuerst:v8-5
ALTER TABLE account_entity
DROP CONSTRAINT fk_profile,
ADD CONSTRAINT fk_profile FOREIGN KEY (profile_id) REFERENCES profile_entity (id) ON UPDATE NO ACTION ON DELETE Cascade;


