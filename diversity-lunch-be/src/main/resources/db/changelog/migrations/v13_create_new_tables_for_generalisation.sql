--liquibase formatted sql


-- changeset dfuerst:v13-1
CREATE TABLE dimension_category
(
    id               BIGSERIAL PRIMARY KEY,
    description      varchar(64)  NOT NULL,
    profile_question varchar(128) NOT NULL
);
CREATE TABLE basic_dimension
(
    id               BIGSERIAL PRIMARY KEY,
    category_id      BIGINT NOT NULL,
    default_value_id BIGINT NOT NULL
);

create table basic_dimension_selectable_option
(
    id                    BIGSERIAL   not null,
    ignore_in_scoring     boolean     not null,
    value                 varchar(64) not null,
    dimension_category_id bigint      not null
);

create table multiselect_dimension
(
    id          BIGSERIAL not null,
    category_id bigint    not null
);

create table multiselect_dimension_selectable_option
(
    id                    BIGSERIAL   not null,
    value                 varchar(64) not null,
    dimension_category_id bigint      not null
);
create table profile_entity_selected_basic_values
(
    profile_entity_id        bigint not null,
    selected_basic_values_id bigint not null,
    dimension_category       bigint not null,
    primary key (profile_entity_id, dimension_category)
);

create table profile_entity_selected_multiselect_value
(
    id                 BIGSERIAL not null,
    profile_id         bigint    not null,
    dimension_category bigint    not null
);
create table profile_entity_selected_multiselect_value_selected_options
(
    profile_entity_selected_multiselect_value_id bigint not null,
    selected_options_id                          bigint not null
);
create table profile_entity_selected_weighted_values
(
    profile_entity_id           bigint not null,
    selected_weighted_values_id bigint not null,
    dimension_category          bigint not null,
    primary key (profile_entity_id, dimension_category)
);

create table question_entity
(
    id            bigserial primary key,
    question_text varchar(128) not null,
    category_id   bigint       not null
);

create table weighted_dimension
(
    id                    BIGSERIAL not null primary key,
    dimension_category_id bigint
);

create table weighted_dimension_selectable_option
(
    id                    bigserial       not null,
    ignore_in_scoring     boolean      not null,
    value                 varchar(64) not null,
    weight                integer      not null check (weight >= 0),
    dimension_category_id bigint       not null
);

