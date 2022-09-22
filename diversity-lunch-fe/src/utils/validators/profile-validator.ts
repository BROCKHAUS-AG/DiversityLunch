import { Profile } from '../../model/Profile';

export function isValidProfile(profile: Partial<Profile>): boolean {
    return !!(profile.birthYear
        && profile.diet
        && profile.education
        && profile.email
        && profile.gender
        && profile.hobby
        && profile.id !== undefined
        && profile.motherTongue
        && profile.name
        && profile.originCountry
        && profile.project
        && profile.religion
        && profile.workExperience);
}
