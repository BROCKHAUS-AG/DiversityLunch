--liquibase formatted sql

-- changeset dfuerst:v6-1
ALTER TABLE work_experience_entity ADD COLUMN weight Int NOT NULL DEFAULT 1 CHECK ( weight > -1 );
UPDATE work_experience_entity SET weight = 0 WHERE descriptor LIKE 'keine Angabe';
UPDATE work_experience_entity SET weight = 2 WHERE descriptor LIKE '4-10 Jahre';
UPDATE work_experience_entity SET weight = 3 WHERE descriptor LIKE 'über 10 Jahre';
