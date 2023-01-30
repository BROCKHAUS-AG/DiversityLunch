--liquibase formatted sql


-- changeset adianati:v11-1
ALTER TABLE profile_entity ADD COLUMN was_changed_by_admin BOOLEAN DEFAULT FALSE;
