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

export function partialProfileToProfile(profile: Partial<Profile>): Profile | undefined {
    if (profile.birthYear
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
        && profile.workExperience) {
        return profile as Profile;
    }
    return undefined;
}

export function isUpdatedProfile(profile: Profile, updatedProfile: Profile): boolean {
    return (profile.diet.id !== updatedProfile.diet.id
        || profile.birthYear !== updatedProfile.birthYear
        || profile.education.id !== updatedProfile.education.id
        || profile.gender.id !== updatedProfile.gender.id
        || profile.hobby.id !== updatedProfile.hobby.id
        || profile.motherTongue.id !== updatedProfile.motherTongue.id
        || profile.originCountry.id !== updatedProfile.originCountry.id
        || profile.project.id !== updatedProfile.project.id
        || profile.religion.id !== updatedProfile.religion.id
        || profile.workExperience.id !== updatedProfile.workExperience.id);
}
