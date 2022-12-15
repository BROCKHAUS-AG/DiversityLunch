import React, { useEffect, useState } from 'react';
import { MeetingTimespan } from '../../../model/MeetingTimespan';
import './meeting-time-button.scss';

type MeetingTimeProps = {
  timeSpan: MeetingTimespan,
  onTimeChange: (timespan: MeetingTimespan) => void,
  setCurrentTimeLabel: (timeLabel: string) => void,
  isActive: boolean,
}

export const MeetingTimeButton: React.FC<MeetingTimeProps> = (props: MeetingTimeProps) => {
    const [timeLabel, setTimeLabel] = useState<string>('');

    const {
        timeSpan, onTimeChange, setCurrentTimeLabel, isActive,
    } = props;

    useEffect(() => {
        setTimeLabel(prepareTimeLabel(timeSpan));
    }, [timeSpan]);

    const prepareTimeLabel = (timespan: MeetingTimespan): string => {
        const {
            fromHour, fromMinute, toHour, toMinute,
        } = timespan;

        return `${fromHour}:${
            (fromMinute === 0)
                ? `${fromMinute}0`
                : fromMinute
        } - ${toHour}:${
            (toMinute === 0)
                ? `${toMinute}0`
                : toMinute}`;
    };

    return (
        <button
            type="button"
            className={`MeetingTimeButton${isActive ? '-is-active' : ''}`}
            onClick={() => {
                onTimeChange(timeSpan);
                setCurrentTimeLabel(timeLabel);
            }}
        >
            {timeLabel}
        </button>
    );
};
