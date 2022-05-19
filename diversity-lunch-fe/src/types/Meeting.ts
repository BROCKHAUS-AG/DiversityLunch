export type CreateMeeting = {
    fromDateTime: Date;
}

export type Meeting = {
    id: string;
    partnerName: string;
} & CreateMeeting;
