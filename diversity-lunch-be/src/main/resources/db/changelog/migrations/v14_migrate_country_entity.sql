--liquibase formatted sql

-- changeset dfuerst:v14-1
INSERT INTO basic_dimension_selectable_option(value) SELECT descriptor FROM country_entity;
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=false,
            dimension_category_id=(Select id FROM dimension_category WHERE description='Ethnische Herkunft');
UPDATE basic_dimension_selectable_option SET ignore_in_scoring=true WHERE value='keine Angabe';

INSERT INTO basic_dimension(category_id) Select id FROM dimension_category WHERE description='Ethnische Herkunft';
UPDATE  basic_dimension SET default_value_id=(SELECT id FROM basic_dimension_selectable_option
    WHERE value='keine Angabe' AND dimension_category_id=(Select id FROM dimension_category WHERE description='Ethnische Herkunft'));