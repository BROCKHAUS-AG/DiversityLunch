--liquibase formatted sql

-- changeset dfuerst:v4-1
INSERT INTO gender_entity(descriptor) VALUES
                         ('Trans*'),
                         ('Inter*');
