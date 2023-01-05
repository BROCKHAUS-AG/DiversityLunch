DELETE FROM account_entity;
DELETE FROM basic_dimension;
DELETE FROM basic_dimension_selectable_option;
DELETE FROM databasechangelog;
DELETE FROM databasechangeloglock;
delete from dimension_category;
delete from meeting_entity;
delete from meeting_proposal_entity;
delete from multiselect_dimension;
delete from multiselect_dimension_selectable_option;
delete from profile_entity;
delete from profile_entity_selected_basic_values;
delete from profile_entity_selected_multiselect_value;
delete from profile_entity_selected_multiselect_value_selected_options;
delete from profile_entity_selected_weighted_values;
delete from question_entity;
delete from voucher_entity;
delete from weighted_dimension;
delete from weighted_dimension_selectable_option;

-- TODO: check if everything needs to be deleted
