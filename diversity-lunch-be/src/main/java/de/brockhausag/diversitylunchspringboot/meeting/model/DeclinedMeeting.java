package de.brockhausag.diversitylunchspringboot.meeting.model;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

import java.util.List;

public record DeclinedMeeting(MeetingEntity meetingEntity, List<ProfileEntity> decliner) {
}
