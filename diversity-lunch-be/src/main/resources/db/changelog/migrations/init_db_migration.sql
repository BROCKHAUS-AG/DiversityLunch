-- liquibase formatted sql

-- changeset ckurzeya:1649320404171-1
CREATE SEQUENCE  IF NOT EXISTS "hibernate_sequence" AS bigint START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

-- changeset ckurzeya:1649320404171-2
CREATE TABLE "account_entity" ("id" BIGINT NOT NULL, "unique_name" VARCHAR(255), "profile_id" BIGINT, CONSTRAINT "account_entity_pkey" PRIMARY KEY ("id"));

-- changeset ckurzeya:1649320404171-3
CREATE TABLE "meeting_entity" ("id" BIGINT NOT NULL, "created_at" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "from_date_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "question" VARCHAR(255) NOT NULL, "score" INTEGER NOT NULL, "partner_id" BIGINT NOT NULL, "proposer_id" BIGINT NOT NULL, CONSTRAINT "meeting_entity_pkey" PRIMARY KEY ("id"));

-- changeset ckurzeya:1649320404171-4
CREATE TABLE "meeting_proposal_entity" ("id" BIGINT NOT NULL, "created_at" TIMESTAMP WITHOUT TIME ZONE NOT NULL, "matched" BOOLEAN NOT NULL, "proposed_date_time" TIMESTAMP WITHOUT TIME ZONE, "proposer_profile_id" BIGINT, CONSTRAINT "meeting_proposal_entity_pkey" PRIMARY KEY ("id"));

-- changeset ckurzeya:1649320404171-5
CREATE TABLE "profile_entity" ("id" BIGINT NOT NULL, "birth_year" INTEGER NOT NULL, "current_project" VARCHAR(255), "diet" VARCHAR(255), "education" VARCHAR(255), "email" VARCHAR(255), "gender" VARCHAR(255), "hobby" VARCHAR(255), "mother_tongue" VARCHAR(255), "name" VARCHAR(255), "origin_country" VARCHAR(255), "religion" VARCHAR(255), "work_experience" VARCHAR(255), CONSTRAINT "profile_entity_pkey" PRIMARY KEY ("id"));

-- changeset ckurzeya:1649320404171-6
ALTER TABLE "account_entity" ADD CONSTRAINT "fkapffx26wusfvg2g4iy741tmyg" FOREIGN KEY ("profile_id") REFERENCES "profile_entity" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset ckurzeya:1649320404171-7
ALTER TABLE "meeting_entity" ADD CONSTRAINT "fkb8oasasyail6mq59hv6kggye8" FOREIGN KEY ("proposer_id") REFERENCES "profile_entity" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset ckurzeya:1649320404171-8
ALTER TABLE "meeting_proposal_entity" ADD CONSTRAINT "fkf9440winnsdphoduuu2q7v4hl" FOREIGN KEY ("proposer_profile_id") REFERENCES "profile_entity" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

-- changeset ckurzeya:1649320404171-9
ALTER TABLE "meeting_entity" ADD CONSTRAINT "fkmbqmocy8htmqa7vjcw9ycgi65" FOREIGN KEY ("partner_id") REFERENCES "profile_entity" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION;

