--liquibase formatted sql

--changeset dfuerst:1
CREATE  TABLE IF NOT EXISTS voucher_entity(
    id uuid PRIMARY KEY DEFAULT gen_random_uuid (),
    voucher VARCHAR(64) UNIQUE NOT NULL,
    profile_id BIGINT,
    meeting_id BIGINT,
    CONSTRAINT fk_user_profile FOREIGN KEY (profile_id) REFERENCES profile_entity (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_meeting FOREIGN KEY (meeting_id) REFERENCES meeting_entity (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);
