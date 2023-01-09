--liquibase formatted sql

-- changeset dfuerst:v17-1
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id)
    (SELECT id, diet_id
     FROM profile_entity);
----------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id)
    (SELECT id, education_id
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id)
    (SELECT id, gender_id
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id)
    (SELECT id, mother_tongue_id
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id)
    (SELECT id, origin_country_id
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id)
    (SELECT id, project_id
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id)
    (SELECT id, religion_id
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id)
    (SELECT id, sexual_orientation_id
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id)
    (SELECT id, social_background_id
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id)
    (SELECT id, social_background_discrimination_id
     FROM profile_entity);--------------------------------------

-- changeset dfuerst:v17-2
--------------------------------------
INSERT INTO profile_entity_selected_weighted_values(profile_entity_id, selected_weighted_values_id)
    (SELECT id, work_experience_id
     FROM profile_entity);
--------------------------------------

-- changeset dfuerst:v17-3
INSERT INTO profile_entity_selected_multiselect_values(profile_id)
 (SELECT id
FROM profile_entity);


INSERT INTO profile_entity_selected_multiselect_values_selected_options(profile_entity_selected_multiselect_values_id, selected_options_id)
(SELECT a.id, b.hobby_id FROM profile_entity p
JOIN profile_entity_selected_multiselect_values a on p.id = a.profile_id
JOIN profile_hobby b on p.id = b.profile_id);

-- changeset dfuerst v17-4
ALTER TABLE profile_entity DROP COLUMN diet_id;
ALTER TABLE profile_entity DROP COLUMN education_id;
ALTER TABLE profile_entity DROP COLUMN gender_id;
ALTER TABLE profile_entity DROP COLUMN mother_tongue_id;
ALTER TABLE profile_entity DROP COLUMN origin_country_id;
ALTER TABLE profile_entity DROP COLUMN project_id;
ALTER TABLE profile_entity DROP COLUMN religion_id;
ALTER TABLE profile_entity DROP COLUMN work_experience_id;
ALTER TABLE profile_entity DROP COLUMN sexual_orientation_id;
ALTER TABLE profile_entity DROP COLUMN social_background_id;
ALTER TABLE profile_entity DROP COLUMN social_background_discrimination_id;

UPDATE profile_entity SET was_changed_by_admin = false WHERE was_changed_by_admin IS NULL;
ALTER TABLE profile_entity ALTER COLUMN was_changed_by_admin SET NOT NULL;

DROP TABLE country_entity;
DROP TABLE diet_entity;
DROP TABLE education_entity;
DROP TABLE gender_entity;
DROP TABLE profile_hobby;
DROP TABLE hobby_entity;
DROP TABLE language_entity;
DROP TABLE project_entity;
DROP TABLE religion_entity;
DROP TABLE sexual_orientation_entity;
DROP TABLE social_background_discrimination_entity;
DROP TABLE social_background_entity;
DROP TABLE work_experience_entity;
