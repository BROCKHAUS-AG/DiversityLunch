import { Profile } from '../../model/Profile';
import { Hobby } from '../../model/Hobby';

export function isValidProfile(profile: Partial<Profile>): boolean {
    return !!(profile.birthYear
        && profile.diet
        && profile.education
        && profile.email
        && profile.gender
        && profile.id !== undefined
        && profile.motherTongue
        && profile.name
        && profile.originCountry
        && profile.project
        && profile.religion
        && profile.workExperience
        && profile.sexualOrientation
        && profile.socialBackground
        && profile.socialBackgroundDiscrimination
        && profile.hobby
        && profile.hobby!.length <= 3
    );
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
        && profile.workExperience
        && profile.sexualOrientation
        && profile.socialBackground
        && profile.socialBackgroundDiscrimination) {
        return profile as Profile;
    }
    return undefined;
}

export function isUpdatedProfile(profile: Profile, updatedProfile: Profile): boolean {
    return (profile.diet.id !== updatedProfile.diet.id
        || profile.birthYear !== updatedProfile.birthYear
        || profile.education.id !== updatedProfile.education.id
        || profile.gender.id !== updatedProfile.gender.id
        || profile.motherTongue.id !== updatedProfile.motherTongue.id
        || profile.originCountry.id !== updatedProfile.originCountry.id
        || profile.project.id !== updatedProfile.project.id
        || profile.religion.id !== updatedProfile.religion.id
        || profile.workExperience.id !== updatedProfile.workExperience.id
        || profile.sexualOrientation.id !== updatedProfile.sexualOrientation.id
        || profile.socialBackground.id !== updatedProfile.socialBackground.id
        || profile.socialBackgroundDiscrimination.id !== updatedProfile.socialBackgroundDiscrimination.id)
        || isUpdatedHobbies(profile.hobby, updatedProfile.hobby);
}

function isUpdatedHobbies(first: Array<Hobby>, second: Array<Hobby>): boolean {
    if (first.length !== second.length) {
        return true;
    }
    return !first.map((value) => second.includes(value)).every((x) => x);
}
