-- liquibase formatted sql

-- changeset ckurzeya:v1-1
ALTER TABLE "meeting_entity" ADD COLUMN "ms_teams_meeting_id" VARCHAR(255) NOT NULL DEFAULT ''