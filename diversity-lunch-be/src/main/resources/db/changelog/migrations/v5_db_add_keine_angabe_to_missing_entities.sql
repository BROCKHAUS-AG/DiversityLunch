--liquibase formatted sql

-- changeset dfuerst:v5-1
INSERT INTO country_entity(descriptor) VALUES ('keine Angabe');
INSERT INTO diet_entity(descriptor) VALUES ('keine Angabe');
INSERT INTO education_entity(descriptor) VALUES ('keine Angabe');
INSERT INTO hobby_category_entity(descriptor) VALUES ('keine Angabe');
INSERT INTO hobby_entity(descriptor, category_id) VALUES ('keine Angabe', (SELECT id FROM hobby_category_entity WHERE descriptor LIKE 'keine Angabe'));
INSERT INTO language_entity(descriptor) VALUES ('keine Angabe');
INSERT INTO project_entity(descriptor) VALUES ('keine Angabe');
INSERT INTO religion_entity(descriptor) VALUES ('keine Angabe');
INSERT INTO work_experience_entity(descriptor) VALUES ('keine Angabe');


