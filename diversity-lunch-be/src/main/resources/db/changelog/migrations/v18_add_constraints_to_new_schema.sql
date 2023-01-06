--liquibase formatted sql

-- changeset dfuerst:v18-1
ALTER TABLE  dimension_category ALTER COLUMN description SET NOT NULL;
ALTER TABLE  dimension_category ALTER COLUMN profile_question SET NOT NULL;

ALTER TABLE  basic_dimension_selectable_option ALTER COLUMN value SET NOT NULL;
ALTER TABLE  basic_dimension_selectable_option ALTER COLUMN ignore_in_scoring SET NOT NULL;
ALTER TABLE  basic_dimension_selectable_option
    ADD CONSTRAINT fk_category_id FOREIGN KEY(dimension_category_id)
    REFERENCES dimension_category(id) ON DELETE CASCADE;

ALTER TABLE  basic_dimension
    ADD CONSTRAINT fk_category_id FOREIGN KEY(dimension_category_id)
        REFERENCES dimension_category(id) ON DELETE CASCADE;
ALTER TABLE  basic_dimension
    ADD CONSTRAINT fk_default_value_id FOREIGN KEY(default_value_id)
        REFERENCES basic_dimension_selectable_option(id);

ALTER TABLE  multiselect_dimension
    ADD CONSTRAINT fk_category_id FOREIGN KEY(dimension_category_id)
        REFERENCES dimension_category(id) ON DELETE CASCADE;

ALTER TABLE  multiselect_dimension_selectable_option ALTER COLUMN value SET NOT NULL;
ALTER TABLE  multiselect_dimension_selectable_option
    ADD CONSTRAINT fk_category_id FOREIGN KEY(dimension_category_id)
        REFERENCES dimension_category(id) ON DELETE CASCADE;

ALTER TABLE  profile_entity_selected_basic_values
    ADD CONSTRAINT fk_selected_basic_values FOREIGN KEY(selected_basic_values_id)
        REFERENCES basic_dimension_selectable_option(id);
ALTER TABLE  profile_entity_selected_basic_values
    ADD CONSTRAINT fk_basic_dimensions FOREIGN KEY(basic_dimension)
        REFERENCES basic_dimension(id) ON DELETE CASCADE;
Alter TABLE profile_entity_selected_basic_values
    ADD CONSTRAINT fk_profile_entity FOREIGN KEY(profile_entity_id)
        REFERENCES profile_entity(id)  ON DELETE CASCADE;

ALTER TABLE  profile_entity_selected_multiselect_value
    ADD CONSTRAINT fk_profile_id FOREIGN KEY(profile_id)
        REFERENCES profile_entity(id) ON DELETE CASCADE;
ALTER TABLE  profile_entity_selected_multiselect_value
    ADD CONSTRAINT fk_multi_dimension FOREIGN KEY(multiselect_dimension)
        REFERENCES multiselect_dimension(id) ON DELETE CASCADE;


ALTER TABLE  profile_entity_selected_multiselect_value_selected_options
    ADD CONSTRAINT fk_multiselect_value_id FOREIGN KEY(profile_entity_selected_multiselect_value_id)
        REFERENCES profile_entity_selected_multiselect_value(id) ON DELETE CASCADE;
ALTER TABLE  profile_entity_selected_multiselect_value_selected_options
    ADD CONSTRAINT fk_selected_options_id FOREIGN KEY(selected_options_id)
        REFERENCES multiselect_dimension_selectable_option(id) ON DELETE CASCADE;

ALTER TABLE  weighted_dimension_selectable_option ALTER COLUMN ignore_in_scoring SET NOT NULL;
ALTER TABLE  weighted_dimension_selectable_option ALTER COLUMN value SET NOT NULL;
ALTER TABLE  weighted_dimension_selectable_option
    ADD CONSTRAINT fk_category_id FOREIGN KEY(dimension_category_id)
        REFERENCES dimension_category(id) ON DELETE CASCADE;

ALTER TABLE  profile_entity_selected_weighted_values
    ADD CONSTRAINT fk_profile_id FOREIGN KEY(profile_entity_id)
        REFERENCES profile_entity(id) ON DELETE CASCADE;
ALTER TABLE  profile_entity_selected_weighted_values
    ADD CONSTRAINT fk_selected_weighted_values FOREIGN KEY(selected_weighted_values_id)
        REFERENCES weighted_dimension_selectable_option(id);
ALTER TABLE  profile_entity_selected_weighted_values
    ADD CONSTRAINT fk_weighted_dimension FOREIGN KEY(weighted_dimension)
        REFERENCES weighted_dimension(id) ON DELETE CASCADE;

ALTER TABLE  weighted_dimension
    ADD CONSTRAINT fk_category_id FOREIGN KEY(dimension_category_id)
        REFERENCES dimension_category(id) ON DELETE CASCADE;
ALTER TABLE  weighted_dimension
    ADD CONSTRAINT fk_default_value_id FOREIGN KEY(default_value_id)
        REFERENCES weighted_dimension_selectable_option(id);