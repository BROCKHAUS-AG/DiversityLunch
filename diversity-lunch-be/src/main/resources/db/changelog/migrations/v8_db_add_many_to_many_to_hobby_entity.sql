--liquibase formatted sql


-- changeset jklein:v8-1
CREATE TABLE profile_hobby (
                               hobby_id   integer not null
                                   constraint fk_hobby_id
                                       references hobby_entity (id),
                               profile_id integer not null
                                   constraint fk_profile_id
                                       references profile_entity (id)
);

-- changeset jklein:v8-2
INSERT INTO profile_hobby (hobby_id, profile_id) SELECT hobby_id, id FROM profile_entity;
