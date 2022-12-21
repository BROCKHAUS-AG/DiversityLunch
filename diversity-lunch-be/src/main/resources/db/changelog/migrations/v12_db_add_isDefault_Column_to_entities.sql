--liquibase formatted sql


-- changeset dfuerst:v12-1
ALTER TABLE country_entity ADD COLUMN isDefault boolean not null default false;
ALTER TABLE diet_entity ADD COLUMN isDefault boolean not null default false;
ALTER TABLE education_entity ADD COLUMN isDefault boolean not null default false;
ALTER TABLE gender_entity ADD COLUMN isDefault boolean not null default false;
ALTER TABLE language_entity ADD COLUMN isDefault boolean not null default false;
ALTER TABLE religion_entity ADD COLUMN isDefault boolean not null default false;
ALTER TABLE sexual_orientation_entity ADD COLUMN isDefault boolean not null default false;
ALTER TABLE social_background_discrimination_entity ADD COLUMN isDefault boolean not null default false;
ALTER TABLE work_experience_entity ADD COLUMN isDefault boolean not null default false;
ALTER TABLE project_entity ADD COLUMN isDefault boolean not null default false;
ALTER TABLE social_background_entity ADD COLUMN isDefault boolean not null default false;

-- changeset dfuerst:v12-2
Update country_entity                             SET isDefault=true   WHERE lower(descriptor)=lower('keine angabe');
Update diet_entity                                SET isDefault=true   WHERE lower(descriptor)=lower('keine angabe');
Update education_entity                           SET isDefault=true   WHERE lower(descriptor)=lower('keine angabe');
Update gender_entity                              SET isDefault=true   WHERE lower(descriptor)=lower('keine angabe');
Update language_entity                            SET isDefault=true   WHERE lower(descriptor)=lower('keine angabe');
Update religion_entity                            SET isDefault=true   WHERE lower(descriptor)=lower('keine angabe');
Update sexual_orientation_entity                  SET isDefault=true   WHERE lower(descriptor)=lower('keine angabe');
Update social_background_discrimination_entity    SET isDefault=true   WHERE lower(descriptor)=lower('keine angabe');
Update work_experience_entity                     SET isDefault=true   WHERE lower(descriptor)=lower('keine angabe');
Update social_background_entity  SET isDefault=true   WHERE lower(descriptor)=lower('keine angabe');
Update project_entity  SET isDefault=true WHERE lower(descriptor)=lower('keine angabe');
