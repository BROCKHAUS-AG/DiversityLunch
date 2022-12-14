--liquibase formatted sql


-- changeset pblesin:v9-1
ALTER TABLE hobby_entity DROP COLUMN category_id;
DROP TABLE hobby_category_entity;