import { CreateMeetingDto, MeetingDto } from '../model/dtos/MeetingDto';
import { CreateMeeting, Meeting } from '../model/Meeting';
import { dateFromUTCDateString } from '../utils/date.utils';

export const mapMeetingToDto = (meeting: Meeting): MeetingDto => ({
    id: meeting.id,
    fromDateTime: meeting.fromDateTime.toISOString(),
    partnerName: meeting.partnerName,
});

export const mapDtoToMeeting = (meeting: MeetingDto): Meeting => ({
    id: meeting.id,
    fromDateTime: dateFromUTCDateString(meeting.fromDateTime),
    partnerName: meeting.partnerName,
});

export const mapCreateMeetingToDto = (createMeeting: CreateMeeting): CreateMeetingDto => ({
    fromDateTime: createMeeting.fromDateTime.toISOString(),
});
