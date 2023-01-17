--liquibase formatted sql

-- changeset dfuerst:v14-1
INSERT INTO basic_dimension_selectable_option(value) SELECT descriptor FROM country_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false,
            dimension_category_id=(Select id FROM dimension_category WHERE description='Ethnische Herkunft');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true WHERE value='keine Angabe';

INSERT INTO basic_dimension(dimension_category_id) Select id FROM dimension_category WHERE description='Ethnische Herkunft';
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
    WHERE value='keine Angabe' AND dimension_category_id=(Select id FROM dimension_category WHERE description='Ethnische Herkunft'));

--------------------------------------------------------

INSERT INTO basic_dimension_selectable_option(value, dimension_category_id) SELECT descriptor, (Select id FROM dimension_category WHERE description='Religion') FROM religion_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Religion');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true WHERE value='keiner Konfession angehörig' AND dimension_category_id=(Select id FROM dimension_category WHERE description='Religion');

INSERT INTO basic_dimension(dimension_category_id) (Select id FROM dimension_category WHERE description='Religion');
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
                WHERE value='keiner Konfession angehörig'
                 AND dimension_category_id=(Select id FROM dimension_category WHERE description='Religion'))
        WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Religion');

-------------------------------------------------------------

INSERT INTO basic_dimension_selectable_option(value, dimension_category_id) SELECT descriptor, (Select id FROM dimension_category WHERE description='Soziale Herkunft') FROM social_background_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Soziale Herkunft');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true WHERE value='keine Angabe' AND dimension_category_id=(Select id FROM dimension_category WHERE description='Soziale Herkunft');

INSERT INTO basic_dimension(dimension_category_id) (Select id FROM dimension_category WHERE description='Soziale Herkunft');
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
                                              WHERE value='keine Angabe'
                                                AND dimension_category_id=(Select id FROM dimension_category WHERE description='Soziale Herkunft'))
WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Soziale Herkunft');

-------------------------------------------------------------

INSERT INTO basic_dimension_selectable_option(value, dimension_category_id) SELECT descriptor, (Select id FROM dimension_category WHERE description='Diskriminierung aufgrund sozialer Herkunft') FROM social_background_discrimination_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Diskriminierung aufgrund sozialer Herkunft');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true WHERE value='keine Angabe' AND dimension_category_id=(Select id FROM dimension_category WHERE description='Diskriminierung aufgrund sozialer Herkunft');

INSERT INTO basic_dimension(dimension_category_id) (Select id FROM dimension_category WHERE description='Diskriminierung aufgrund sozialer Herkunft');
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
                                              WHERE value='keine Angabe'
                                                AND dimension_category_id=(Select id FROM dimension_category WHERE description='Diskriminierung aufgrund sozialer Herkunft'))
WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Diskriminierung aufgrund sozialer Herkunft');

----------------------------------------------------------

INSERT INTO basic_dimension_selectable_option(value, dimension_category_id) SELECT descriptor, (Select id FROM dimension_category WHERE description='Sexuelle Orientierung') FROM sexual_orientation_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Sexuelle Orientierung');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true WHERE value='keine Angabe' AND dimension_category_id=(Select id FROM dimension_category WHERE description='Sexuelle Orientierung');

INSERT INTO basic_dimension(dimension_category_id) (Select id FROM dimension_category WHERE description='Sexuelle Orientierung');
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
                                              WHERE value='keine Angabe'
                                                AND dimension_category_id=(Select id FROM dimension_category WHERE description='Sexuelle Orientierung'))
WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Sexuelle Orientierung');

----------------------------------------------------------


INSERT INTO basic_dimension_selectable_option(value, dimension_category_id) SELECT descriptor, (Select id FROM dimension_category WHERE description='Projekt') FROM project_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Projekt');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true WHERE value='keine Angabe' AND dimension_category_id=(Select id FROM dimension_category WHERE description='Projekt');

INSERT INTO basic_dimension(dimension_category_id) (Select id FROM dimension_category WHERE description='Projekt');
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
                                              WHERE value='keine Angabe'
                                                AND dimension_category_id=(Select id FROM dimension_category WHERE description='Projekt'))
WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Projekt');

----------------------------------------------------------

INSERT INTO basic_dimension_selectable_option(value, dimension_category_id) SELECT descriptor, (Select id FROM dimension_category WHERE description='Geschlechtliche Identität') FROM gender_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Geschlechtliche Identität');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true WHERE value='keine Angabe' AND dimension_category_id=(Select id FROM dimension_category WHERE description='Geschlechtliche Identität');

INSERT INTO basic_dimension(dimension_category_id) (Select id FROM dimension_category WHERE description='Geschlechtliche Identität');
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
                                              WHERE value='keine Angabe'
                                                AND dimension_category_id=(Select id FROM dimension_category WHERE description='Geschlechtliche Identität'))
WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Geschlechtliche Identität');

----------------------------------------------------------


INSERT INTO basic_dimension_selectable_option(value, dimension_category_id)
    SELECT descriptor, (Select id FROM dimension_category
                              WHERE description='Bildungsweg') FROM education_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false
    WHERE dimension_category_id=(Select id FROM dimension_category
    WHERE description='Bildungsweg');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true
    WHERE value='keine Angabe' AND
    dimension_category_id=(Select id FROM dimension_category
    WHERE description='Bildungsweg');

INSERT INTO basic_dimension(dimension_category_id)
    (Select id FROM dimension_category WHERE description='Bildungsweg');
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
    WHERE value='keine Angabe'
    AND dimension_category_id=(Select id FROM dimension_category
    WHERE description='Bildungsweg'))
WHERE dimension_category_id=(Select id FROM dimension_category
WHERE description='Bildungsweg');

----------------------------------------------------------

INSERT INTO basic_dimension_selectable_option(value, dimension_category_id)
SELECT descriptor, (Select id FROM dimension_category
                    WHERE description='Ernährung') FROM diet_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false
WHERE dimension_category_id=(Select id FROM dimension_category
                             WHERE description='Ernährung');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true
WHERE value='keine Angabe' AND
        dimension_category_id=(Select id FROM dimension_category
                               WHERE description='Ernährung');

INSERT INTO basic_dimension(dimension_category_id)
    (Select id FROM dimension_category WHERE description='Ernährung');
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
                                              WHERE value='keine Angabe'
                                                AND dimension_category_id=(Select id FROM dimension_category
                                                                           WHERE description='Ernährung'))
WHERE dimension_category_id=(Select id FROM dimension_category
                   WHERE description='Ernährung');

----------------------------------------------------------


INSERT INTO basic_dimension_selectable_option(value, dimension_category_id)
SELECT descriptor, (Select id FROM dimension_category
                    WHERE description='Muttersprache') FROM language_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false
WHERE dimension_category_id=(Select id FROM dimension_category
                             WHERE description='Muttersprache');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true
WHERE value='keine Angabe' AND
        dimension_category_id=(Select id FROM dimension_category
                               WHERE description='Muttersprache');

INSERT INTO basic_dimension(dimension_category_id)
    (Select id FROM dimension_category WHERE description='Muttersprache');
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
                                              WHERE value='keine Angabe'
                                                AND dimension_category_id=(Select id FROM dimension_category
                                                                           WHERE description='Muttersprache'))
WHERE dimension_category_id=(Select id FROM dimension_category
                   WHERE description='Muttersprache');

----------------------------------------------------------