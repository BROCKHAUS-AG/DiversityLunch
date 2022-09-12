export const getMeetingEndTime = (fromDateTime: Date): string => {
    const meetingStartTime = new Date(fromDateTime);
    const meetingEndTime = new Date();
    meetingEndTime.setTime(meetingStartTime.getTime() + 30 * 60 * 1000);
    return substringLocalTime(meetingEndTime);
};

export const substringLocalTimeTest = (date: Date) => date.toLocaleTimeString('de-DE').substring(0, 5);

export const substringLocalTime = (date: Date): string => date.toLocaleTimeString('de-DE').substring(0, 5);

export const dateToString = (date: Date): string => {
    const options: Intl.DateTimeFormatOptions = {
        weekday: 'long', year: 'numeric', month: 'long', day: 'numeric',
    };
    return date.toLocaleDateString('de-DE', options);
};

export const dateFromUTCDateString = (s: string) => {
    const date = new Date(s);
    return new Date(
        Date.UTC(
            date.getUTCFullYear(),
            date.getUTCMonth(),
            date.getUTCDate(),
            date.getHours(),
            date.getUTCMinutes(),
            date.getUTCSeconds(),
            date.getUTCMilliseconds(),
        ),
    );
};
