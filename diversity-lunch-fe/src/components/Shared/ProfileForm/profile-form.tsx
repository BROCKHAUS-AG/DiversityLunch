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
    // const cultures = useSelector((store: AppStoreState) => store.culture);
    const diets = useSelector((store: AppStoreState) => store.diet);
    const educations = useSelector((store: AppStoreState) => store.education);
    const genders = useSelector((store: AppStoreState) => store.gender);
    const hobbies = useSelector((store: AppStoreState) => store.hobby);
    // const industries = useSelector((store: AppStoreState) => store.industry);
    const languages = useSelector((store: AppStoreState) => store.language);
    const project = useSelector((store: AppStoreState) => store.project);
    const religions = useSelector((store: AppStoreState) => store.religion);
    const workExperience = useSelector((store: AppStoreState) => store.workExperience);

    useEffect(() => {
        dispatch(countryFetch.getAll());
        // dispatch(cultureFetch.getAll());
        dispatch(dietFetch.getAll());
        dispatch(educationFetch.getAll());
        dispatch(genderFetch.getAll());
        dispatch(hobbyFetch.getAll());
        // dispatch(industryFetch.getAll());
        dispatch(languageFetch.getAll());
        dispatch(projectFetch.getAll());
        dispatch(religionFetch.getAll());
        dispatch(workExperienceFetch.getAll());
    }, []);

    function updateProfile<KEY extends keyof Profile>(key: KEY, value?: Profile[KEY]) {
        const updatedProfile = {
            ...profile,
            [key]: value,
        };
        setProfile(updatedProfile);

        // eslint-disable-next-line eqeqeq
        if (key == 'birthYear') {
            setIsValid(true);
        } else {
            setIsValid(checkValidity(updatedProfile));
        }

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
            <Button
                disabled={!isValid}
                label={buttonText || 'Speichern'}
                type="submit"
            />
        </form>
    );
};
