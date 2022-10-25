--liquibase formatted sql

--changeset dfuerst:1
CREATE  TABLE IF NOT EXISTS voucher_entity(
    id uuid PRIMARY KEY DEFAULT gen_random_uuid (),
    voucher VARCHAR(64) NOT NULL,
    userid int,
    meetingid int,
    CONSTRAINT fk_user_profile FOREIGN KEY (userid) REFERENCES profile_entity (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_meeting FOREIGN KEY (meetingid) REFERENCES meeting_entity (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);
