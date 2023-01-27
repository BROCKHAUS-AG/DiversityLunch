import iconProfile from '../../../resources/icons/icon-profil.svg';
import iconCalendar from '../../../resources/icons/icon-termin-auswählen.svg';
import iconMeeting from '../../../resources/icons/icon-anstehende-termine.svg';
import iconInfo from '../../../resources/icons/icon-info.svg';
import home from '../../../resources/icons/home.svg';
import iconVoucher from '../../../resources/icons/icon-voucher-percent.svg';

export const NavbarData = [
    {
        id: 1,
        title: 'Home',
        path: '/dashboard',
        cName: 'nav-text',
        icon: home,
    },
    {
        id: 2,
        title: 'Dein Profil',
        path: '/profile',
        cName: 'nav-text',
        icon: iconProfile,
    },
    {
        id: 3,
        title: 'Termin wählen',
        path: '/add+meetings/choose+date',
        cName: 'nav-text',
        icon: iconCalendar,
    },
    {
        id: 4,
        title: 'Anstehende Meetings',
        path: '/upcoming+meetings',
        cName: 'nav-text',
        icon: iconMeeting,
    },
    {
        id: 5,
        title: 'Gutscheine',
        path: '/myVouchers',
        cName: 'nav-text',
        icon: iconVoucher,
    },
    {
        id: 6,
        title: 'Informationen',
        path: '/information',
        cName: 'nav-text',
        icon: iconInfo,
    },


];
