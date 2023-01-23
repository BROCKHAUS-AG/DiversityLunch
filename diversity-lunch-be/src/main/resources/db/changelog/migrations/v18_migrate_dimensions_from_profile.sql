--liquibase formatted sql

-- changeset dfuerst:v17-1
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT p.id,
            bdso.id,
            (SELECT b.id FROM basic_dimension b WHERE b.dimension_category_id = bdso.dimension_category_id)
     FROM basic_dimension_selectable_option bdso
              JOIN diet_entity de ON bdso.value = de.descriptor AND bdso.dimension_category_id = (SELECT dc.id
                                                                                                  FROM dimension_category dc
                                                                                                  WHERE dc.description = 'Ernährung')
              JOIN profile_entity p ON de.id = p.diet_id);
----------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT p.id,
            bdso.id,
            (SELECT b.id FROM basic_dimension b WHERE b.dimension_category_id = bdso.dimension_category_id)
     FROM basic_dimension_selectable_option bdso
              JOIN education_entity de ON bdso.value = de.descriptor AND bdso.dimension_category_id = (SELECT dc.id
                                                                                                  FROM dimension_category dc
                                                                                                  WHERE dc.description = 'Bildungsweg')
              JOIN profile_entity p ON de.id = p.education_id);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT p.id,
            bdso.id,
            (SELECT b.id FROM basic_dimension b WHERE b.dimension_category_id = bdso.dimension_category_id)
     FROM basic_dimension_selectable_option bdso
              JOIN gender_entity de ON bdso.value = de.descriptor AND bdso.dimension_category_id = (SELECT dc.id
                                                                                                  FROM dimension_category dc
                                                                                                  WHERE dc.description = 'Geschlechtliche Identität')
              JOIN profile_entity p ON de.id = p.gender_id);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT p.id,
            bdso.id,
            (SELECT b.id FROM basic_dimension b WHERE b.dimension_category_id = bdso.dimension_category_id)
     FROM basic_dimension_selectable_option bdso
              JOIN language_entity de ON bdso.value = de.descriptor AND bdso.dimension_category_id = (SELECT dc.id
                                                                                                  FROM dimension_category dc
                                                                                                  WHERE dc.description = 'Muttersprache')
              JOIN profile_entity p ON de.id = p.mother_tongue_id);--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT p.id,
            bdso.id,
            (SELECT b.id FROM basic_dimension b WHERE b.dimension_category_id = bdso.dimension_category_id)
     FROM basic_dimension_selectable_option bdso
              JOIN country_entity de ON bdso.value = de.descriptor AND bdso.dimension_category_id = (SELECT dc.id
                                                                                                  FROM dimension_category dc
                                                                                                  WHERE dc.description = 'Ethnische Herkunft')
              JOIN profile_entity p ON de.id = p.origin_country_id);--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT p.id,
            bdso.id,
            (SELECT b.id FROM basic_dimension b WHERE b.dimension_category_id = bdso.dimension_category_id)
     FROM basic_dimension_selectable_option bdso
              JOIN project_entity de ON bdso.value = de.descriptor AND bdso.dimension_category_id = (SELECT dc.id
                                                                                                  FROM dimension_category dc
                                                                                                  WHERE dc.description = 'Projekt')
              JOIN profile_entity p ON de.id = p.project_id);--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT p.id,
            bdso.id,
            (SELECT b.id FROM basic_dimension b WHERE b.dimension_category_id = bdso.dimension_category_id)
     FROM basic_dimension_selectable_option bdso
              JOIN religion_entity de ON bdso.value = de.descriptor AND bdso.dimension_category_id = (SELECT dc.id
                                                                                                  FROM dimension_category dc
                                                                                                  WHERE dc.description = 'Religion')
              JOIN profile_entity p ON de.id = p.religion_id);--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT p.id,
            bdso.id,
            (SELECT b.id FROM basic_dimension b WHERE b.dimension_category_id = bdso.dimension_category_id)
     FROM basic_dimension_selectable_option bdso
              JOIN sexual_orientation_entity de ON bdso.value = de.descriptor AND bdso.dimension_category_id = (SELECT dc.id
                                                                                                  FROM dimension_category dc
                                                                                                  WHERE dc.description = 'Sexuelle Orientierung')
              JOIN profile_entity p ON de.id = p.sexual_orientation_id);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT p.id,
            bdso.id,
            (SELECT b.id FROM basic_dimension b WHERE b.dimension_category_id = bdso.dimension_category_id)
     FROM basic_dimension_selectable_option bdso
              JOIN social_background_entity de ON bdso.value = de.descriptor AND bdso.dimension_category_id = (SELECT dc.id
                                                                                                  FROM dimension_category dc
                                                                                                  WHERE dc.description = 'Soziale Herkunft')
              JOIN profile_entity p ON de.id = p.social_background_id);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT p.id,
            bdso.id,
            (SELECT b.id FROM basic_dimension b WHERE b.dimension_category_id = bdso.dimension_category_id)
     FROM basic_dimension_selectable_option bdso
              JOIN social_background_discrimination_entity de ON bdso.value = de.descriptor AND bdso.dimension_category_id = (SELECT dc.id
                                                                                                  FROM dimension_category dc
                                                                                                  WHERE dc.description = 'Diskriminierung aufgrund sozialer Herkunft')
              JOIN profile_entity p ON de.id = p.social_background_discrimination_id);
--------------------------------------

-- changeset dfuerst:v17-2
--------------------------------------
INSERT INTO profile_entity_selected_weighted_values(profile_entity_id, selected_weighted_values_id, weighted_dimension)
    (SELECT p.id,
        wdso.id,
        (SELECT b.id FROM weighted_dimension b WHERE b.dimension_category_id = wdso.dimension_category_id)
    FROM weighted_dimension_selectable_option wdso
        JOIN work_experience_entity de ON wdso.value = de.descriptor AND wdso.dimension_category_id = (SELECT dc.id
        FROM dimension_category dc
        WHERE dc.description = 'Berufserfahrung')
        JOIN profile_entity p ON de.id = p.work_experience_id);
--------------------------------------

-- changeset dfuerst:v17-3
INSERT INTO profile_entity_selected_multiselect_value(profile_id, multiselect_dimension_id)
    (SELECT id,
            (SELECT b.id
             FROM multiselect_dimension b
                      JOIN dimension_category dc on b.dimension_category_id = dc.id
             WHERE dc.description = 'Hobby')
     FROM profile_entity);


INSERT INTO profile_entity_selected_multiselect_value_selected_options(profile_entity_selected_multiselect_value_id, selected_options_id)
    (SELECT a.id, b.hobby_id
     FROM profile_entity p
              JOIN profile_entity_selected_multiselect_value a on p.id = a.profile_id
              JOIN profile_hobby b on p.id = b.profile_id);

-- changeset dfuerst v17-4
ALTER TABLE profile_entity
    DROP COLUMN diet_id;
ALTER TABLE profile_entity
    DROP COLUMN education_id;
ALTER TABLE profile_entity
    DROP COLUMN gender_id;
ALTER TABLE profile_entity
    DROP COLUMN mother_tongue_id;
ALTER TABLE profile_entity
    DROP COLUMN origin_country_id;
ALTER TABLE profile_entity
    DROP COLUMN project_id;
ALTER TABLE profile_entity
    DROP COLUMN religion_id;
ALTER TABLE profile_entity
    DROP COLUMN work_experience_id;
ALTER TABLE profile_entity
    DROP COLUMN sexual_orientation_id;
ALTER TABLE profile_entity
    DROP COLUMN social_background_id;
ALTER TABLE profile_entity
    DROP COLUMN social_background_discrimination_id;

UPDATE profile_entity
SET was_changed_by_admin = false
WHERE was_changed_by_admin IS NULL;
ALTER TABLE profile_entity
    ALTER COLUMN was_changed_by_admin SET NOT NULL;

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
