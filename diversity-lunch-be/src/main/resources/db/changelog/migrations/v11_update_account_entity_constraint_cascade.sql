--liquibase formatted sql

-- changeset dfuerst:v11-1
ALTER TABLE account_entity
DROP CONSTRAINT fk_profile,
ADD CONSTRAINT fk_profile FOREIGN KEY (profile_id) REFERENCES profile_entity (id) ON UPDATE NO ACTION ON DELETE Cascade;

