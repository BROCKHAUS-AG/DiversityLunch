import { CreateMeetingDto, MeetingDto } from '../types/dtos/MeetingDto';
import { CreateMeeting, Meeting } from '../types/Meeting';
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
