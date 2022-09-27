import { shallowEquals } from '../equals.utils';
import { Profile } from '../../types/Profile';

describe('Equals Utils', () => {
    const profileWithoutId:Profile = {
        birthYear: 2013,
        diet: 'VEGAN',
        education: 'OTHER',
        email: 'blablalba@brockhaus-ag.de',
        gender: 'FEMALE',
        hobby: 'ARCHERY',
        motherTongue: 'DEUTSCH',
        name: 'John Doe',
        originCountry: 'DEUTSCHLAND',
        project: 'Sonstiges',
        religion: 'HINDUISM',
        workExperience: 'LOW_EXPERIENCE',
    };
    const copyOfProfileWithoutId:Profile = { ...profileWithoutId };
    const profileWithId:Profile = { ...profileWithoutId, id: 42 };
    const testDataFalse:any[] = [
        [4, 5],
        [4, '4'],
        [4, { number: 4 }],
        [{ number: 5 }, { number: 4 }],
        [{ number2: 4 }, { number: 4 }],
        [{ string: '4' }, { number: 4 }],
        [{}, { number: 4 }],
        [null, { number: 4 }],
        [undefined, { number: 4 }],
        [undefined, null],
        [profileWithoutId, profileWithId],
    ];
    const testDataTrue:any[] = [
        [4, 4],
        [null, null],
        [{ number: 4 }, { number: 4 }],
        [undefined, undefined],
        ['4', '4'],
        [profileWithoutId, copyOfProfileWithoutId],
    ];

    it.each(testDataFalse)('shallowEquals(%p, %p)', (testData1, testData2) => {
        expect(shallowEquals(testData1, testData2)).toBe(false);
    });

    it.each(testDataTrue)('shallowEquals(%p, %p)', (testData1, testData2) => {
        expect(shallowEquals(testData1, testData2)).toBe(true);
    });
});
