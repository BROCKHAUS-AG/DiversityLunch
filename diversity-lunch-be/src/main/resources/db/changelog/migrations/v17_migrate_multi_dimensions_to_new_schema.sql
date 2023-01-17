--liquibase formatted sql

-- changeset dfuerst:v16-1
INSERT INTO multiselect_dimension_selectable_option (value,dimension_category_id)
SELECT descriptor,
    (Select id FROM dimension_category
    WHERE description='Hobby') FROM hobby_entity;

INSERT INTO multiselect_dimension(dimension_category_id)
    (Select id FROM dimension_category WHERE description='Hobby');