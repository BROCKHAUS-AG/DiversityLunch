import React from 'react';
import { Tile } from './Tile';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import '../../styles/component-styles/dashboard/dashboard.scss';
import iconInfo from '../../resources/icons/icon-info.svg';
import iconProfile from '../../resources/icons/icon-profil.svg';
import iconMeeting from '../../resources/icons/icon-anstehende-termine.svg';
import iconCalendar from '../../resources/icons/icon-termin-auswählen.svg';
import { AdminPanelIconContainer } from '../General/HeaderTemplate/AdminPanelIconContainer';

export const Dashboard = () => (
  <div className="Dashboard">
      <AdminPanelIconContainer />
      <DiversityIconContainer title="DIVERSITY LUNCH" poweredBy />

      <div className="Dashboard-tiles-container">
          <Tile title="DEIN PROFIL" icon={iconProfile} link="profile" />
          <Tile title="TERMIN WÄHLEN" icon={iconCalendar} link="/add+meetings/choose+date" />
          <Tile title="ANSTEHENDE MEETINGS" icon={iconMeeting} link="upcoming+meetings" />
          <Tile title="INFORMATIONEN" icon={iconInfo} link="information" />
        </div>

    </div>

);
