package de.brockhausag.diversitylunchspringboot.meeting.model;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

public record DeclinedMeeting(MeetingEntity meetingEntity, ProfileEntity decliner) {
}
