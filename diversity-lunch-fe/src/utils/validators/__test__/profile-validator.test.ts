import { Profile } from '../../../model/Profile';
import { isValidProfile } from '../profile-validator';

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
            hobbies: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            id: 42,
            motherTongue: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            name: 'Klaus',
            originCountry: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            project: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            religion: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
            workExperience: { id: 7, descriptor: 'hier könnte was sinnvolles stehen, hauptsache nicht leer!' },
        };

        const result: boolean = isValidProfile(profile);

        expect(result).toBe(true);
    });

    it('truthy', () => {
        expect([] || ['hallo']).toEqual([]);
    });
});
