--liquibase formatted sql

-- changeset dfuerst:v14-1
INSERT INTO basic_dimension_selectable_option(value) SELECT descriptor FROM country_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false,
            dimension_category_id=(Select id FROM dimension_category WHERE description='Ethnische Herkunft');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true WHERE value='keine Angabe';

INSERT INTO basic_dimension(category_id) Select id FROM dimension_category WHERE description='Ethnische Herkunft';
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
    WHERE value='keine Angabe' AND dimension_category_id=(Select id FROM dimension_category WHERE description='Ethnische Herkunft'));

--------------------------------------------------------

INSERT INTO basic_dimension_selectable_option(value, dimension_category_id) SELECT descriptor, (Select id FROM dimension_category WHERE description='Religion') FROM religion_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Religion');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true WHERE value='keiner Konfession angehörig' AND dimension_category_id=(Select id FROM dimension_category WHERE description='Religion');

INSERT INTO basic_dimension(category_id) (Select id FROM dimension_category WHERE description='Religion');
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
                WHERE value='keiner Konfession angehörig'
                 AND dimension_category_id=(Select id FROM dimension_category WHERE description='Religion'))
        WHERE category_id=(Select id FROM dimension_category WHERE description='Religion');

-------------------------------------------------------------

INSERT INTO basic_dimension_selectable_option(value, dimension_category_id) SELECT descriptor, (Select id FROM dimension_category WHERE description='Soziale Herkunft') FROM social_background_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false WHERE dimension_category_id=(Select id FROM dimension_category WHERE description='Soziale Herkunft');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true WHERE value='keine Angabe' AND dimension_category_id=(Select id FROM dimension_category WHERE description='Soziale Herkunft');

INSERT INTO basic_dimension(category_id) (Select id FROM dimension_category WHERE description='Soziale Herkunft');
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
                                              WHERE value='keine Angabe'
                                                AND dimension_category_id=(Select id FROM dimension_category WHERE description='Soziale Herkunft'))
WHERE category_id=(Select id FROM dimension_category WHERE description='Soziale Herkunft');

-------------------------------------------------------------