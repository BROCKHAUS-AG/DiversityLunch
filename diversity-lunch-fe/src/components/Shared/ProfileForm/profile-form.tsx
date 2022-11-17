import {
    ChangeEvent, FC, FormEvent, useEffect, useState,
} from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { TextField } from '@material-ui/core';
import { Profile } from '../../../model/Profile';
import { Dropdown } from '../../Questions/Dropdown';
import { Button } from '../../General/Button/Button';
import { AppStoreState } from '../../../store/Store';
import { countryFetch } from '../../../data/country/fetch-country';
import { dietFetch } from '../../../data/diet/fetch-diet';
import { educationFetch } from '../../../data/education/fetch-education';
import { genderFetch } from '../../../data/gender/fetch-gender';
import { hobbyFetch } from '../../../data/hobby/fetch-hobby';
import { languageFetch } from '../../../data/language/language-fetch';
import { projectFetch } from '../../../data/project/project-fetch';
import { religionFetch } from '../../../data/religion/religion-fetch';
import { workExperienceFetch } from '../../../data/work-experience/work-experience-fetch';
import { sexualOrientationFetch } from '../../../data/sexual-orientation/sexual-orientation-fetch';
import { socialBackgroundFetch } from '../../../data/social-background/social-background-fetch';

export type ProfileFormCallback = (formData: Partial<Profile>) => void;
export type ProfileFormIsValidCallback = (formData: Partial<Profile>)=>boolean;

export interface ProfileFormProps {
    initialProfile?: Partial<Profile>,
    checkValidity: ProfileFormIsValidCallback,
    onSubmit: ProfileFormCallback,
    onChange?: ProfileFormCallback,
    buttonText?: string
}

export const ProfileForm: FC<ProfileFormProps> = ({
    initialProfile, checkValidity, onSubmit, onChange, buttonText,
}: ProfileFormProps) => {
    const [profile, setProfile] = useState(initialProfile || {} as Partial<Profile>);

    const [isValid, setIsValid] = useState(checkValidity(profile));
    const dispatch = useDispatch();
    const countries = useSelector((store: AppStoreState) => store.country);
    const diets = useSelector((store: AppStoreState) => store.diet);
    const educations = useSelector((store: AppStoreState) => store.education);
    const genders = useSelector((store: AppStoreState) => store.gender);
    const hobbies = useSelector((store: AppStoreState) => store.hobby);
    const languages = useSelector((store: AppStoreState) => store.language);
    const project = useSelector((store: AppStoreState) => store.project);
    const religions = useSelector((store: AppStoreState) => store.religion);
    const workExperience = useSelector((store: AppStoreState) => store.workExperience);
    const sexualOrientation = useSelector((store: AppStoreState) => store.sexualOrientation);
    const socialBackground = useSelector((store: AppStoreState) => store.socialBackground);

    useEffect(() => {
        // TODO: Handle network and http errors properly tgohlisch 17.11.2022
        dispatch(countryFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(dietFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(educationFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(genderFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(hobbyFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(languageFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(projectFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(religionFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(workExperienceFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(sexualOrientationFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(socialBackgroundFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
    }, []);

    function updateProfile<KEY extends keyof Profile>(key: KEY, value?: Profile[KEY]) {
        const updatedProfile = {
            ...profile,
            [key]: value,
        };
        setProfile(updatedProfile);
        setIsValid(checkValidity(updatedProfile));

        if (onChange) onChange(updatedProfile);
    }

    function formSubmitted(formEvent: FormEvent<HTMLFormElement>) {
        formEvent.preventDefault();
        formEvent.stopPropagation();
        if (onSubmit) onSubmit(profile);
    }

    return (
        <form onSubmit={formSubmitted} className="ProfileForm">
            <div className="DropdownQuestion">
                <p className="DropdownQuestion-question">Wann wurdest du geboren?</p>
                <TextField
                    id="birth_year"
                    label="Geburtsjahr"
                    variant="outlined"
                    type="number"
                    onChange={(e: ChangeEvent<HTMLInputElement>) => updateProfile('birthYear', e.target.valueAsNumber)}
                    InputProps={{ inputProps: { min: 1900, max: 2022 } }}
                    defaultValue={profile.birthYear}
                />
            </div>
            <Dropdown
                options={project.items}
                placeholder="In welchem Projekt arbeitest du derzeit?"
                onChange={(value) => updateProfile('project', value)}
                label="Projekt"
                currentValue={profile.project || undefined}
            />
            <Dropdown
                options={genders.items}
                placeholder="Wähle ein Geschlecht?"
                onChange={(value) => updateProfile('gender', value)}
                label="Geschlecht"
                currentValue={profile.gender || undefined}
            />
            <Dropdown
                options={countries.items}
                placeholder="Was ist dein Herkunftsland?"
                onChange={(value) => updateProfile('originCountry', value)}
                label="Herkunftsland"
                currentValue={profile.originCountry || undefined}
            />
            <Dropdown
                options={languages.items}
                placeholder="Was ist deine Muttersprache?"
                onChange={(value) => updateProfile('motherTongue', value)}
                label="Muttersprache"
                currentValue={profile.motherTongue || undefined}
            />
            <Dropdown
                options={religions.items}
                placeholder="An welche Religion glaubst du?"
                onChange={(value) => updateProfile('religion', value)}
                label="Religion"
                currentValue={profile.religion || undefined}
            />
            <Dropdown
                options={workExperience.items}
                placeholder="Wie viele Jahre Berufserfahrung hast du schon gesammelt?"
                onChange={(value) => updateProfile('workExperience', value)}
                label="Berufserfahrung"
                currentValue={profile.workExperience || undefined}
            />
            <Dropdown
                options={hobbies.items}
                placeholder="Was hast du für ein Hobby?"
                onChange={(value) => updateProfile('hobby', value)}
                label="Hobby"
                currentValue={profile.hobby || undefined}
            />
            <Dropdown
                options={educations.items}
                placeholder="Welchen Bildungsweg hast du bisher bestritten?"
                onChange={(value) => updateProfile('education', value)}
                label="Bildungsweg"
                currentValue={profile.education || undefined}
            />

            <Dropdown
                options={diets.items}
                placeholder="Wie ernährst du dich?"
                onChange={(value) => updateProfile('diet', value)}
                label="Ernährung"
                currentValue={profile.diet || undefined}
            />
            <Dropdown
                options={sexualOrientation.items}
                placeholder="Was ist deine sexuelle Orientierung?"
                onChange={(value) => updateProfile('sexualOrientation', value)}
                label="Sexualität"
                currentValue={profile.sexualOrientation || undefined}
            />
            <Dropdown
                options={socialBackground.items}
                placeholder="Bist du ein Akademikerkind, oder eher die erste Person in der Familie, die studiert oder ihr Abitur gemacht hat?"
                onChange={(value) => updateProfile('socialBackground', value)}
                label="Soziale Herkunft"
                currentValue={profile.socialBackground || undefined}
            />
            <Button
                disabled={!isValid}
                label={buttonText || 'Speichern'}
                type="submit"
            />
        </form>
    );
};
