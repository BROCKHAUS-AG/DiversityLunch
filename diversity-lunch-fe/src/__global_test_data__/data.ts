import { Role } from '../model/Role';
import { Account } from '../model/Account';
import { Profile } from '../model/Profile';
import { UserVoucher } from '../model/UserVoucher';

export const categoryData = [{ id: 2, descriptor: 'Kreatives', default: false }, { id: 1337, descriptor: 'Sport', default: false }];
export const countryData = [{ id: 9, descriptor: 'Bahamas', default: false }, { id: 1337, descriptor: 'Deutschland', default: false }];
export const dietData = [{ id: 9, descriptor: 'Fleischesser', default: false }, { id: 1337, descriptor: 'Frutarier', default: false }];
export const educationData = [{ id: 9, descriptor: 'Abitur', default: false }, { id: 1337, descriptor: 'Doktor', default: false }];
export const genderData = [{ id: 9, descriptor: 'männlich', default: false }, { id: 1337, descriptor: 'weiblich', default: false }];
export const languageData = [{ id: 9, descriptor: 'Deutsch', default: false }, { id: 1337, descriptor: 'Französisch', default: false }];
export const projectData = [{ id: 9, descriptor: 'intern', default: false }, { id: 1337, descriptor: 'extern', default: false }];
export const religionData = [{ id: 9, descriptor: 'katholisch', default: false }, { id: 1337, descriptor: 'evangelisch', default: false }];
export const workExperienceData = [{ id: 9, descriptor: '0-4 Jahre', default: false }, { id: 1337, descriptor: '4-10 Jahre', default: false }];
export const hobbyData = [{
    id: 6, descriptor: 'DIY', category: { id: 2, descriptor: 'Kreatives' }, default: false,
},
{
    id: 7, descriptor: 'Fußball', category: { id: 1, descriptor: 'Sport' }, default: false,
}];
export const sexualOrientationData = [{ id: 9, descriptor: 'Asexuell', default: false }, { id: 1337, descriptor: 'Pansexuell', default: false }];
export const socialBackgroundData = [
    { id: 9, descriptor: 'Nichtakademisches Elternhaus', default: false },
    { id: 547, descriptor: 'Akademikerfamilie', default: false },
    { id: 234, descriptor: 'keine Angabe', default: false },
];
export const socialBackgroundDiscriminationData = [{ id: 9, descriptor: 'keine Angabe', default: false },
    { id: 1337, descriptor: 'ja', default: false }, { id: 1337, descriptor: 'nein', default: false }];

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
    hobby: [hobbyData[0]],
    sexualOrientation: sexualOrientationData[0],
    socialBackground: socialBackgroundData[0],
    socialBackgroundDiscrimination: socialBackgroundDiscriminationData[0],
    wasChangedByAdmin: false,
}];

export const accountStandardData = {
    id: 2,
    email: 'hans@brockhaus-ag.de',
    role: Role.STANDARD,
    profileId: 1,
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
        hobby: [hobbyData[0]],
        sexualOrientation: sexualOrientationData[0],
        socialBackground: socialBackgroundData[0],
        socialBackgroundDiscrimination: socialBackgroundDiscriminationData[0],
        wasChangedByAdmin: false,
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
        hobby: [hobbyData[0]],
        sexualOrientation: sexualOrientationData[0],
        socialBackground: socialBackgroundData[0],
        socialBackgroundDiscrimination: socialBackgroundDiscriminationData[0],
        wasChangedByAdmin: false,
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
        hobby: [hobbyData[0]],
        sexualOrientation: sexualOrientationData[0],
        socialBackground: socialBackgroundData[0],
        socialBackgroundDiscrimination: socialBackgroundDiscriminationData[0],
        wasChangedByAdmin: false,
    },
];
export const userVoucherList : UserVoucher[] = [
    {
        voucherCode: 'abcdef',
    },
    {
        voucherCode: 'abccba',
    },
    {
        voucherCode: '1a2b3c',
    },
];
