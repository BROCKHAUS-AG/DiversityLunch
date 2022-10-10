--liquibase formatted sql

--changeset sharwig:1
CREATE SEQUENCE IF NOT EXISTS "hibernate_sequence" AS BIGINT START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

-- changeset sharwig:2
CREATE TABLE IF NOT EXISTS "work_experience_entity" ("id" BIGSERIAL NOT NULL,
                                       "descriptor" VARCHAR(255),
                                       CONSTRAINT "pk_work_experience" PRIMARY KEY ("id")
);

-- changeset sharwig:3
CREATE TABLE IF NOT EXISTS "religion_entity" ("id" BIGSERIAL NOT NULL,
                                "descriptor" VARCHAR(255),
                                CONSTRAINT "pk_religion" PRIMARY KEY ("id")
);

-- changeset sharwig:4
CREATE TABLE IF NOT EXISTS "project_entity" ("id" BIGSERIAL NOT NULL,
                               "descriptor" VARCHAR(255),
                               CONSTRAINT "pk_project" PRIMARY KEY ("id")
);

-- changeset sharwig:5
CREATE TABLE IF NOT EXISTS "language_entity" ("id" BIGSERIAL NOT NULL,
                                "descriptor" VARCHAR(255),
                                CONSTRAINT "pk_language" PRIMARY KEY ("id")
);

-- changeset sharwig:6
CREATE TABLE IF NOT EXISTS "hobby_category_entity" ("id" BIGSERIAL NOT NULL,
                                      "descriptor" VARCHAR(255),
                                      CONSTRAINT "pk_hobby_category" PRIMARY KEY ("id")
);

-- changeset sharwig:7
CREATE TABLE IF NOT EXISTS "hobby_entity" ("id" BIGSERIAL NOT NULL,
                             "descriptor" VARCHAR(255),
                             "category_id" BIGINT,
                             CONSTRAINT "pk_hobby" PRIMARY KEY ("id"),
                             CONSTRAINT fk_hobby_category FOREIGN KEY ("category_id") REFERENCES hobby_category_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- changeset sharwig:8
CREATE TABLE IF NOT EXISTS "gender_entity" ("id" BIGSERIAL NOT NULL,
                              "descriptor" VARCHAR(255),
                              CONSTRAINT "pk_gender" PRIMARY KEY ("id")
);

-- changeset sharwig:9
CREATE TABLE IF NOT EXISTS "education_entity" ("id" BIGSERIAL NOT NULL,
                                 "descriptor" VARCHAR(255),
                                 CONSTRAINT "pk_education" PRIMARY KEY ("id")
);

-- changeset sharwig:10
CREATE TABLE IF NOT EXISTS "diet_entity" ("id" BIGSERIAL NOT NULL,
                            "descriptor" VARCHAR(255),
                            CONSTRAINT "pk_diet" PRIMARY KEY ("id")
);

-- changeset sharwig:11
CREATE TABLE IF NOT EXISTS "country_entity" ("id" BIGSERIAL NOT NULL,
                               "descriptor" VARCHAR(255),
                               CONSTRAINT "pk_country" PRIMARY KEY ("id")
);

-- changeset sharwig:12
CREATE TABLE IF NOT EXISTS "profile_entity" ("id" BIGSERIAL NOT NULL,
                               "birth_year" INT,
                               "email" VARCHAR(255),
                               "name" VARCHAR(255),
                               "diet_id" BIGINT,
                               "education_id" BIGINT,
                               "gender_id" BIGINT,
                               "hobby_id" BIGINT,
                               "mother_tongue_id" BIGINT,
                               "origin_country_id" BIGINT,
                               "project_id" BIGINT,
                               "religion_id" BIGINT,
                               "work_experience_id" BIGINT,
                               CONSTRAINT "pk_profile" PRIMARY KEY ("id"),
                               CONSTRAINT fk_diet FOREIGN KEY ("diet_id") REFERENCES diet_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
                               CONSTRAINT fk_education_id FOREIGN KEY ("education_id") REFERENCES education_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
                               CONSTRAINT fk_gender FOREIGN KEY ("gender_id") REFERENCES gender_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
                               CONSTRAINT fk_hobby FOREIGN KEY ("hobby_id") REFERENCES hobby_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
                               CONSTRAINT fk_mother_tongue FOREIGN KEY ("mother_tongue_id") REFERENCES language_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
                               CONSTRAINT fk_origin_country FOREIGN KEY ("origin_country_id") REFERENCES country_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
                               CONSTRAINT fk_project FOREIGN KEY ("project_id") REFERENCES profile_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
                               CONSTRAINT fk_religion FOREIGN KEY ("religion_id") REFERENCES religion_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
                               CONSTRAINT fk_work_experience FOREIGN KEY ("work_experience_id") REFERENCES work_experience_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

--changeset sharwig:13
CREATE TABLE IF NOT EXISTS "meeting_entity" ("id" BIGSERIAL NOT NULL,
                               "created_at" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                               "from_date_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                               "question" VARCHAR(255) NOT NULL,
                               "score" INTEGER NOT NULL,
                               "partner_id" BIGINT NOT NULL,
                               "proposer_id" BIGINT NOT NULL,
                               "ms_teams_meeting_id" VARCHAR(255) NOT NULL DEFAULT '',
                               CONSTRAINT "pk_meeting" PRIMARY KEY ("id"),
                               CONSTRAINT fk_meeting_partner FOREIGN KEY ("partner_id") REFERENCES profile_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
                               CONSTRAINT fk_meeting_proposer FOREIGN KEY ("proposer_id") REFERENCES profile_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- changeset sharwig:14
CREATE TABLE IF NOT EXISTS "meeting_proposal_entity" ("id" BIGSERIAL NOT NULL,
                                        "created_at" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                                        "matched" BOOLEAN NOT NULL,
                                        "proposed_date_time" TIMESTAMP WITHOUT TIME ZONE,
                                        "proposer_profile_id" BIGINT,
                                        CONSTRAINT "pk_meeting_proposal" PRIMARY KEY ("id"),
                                        CONSTRAINT fk_meeting_proposal_proposer FOREIGN KEY ("proposer_profile_id") REFERENCES profile_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

--changeset sharwig:15
CREATE TABLE IF NOT EXISTS "account_entity" ("id" BIGSERIAL NOT NULL,
                               "unique_name" VARCHAR(255),
                               "profile_id" BIGINT,
                               "role" INTEGER NOT NULL DEFAULT 0,
                               CONSTRAINT "pk_account" PRIMARY KEY ("id"),
                               CONSTRAINT fk_profile FOREIGN KEY ("profile_id") REFERENCES profile_entity("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);