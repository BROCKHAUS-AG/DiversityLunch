-- liquibase formatted sql

-- changeset INoTime:v2-1
ALTER TABLE "account_entity" ADD COLUMN "role" INTEGER NOT NULL DEFAULT 0