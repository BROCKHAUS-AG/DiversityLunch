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
            diet: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            education: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            email: 'beste@email.wogibt',
            gender: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            hobby: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            id: 42,
            motherTongue: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            name: 'Klaus',
            originCountry: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            project: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            religion: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            workExperience: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            sexualOrientation: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            socialBackground: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            socialBackgroundDiscrimination: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
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
            diet: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            education: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            email: 'beste@email.wogibt',
            gender: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            hobby: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            id: 42,
            motherTongue: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            name: 'Klaus',
            originCountry: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            project: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            religion: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            workExperience: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            sexualOrientation: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            socialBackground: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            socialBackgroundDiscrimination: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
        };

        const updatedProfile: Profile = {
            birthYear: 2000,
            diet: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            education: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            email: 'beste@email.wogibt',
            gender: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            hobby: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            id: 42,
            motherTongue: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            name: 'Klaus',
            originCountry: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            project: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            religion: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            workExperience: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            sexualOrientation: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            socialBackground: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            socialBackgroundDiscrimination: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
        };

        const result : boolean = isUpdatedProfile(currentProfile, updatedProfile);
        expect(result).toBe(false);
    });

    it('returns true if at least one attribute of updatedProfile is different from current profile', () => {
        const currentProfile: Profile = {
            birthYear: 2000,
            diet: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            education: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            email: 'beste@email.wogibt',
            gender: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            hobby: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            id: 42,
            motherTongue: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            name: 'Klaus',
            originCountry: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            project: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            religion: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            workExperience: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            sexualOrientation: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            socialBackground: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            socialBackgroundDiscrimination: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
        };

        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        Object.entries(currentProfile).forEach(([key], index) => {
            const updatedProfile : Profile = JSON.parse(JSON.stringify(currentProfile));
            if (key === 'birthYear') updatedProfile[key] += 1;
            else if (key === 'id' || key === 'name' || key === 'email') return;
            else {
                // eslint-disable-next-line no-param-reassign
                // @ts-ignore
                updatedProfile[key].id += 1;
            }
            const result : boolean = isUpdatedProfile(currentProfile, updatedProfile);
            expect(result).toBe(true);
        });
    });
});
