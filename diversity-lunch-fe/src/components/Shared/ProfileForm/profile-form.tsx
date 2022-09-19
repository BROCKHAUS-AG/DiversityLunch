import {
    ChangeEvent, FC, FormEvent, useCallback, useEffect, useState,
} from 'react';
import { useDispatch, useSelector } from 'react-redux';
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

export type ProfileFormCallback = (formData: Profile) => void;

export interface ProfileFormProps {
    profile?: Profile,
    disabled?: boolean,
    onSubmit?: ProfileFormCallback,
    onChange?: ProfileFormCallback,
    buttonText?: string
}

export const ProfileForm: FC<ProfileFormProps> = ({
    profile, disabled, onSubmit, onChange, buttonText,
}: ProfileFormProps) => {
    const [profileState, setProfileState] = useState(profile || {} as Profile);

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

    const updateProfileField = useCallback(<KEY extends keyof Profile>(key: KEY, value?: Profile[KEY]) => {
        const updatedProfileState = {
            ...profileState,
            [key]: value,
        };
        setProfileState(updatedProfileState);
        if (onChange) onChange(updatedProfileState);
    }, []);

    function formSubmitted(formEvent: FormEvent<HTMLFormElement>) {
        formEvent.preventDefault();
        formEvent.stopPropagation();
        if (onSubmit) onSubmit(profileState);
    }

    return (

        <form onSubmit={formSubmitted}>
            <label>
                Wann wurdest du geboren?
                <input
                    type="number"
                    min="1900"
                    max="2022"
                    onInput={(e: ChangeEvent<HTMLInputElement>) => updateProfileField('birthYear', e.target.valueAsNumber)}
                />
            </label>
            <Dropdown
                options={project.items}
                label="In welchem Projekt arbeitest du derzeit?"
                onChange={(value) => updateProfileField('project', value)}
            />

            <Dropdown
                options={genders.items}
                label="Wähle ein Geschlecht?"
                onChange={(value) => updateProfileField('gender', value)}
            />

            <Dropdown
                options={countries.items}
                label="Was ist dein Herkunftsland?"
                onChange={(value) => updateProfileField('originCountry', value)}
            />

            <Dropdown
                options={languages.items}
                label="Was ist deine Muttersprache?"
                onChange={(value) => updateProfileField('motherTongue', value)}
            />

            <Dropdown
                options={religions.items}
                label="An welche Religion glaubst du?"
                onChange={(value) => updateProfileField('religion', value)}
            />

            <Dropdown
                options={workExperience.items}
                label="Wie viele Jahre Berufserfahrung hast du schon gesammelt?"
                onChange={(value) => updateProfileField('workExperience', value)}
            />

            <Dropdown
                options={hobbies.items}
                label="Was hast du für ein Hobby?"
                onChange={(value) => updateProfileField('hobbies', value)}
            />

            <Dropdown
                options={educations.items}
                label="Welchen Bildungsweg hast du bisher bestritten?"
                onChange={(value) => updateProfileField('education', value)}
            />

            <Dropdown
                options={diets.items}
                label="Was ist dein Herkunftsland?"
                onChange={(value) => updateProfileField('diet', value)}
            />

            <Button
                disabled={disabled}
                label={buttonText || 'Speichern'}
                type="submit"
            />
        </form>
    );
};
