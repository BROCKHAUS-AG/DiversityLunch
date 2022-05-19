-- liquibase formatted sql

-- changeset ckurzeya:v0-1
CREATE TABLE "account_entity" ("id" BIGINT NOT NULL, "unique_name" VARCHAR(255), "profile_id" BIGINT, CONSTRAINT "account_entity_pkey" PRIMARY KEY ("id"));
-- rollback: DROP TABLE "account_entity";

-- changeset ckurzeya:v0-2
CREATE TABLE "account_entity" ("id" BIGINT NOT NULL, "unique_name" VARCHAR(255), "profile_id" BIGINT, CONSTRAINT "account_entity_pkey" PRIMARY KEY ("id"));
-- rollback: DROP TABLE "account_entity";

-- changeset name:version-changesetNumber

-- comment: some comment
