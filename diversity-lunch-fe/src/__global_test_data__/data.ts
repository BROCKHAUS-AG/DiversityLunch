import { Role } from '../model/Role';
import { Account } from '../types/Account';
import { Profile } from '../model/Profile';

export const categoryData = [{ id: 2, descriptor: 'Kreatives' }, { id: 1337, descriptor: 'Sport' }];
export const countryData = [{ id: 9, descriptor: 'Bahamas' }, { id: 1337, descriptor: 'Deutschland' }];
export const dietData = [{ id: 9, descriptor: 'Fleischesser' }, { id: 1337, descriptor: 'Frutarier' }];
export const educationData = [{ id: 9, descriptor: 'Abitur' }, { id: 1337, descriptor: 'Doktor' }];
export const genderData = [{ id: 9, descriptor: 'männlich' }, { id: 1337, descriptor: 'weiblich' }];
export const languageData = [{ id: 9, descriptor: 'Deutsch' }, { id: 1337, descriptor: 'Französisch' }];
export const projectData = [{ id: 9, descriptor: 'intern' }, { id: 1337, descriptor: 'extern' }];
export const religionData = [{ id: 9, descriptor: 'katholisch' }, { id: 1337, descriptor: 'evangelisch' }];
export const workExperienceData = [{ id: 9, descriptor: '0-4 Jahre' }, { id: 1337, descriptor: '4-10 Jahre' }];
export const hobbyData = [{ id: 6, descriptor: 'DIY', category: { id: 2, descriptor: 'Kreatives' } },
    { id: 7, descriptor: 'Fußball', category: { id: 1, descriptor: 'Sport' } }];
export const sexualOrientationData = [{ id: 9, descriptor: 'Asexuell' }, { id: 1337, descriptor: 'Pansexuell' }];
export const socialBackgroundData = [
    { id: 9, descriptor: 'Nichtakademisches Elternhaus' },
    { id: 547, descriptor: 'Akademikerfamilie' },
    { id: 234, descriptor: 'keine Angabe' },
];
export const discriminationData = [{ id: 9, descriptor: 'keine Angabe' }, { id: 1337, descriptor: 'ja' }, { id: 1337, descriptor: 'nein' }];

export const profileData: Profile[] = [{
    id: 4,
    name: 'Horstus',
    email: 'jtonn@brockhaus-ag.de',
    birthYear: 1920,
    originCountry: countryData[0],
    diet: dietData[0],
    education: educationData[0],
    gender: genderData[0],
    motherTongue: languageData[0],
    project: projectData[0],
    religion: religionData[0],
    workExperience: workExperienceData[0],
    hobby: hobbyData[0],
    sexualOrientation: sexualOrientationData[0],
    socialBackground: socialBackgroundData[0],
    discrimination: discriminationData[0],
}];

export const accountStandardData = {
    id: 2,
    email: 'hans@brockhaus-ag.de',
    role: Role.STANDARD,
};

export const accountAdminData = {
    id: 2,
    email: 'hans@brockhaus-ag.de',
    role: Role.ADMIN,
};
export const accountAzureAdminData = {
    id: 2,
    email: 'hans@brockhaus-ag.de',
    role: Role.AZURE_ADMIN,
};
export const accountList: Account[] = [
    {
        id: 1, profileId: 1, role: Role.STANDARD,
    },
    {
        id: 2, profileId: 2, role: Role.ADMIN,
    },
    {
        id: 3, profileId: 3, role: Role.AZURE_ADMIN,
    },
];
export const profileList: Profile[] = [
    {
        id: 1,
        name: 'Bernd',
        email: 'bernd@brockhaus-ag.de',
        birthYear: 1920,
        originCountry: countryData[0],
        diet: dietData[0],
        education: educationData[0],
        gender: genderData[0],
        motherTongue: languageData[0],
        project: projectData[0],
        religion: religionData[0],
        workExperience: workExperienceData[0],
        hobby: hobbyData[0],
        sexualOrientation: sexualOrientationData[0],
        socialBackground: socialBackgroundData[0],
        discrimination: discriminationData[0],
    },
    {
        id: 2,
        name: 'Gunni',
        email: 'gunni@brockhaus-ag.de',
        birthYear: 1920,
        originCountry: countryData[0],
        diet: dietData[0],
        education: educationData[0],
        gender: genderData[0],
        motherTongue: languageData[0],
        project: projectData[0],
        religion: religionData[0],
        workExperience: workExperienceData[0],
        hobby: hobbyData[0],
        sexualOrientation: sexualOrientationData[0],
        socialBackground: socialBackgroundData[0],
        discrimination: discriminationData[0],
    },
    {
        id: 3,
        name: 'Chlodhilde',
        email: 'chlodhilde@brockhaus-ag.de',
        birthYear: 1920,
        originCountry: countryData[0],
        diet: dietData[0],
        education: educationData[0],
        gender: genderData[0],
        motherTongue: languageData[0],
        project: projectData[0],
        religion: religionData[0],
        workExperience: workExperienceData[0],
        hobby: hobbyData[0],
        sexualOrientation: sexualOrientationData[0],
        socialBackground: socialBackgroundData[0],
        discrimination: discriminationData[0],
    },
];
