--liquibase formatted sql


-- changeset jevers:v13-1
ALTER TABLE hobby_entity ADD COLUMN is_default boolean not null default false;
