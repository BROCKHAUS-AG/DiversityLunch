--liquibase formatted sql


-- changeset dfuerst:v13-1
ALTER  TABLE dimension_category_entity rename column
descriptor to description;
ALTER TABLE dimension_category_entity add column profile_question
varchar(128) default 'question';
ALTER TABLE dimension_category_entity rename to dimension_category;

CREATE TABLE basic_dimension
(
    id               BIGSERIAL PRIMARY KEY,
    dimension_category_id      BIGINT ,
    default_value_id BIGINT 
);

create table basic_dimension_selectable_option
(
    id                    BIGSERIAL   ,
    ignore_in_scoring     boolean     ,
    value                 varchar(64) ,
    dimension_category_id bigint
);

create table multiselect_dimension
(
    id          BIGSERIAL ,
    dimension_category_id bigint    
);

create table multiselect_dimension_selectable_option
(
    id                    BIGSERIAL   ,
    value                 varchar(64) ,
    dimension_category_id bigint
);
create table profile_entity_selected_basic_values
(
    profile_entity_id        bigint ,
    selected_basic_values_id bigint ,
    basic_dimension         bigint ,
    primary key (profile_entity_id, basic_dimension)
);

create table profile_entity_selected_multiselect_value
(
    id                 BIGSERIAL ,
    profile_id         bigint    ,
    multiselect_dimension bigint    
);
create table profile_entity_selected_multiselect_value_selected_options
(
    profile_entity_selected_multiselect_value_id bigint ,
    selected_options_id                          bigint 
);
create table profile_entity_selected_weighted_values
(
    profile_entity_id           bigint ,
    selected_weighted_values_id bigint ,
    weighted_dimension          bigint ,
    primary key (profile_entity_id, weighted_dimension)
);


create table weighted_dimension
(
    id                    BIGSERIAL  primary key,
    dimension_category_id bigint
);

create table weighted_dimension_selectable_option
(
    id                    bigserial       ,
    ignore_in_scoring     boolean      ,
    value                 varchar(64) ,
    weight                integer       check (weight >= 0),
    dimension_category_id bigint
);

