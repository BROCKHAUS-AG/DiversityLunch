--liquibase formatted sql

-- changeset dfuerst:v15-1

INSERT INTO weighted_dimension_selectable_option (value, dimension_dimension_category_id)
SELECT descriptor, (Select id FROM dimension_category
    WHERE description='Berufserfahrung') FROM work_experience_entity;
UPDATE weighted_dimension_selectable_option SET ignore_in_scoring=false
WHERE dimension_dimension_category_id=(Select id FROM dimension_category
                             WHERE description='Berufserfahrung');
UPDATE weighted_dimension_selectable_option SET ignore_in_scoring=true
WHERE value='keine Angabe' AND
        dimension_dimension_category_id=(Select id FROM dimension_category
                               WHERE description='Berufserfahrung');

INSERT INTO weighted_dimension(dimension_category_id)
    (Select id FROM dimension_category WHERE description='Berufserfahrung');
UPDATE  weighted_dimension SET default_value_id=(SELECT id FROM weighted_dimension_selectable_option
                                              WHERE value='keine Angabe'
                                                AND dimension_dimension_category_id=(Select id FROM dimension_category
                                                                           WHERE description='Berufserfahrung'))
WHERE dimension_category_id=(Select id FROM dimension_category
                   WHERE description='Berufserfahrung');
