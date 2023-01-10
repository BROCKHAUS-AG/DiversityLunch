import iconProfile from '../../../resources/icons/icon-profil.svg';
import iconCalendar from '../../../resources/icons/icon-termin-auswählen.svg';
import iconMeeting from '../../../resources/icons/icon-anstehende-termine.svg';
import iconInfo from '../../../resources/icons/icon-info.svg';
import home from '../../../resources/icons/home.svg';

export const NavbarData = [
    {
        title: 'Home',
        path: '/dashboard',
        cName: 'nav-text',
        icon: home,
    },
    {
        title: 'Dein Profil',
        path: '/profile',
        cName: 'nav-text',
        icon: iconProfile,
    },
    {
        title: 'Termin wählen',
        path: '/add+meetings/choose+date',
        cName: 'nav-text',
        icon: iconCalendar,
    },
    {
        title: 'Anstehende Meetings',
        path: '/upcoming+meetings',
        cName: 'nav-text',
        icon: iconMeeting,
    },
    {
        title: 'Informationen',
        path: '/information',
        cName: 'nav-text',
        icon: iconInfo,
    },
];
