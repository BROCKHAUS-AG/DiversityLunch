export type CreateMeetingDto = {
    fromDateTime: string;
}

export type MeetingDto = {
    id: string;
    partnerName: string;
} & CreateMeetingDto;
