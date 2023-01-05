import { Profile } from '../../model/Profile';
import { isUpdatedProfile, isValidProfile } from './profile-validator';

describe('isValidProfile', () => {
    it('should return false if all keys are undefined', () => {
        const profile: Partial<Profile> = {};

        const result: boolean = isValidProfile(profile);

        expect(result).toBe(false);
    });

    it('should return true if all keys are set to non falsy value', () => {
        const profile: Profile = {
            birthYear: 2000,
            diet: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            education: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            email: 'beste@email.wogibt',
            gender: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            hobby: [{ id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false }],
            id: 42,
            motherTongue: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            name: 'Klaus',
            originCountry: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            project: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            religion: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            workExperience: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            sexualOrientation: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            socialBackground: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            socialBackgroundDiscrimination: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            wasChangedByAdmin: false,
        };

        const result: boolean = isValidProfile(profile);

        expect(result).toBe(true);
    });

    it('truthy', () => {
        expect([] || ['hallo']).toEqual([]);
    });
});

describe('isUpdatedProfile', () => {
    it('returns false if updatedProfile has NO difference', () => {
        const currentProfile: Profile = {
            birthYear: 2000,
            diet: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            education: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            email: 'beste@email.wogibt',
            gender: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            hobby: [{ id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false }],
            id: 42,
            motherTongue: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            name: 'Klaus',
            originCountry: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            project: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            religion: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            workExperience: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            sexualOrientation: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            socialBackground: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            socialBackgroundDiscrimination: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            wasChangedByAdmin: false,
        };

        const updatedProfile: Profile = {
            birthYear: 2000,
            diet: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            education: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            email: 'beste@email.wogibt',
            gender: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            hobby: [{ id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false }],
            id: 42,
            motherTongue: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            name: 'Klaus',
            originCountry: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            project: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            religion: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            workExperience: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            sexualOrientation: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            socialBackground: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            socialBackgroundDiscrimination: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            wasChangedByAdmin: false,
        };

        const result : boolean = isUpdatedProfile(currentProfile, updatedProfile);
        expect(result).toBe(false);
    });

    it('returns true if at least one attribute of updatedProfile is different from current profile', () => {
        const currentProfile: Profile = {
            birthYear: 2000,
            diet: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            education: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            email: 'beste@email.wogibt',
            gender: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            hobby: [{ id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false }],
            id: 42,
            motherTongue: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            name: 'Klaus',
            originCountry: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            project: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            religion: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            workExperience: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            sexualOrientation: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            socialBackground: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            socialBackgroundDiscrimination: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!', default: false },
            wasChangedByAdmin: false,
        };

        Object.entries(currentProfile).forEach(([key]) => {
            const updatedProfile : Profile = JSON.parse(JSON.stringify(currentProfile));
            if (key === 'birthYear') updatedProfile[key] += 1;
            else if (key === 'id' || key === 'name' || key === 'email' || key === 'hobby') return;
            else {
                // @ts-ignore
                updatedProfile[key].id += 1; // Element implicitly has an 'any' type, type 'string' can't be used to index type 'Profile'
            }
            const result : boolean = isUpdatedProfile(currentProfile, updatedProfile);
            expect(result).toBe(true);
        });
    });
});
