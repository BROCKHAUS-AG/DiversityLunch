--liquibase formatted sql

-- changeset dfuerst:v17-1
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT id, diet_id ,
            (SELECT b.id FROM basic_dimension b
                JOIN dimension_category dc on b.dimension_category_id = dc.id
                WHERE dc.description='Ernährung')
     FROM profile_entity);
----------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT id, education_id , (SELECT b.id FROM basic_dimension b
                                                     JOIN dimension_category dc on b.dimension_category_id = dc.id
                                WHERE dc.description='Bildungsweg')
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT id, gender_id , (SELECT b.id FROM basic_dimension b
                                                  JOIN dimension_category dc on b.dimension_category_id = dc.id
                             WHERE dc.description='Geschlechtliche Identität')
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT id, mother_tongue_id , (SELECT b.id FROM basic_dimension b
                                                         JOIN dimension_category dc on b.dimension_category_id = dc.id
                                    WHERE dc.description='Muttersprache')
     FROM profile_entity);--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT id, origin_country_id , (SELECT b.id FROM basic_dimension b
                                                          JOIN dimension_category dc on b.dimension_category_id = dc.id
                                     WHERE dc.description='Ethnische Herkunft')
     FROM profile_entity);--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT id, project_id , (SELECT b.id FROM basic_dimension b
                                                   JOIN dimension_category dc on b.dimension_category_id = dc.id
                              WHERE dc.description='Projekt')
     FROM profile_entity);--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT id, religion_id , (SELECT b.id FROM basic_dimension b
                                                    JOIN dimension_category dc on b.dimension_category_id = dc.id
                               WHERE dc.description='Religion')
     FROM profile_entity);--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT id, sexual_orientation_id , (SELECT b.id FROM basic_dimension b
                                                              JOIN dimension_category dc on b.dimension_category_id = dc.id
                                         WHERE dc.description='Sexuelle Orientierung')
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT id, social_background_id ,(SELECT b.id FROM basic_dimension b
                                                            JOIN dimension_category dc on b.dimension_category_id = dc.id
                                       WHERE dc.description='Soziale Herkunft')
     FROM profile_entity);
--------------------------------------
INSERT INTO profile_entity_selected_basic_values(profile_entity_id, selected_basic_values_id, basic_dimension)
    (SELECT id, social_background_discrimination_id ,(SELECT b.id FROM basic_dimension b
                                                                           JOIN dimension_category dc on b.dimension_category_id = dc.id
                                                      WHERE dc.description='Diskriminierung aufgrund sozialer Herkunft')
     FROM profile_entity);--------------------------------------

-- changeset dfuerst:v17-2
--------------------------------------
INSERT INTO profile_entity_selected_weighted_values(profile_entity_id, selected_weighted_values_id, weighted_dimension)
    (SELECT id, work_experience_id , (SELECT b.id FROM weighted_dimension b
                                                           JOIN dimension_category dc on b.dimension_category_id = dc.id
                                      WHERE dc.description='Berufserfahrung')
     FROM profile_entity);
--------------------------------------

-- changeset dfuerst:v17-3
INSERT INTO profile_entity_selected_multiselect_value(profile_id, multiselect_dimension_id)
 (SELECT id, (SELECT b.id FROM multiselect_dimension b
                                  JOIN dimension_category dc on b.dimension_category_id = dc.id
             WHERE dc.description='Hobby')
FROM profile_entity);


INSERT INTO profile_entity_selected_multiselect_value_selected_options(profile_entity_selected_multiselect_value_id, selected_options_id)
(SELECT a.id, b.hobby_id FROM profile_entity p
JOIN profile_entity_selected_multiselect_value a on p.id = a.profile_id
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
