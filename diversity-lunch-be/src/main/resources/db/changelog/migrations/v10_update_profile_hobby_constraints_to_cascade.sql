--liquibase formatted sql

-- changeset dfuerst:v10-1
ALTER TABLE profile_hobby
DROP CONSTRAINT fk_hobby_id,
ADD constraint fk_hobby_id  foreign Key (hobby_id)
 references hobby_entity (id)
    ON UPDATE NO ACTION
    ON DELETE CASCADE,
DROP CONSTRAINT  fk_profile_id,
ADD constraint fk_profile_id foreign key(profile_id)
 references profile_entity (id)
    ON UPDATE NO ACTION
       ON DELETE CASCADE;

