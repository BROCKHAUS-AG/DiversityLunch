import React, { useState } from 'react';

import { Route, Switch } from 'react-router-dom';

import { SelectMeetingData } from './SelectMeetingData';
import '../../styles/component-styles/addMeeting/addMeeting.scss';
import { DateOverview } from './DateOverview';
import { CreateMeeting } from '../../types/Meeting';

export const AddMeetings = () => {
    const [meeting, setMeeting] = useState<CreateMeeting>({
        fromDateTime: new Date(),
    });

    const onAddMeeting = (createMeeting: CreateMeeting) => {
        setMeeting(createMeeting);
    };

    return (
      <div className="AddMeeting">
          <Switch>
              <Route exact path="/add+meetings/choose+date">
                  <SelectMeetingData onAddMeeting={onAddMeeting} />
                </Route>
              <Route exact path="/add+meetings/meeting+overview">
                  <DateOverview meeting={meeting} />
                </Route>
            </Switch>
        </div>
    );
};
