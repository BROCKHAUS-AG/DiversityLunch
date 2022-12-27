--liquibase formatted sql


-- changeset dfuerst:v13-1
ALTER  TABLE dimension_category_entity rename column
descriptor to description;
ALTER TABLE dimension_category_entity add column profile_question
varchar(1024) default 'question';
ALTER TABLE dimension_category_entity rename to dimension_category;

UPDATE dimension_category SET profile_question='Wann wurdest du geboren?' WHERE description='Alter';
UPDATE dimension_category SET profile_question='Was sind deine Hobbies?' WHERE description='Hobby';
UPDATE dimension_category SET profile_question='Was ist deine Muttersprache?' WHERE description='Muttersprache';
UPDATE dimension_category SET profile_question='In welchem Projekt arbeitest du derzeit?' WHERE description='Projekt';
UPDATE dimension_category SET profile_question='Was ist deine geschlechtliche Identität?' WHERE description='Geschlechtliche Identität';
UPDATE dimension_category SET profile_question='Was ist deine ethnische Herkunft?' WHERE description='Ethnische Herkunft';
UPDATE dimension_category SET profile_question='An welche Religion glaubst du?' WHERE description='Religion';
UPDATE dimension_category SET profile_question='Wie viele Jahre Berufserfahrung hast du schon gesammelt?' WHERE description='Berufserfahrung';
UPDATE dimension_category SET profile_question='Welchen Bildungsweg hast du bisher bestritten?' WHERE description='Bildungsweg';
UPDATE dimension_category SET profile_question='Wie ernährst du dich?' WHERE description='Ernährung';
UPDATE dimension_category SET profile_question='Was ist deine sexuelle Orientierung?' WHERE description='Sexuelle Orientierung';
UPDATE dimension_category SET profile_question='Bist du ein Akademikerkind, oder eher die erste Person in der Familie, die studiert oder ihr Abitur gemacht hat?' WHERE description='Soziale Herkunft';
UPDATE dimension_category SET profile_question='Wurdest du jemals aufgrund deiner sozialen Herkunft Vorurteilen ausgesetzt, herabwürdigend behandelt, benachteiligt oder ausgeschlossen?' WHERE description='Diskriminierung aufgrund sozialer Herkunft';


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
    value                 varchar(128) ,
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
    value                 varchar(128) ,
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
    default_value_id BIGINT,
    dimension_category_id bigint
);

create table weighted_dimension_selectable_option
(
    id                    bigserial       ,
    ignore_in_scoring     boolean      ,
    value                 varchar(128) ,
    weight                integer       check (weight >= 0),
    dimension_category_id bigint
);

