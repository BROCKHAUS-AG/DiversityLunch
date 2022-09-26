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

export const profileData = [{
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
}];
