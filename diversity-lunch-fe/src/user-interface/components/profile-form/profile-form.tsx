import {
    ChangeEvent, FC, FormEvent, useEffect, useState,
} from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { TextField } from '@material-ui/core';
import { Profile } from '../../../model/Profile';
import { Dropdown } from '../dropdown/dropdown';
import { Button } from '../button/button';
import { AppStoreState } from '../../../data/app-store';
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
import { socialBackgroundDiscriminationFetch } from '../../../data/social-background-discrimination/social-background-discrimination-fetch';
import { IdentifiableState } from '../../../data/generic/GenericSlice';
import { Country } from '../../../model/Country';
import { Diet } from '../../../model/Diet';
import { Education } from '../../../model/Education';
import { Gender } from '../../../model/Gender';
import { Hobby } from '../../../model/Hobby';
import { Language } from '../../../model/Language';
import { Religion } from '../../../model/Religion';
import { Project } from '../../../model/Project';
import { WorkExperience } from '../../../model/WorkExperience';
import { SexualOrientation } from '../../../model/SexualOrientation';
import { SocialBackground } from '../../../model/SocialBackground';
import { Identifiable } from '../../../data/generic/Identifiable';
import { SocialBackgroundDiscrimination } from '../../../model/SocialBackgroundDiscrimination';

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
    const countries : IdentifiableState<Country> = useSelector((store: AppStoreState) => store.country);
    const diets : IdentifiableState<Diet> = useSelector((store: AppStoreState) => store.diet);
    const educations : IdentifiableState<Education> = useSelector((store: AppStoreState) => store.education);
    const genders : IdentifiableState<Gender> = useSelector((store: AppStoreState) => store.gender);
    const hobbies : IdentifiableState<Hobby> = useSelector((store: AppStoreState) => store.hobby);
    const languages : IdentifiableState<Language> = useSelector((store: AppStoreState) => store.language);
    const project : IdentifiableState<Project> = useSelector((store: AppStoreState) => store.project);
    const religions: IdentifiableState<Religion> = useSelector((store: AppStoreState) => store.religion);
    const workExperience : IdentifiableState<WorkExperience> = useSelector((store: AppStoreState) => store.workExperience);
    const sexualOrientation : IdentifiableState<SexualOrientation> = useSelector((store: AppStoreState) => store.sexualOrientation);
    const socialBackground : IdentifiableState<SocialBackground> = useSelector((store: AppStoreState) => store.socialBackground);
    const socialBackgroundDiscrimination : IdentifiableState<SocialBackgroundDiscrimination> = useSelector(
        (store: AppStoreState) => store.socialBackgroundDiscrimination,
    );

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
        dispatch(socialBackgroundDiscriminationFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
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

    const DEFAULT_OPTION_DESCRIPTOR = 'keine angabe';
    function sortOptions<T extends Identifiable>(state : IdentifiableState<T>) {
        const copiedList = [...state.items];
        copiedList.sort((a, b) => a.descriptor.localeCompare(b.descriptor));
        return copiedList.sort((a, b) => {
            if (a.descriptor.toLowerCase() === DEFAULT_OPTION_DESCRIPTOR) return -1;
            if (b.descriptor.toLowerCase() === DEFAULT_OPTION_DESCRIPTOR) return 1;
            return 0;
        });
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
                options={sortOptions(project)}
                placeholder="In welchem Projekt arbeitest du derzeit?"
                onChange={(value) => updateProfile('project', value)}
                label="Projekt"
                currentValue={profile.project || undefined}
            />
            <Dropdown
                options={sortOptions(genders)}
                placeholder="Was ist deine geschlechtliche Identität?"
                onChange={(value) => updateProfile('gender', value)}
                label="Geschlechtliche Identität"
                currentValue={profile.gender || undefined}
            />
            <Dropdown
                options={sortOptions(countries)}
                placeholder="Was ist deine ethnische Herkunft?"
                onChange={(value) => updateProfile('originCountry', value)}
                label="Ethnische Herkunft"
                currentValue={profile.originCountry || undefined}
            />
            <Dropdown
                options={sortOptions(languages)}
                placeholder="Was ist deine Muttersprache?"
                onChange={(value) => updateProfile('motherTongue', value)}
                label="Muttersprache"
                currentValue={profile.motherTongue || undefined}
            />
            <Dropdown
                options={sortOptions(religions)}
                placeholder="An welche Religion glaubst du?"
                onChange={(value) => updateProfile('religion', value)}
                label="Religion"
                currentValue={profile.religion || undefined}
            />
            <Dropdown
                options={sortOptions(workExperience)}
                placeholder="Wie viele Jahre Berufserfahrung hast du schon gesammelt?"
                onChange={(value) => updateProfile('workExperience', value)}
                label="Berufserfahrung"
                currentValue={profile.workExperience || undefined}
            />
            <Dropdown
                options={sortOptions(hobbies)}
                placeholder="Was hast du für ein Hobby?"
                onChange={(value) => updateProfile('hobby', value)}
                label="Hobby"
                currentValue={profile.hobby || undefined}
            />
            <Dropdown
                options={sortOptions(educations)}
                placeholder="Welchen Bildungsweg hast du bisher bestritten?"
                onChange={(value) => updateProfile('education', value)}
                label="Bildungsweg"
                currentValue={profile.education || undefined}
            />
            <Dropdown
                options={sortOptions(diets)}
                placeholder="Wie ernährst du dich?"
                onChange={(value) => updateProfile('diet', value)}
                label="Ernährung"
                currentValue={profile.diet || undefined}
            />
            <Dropdown
                options={sortOptions(sexualOrientation)}
                placeholder="Was ist deine sexuelle Orientierung?"
                onChange={(value) => updateProfile('sexualOrientation', value)}
                label="Sexualität"
                currentValue={profile.sexualOrientation || undefined}
            />
            <Dropdown
                options={sortOptions(socialBackground)}
                placeholder="Bist du ein Akademikerkind, oder eher die erste Person in der Familie, die studiert oder ihr Abitur gemacht hat?"
                onChange={(value) => updateProfile('socialBackground', value)}
                label="Soziale Herkunft"
                currentValue={profile.socialBackground || undefined}
            />
            <Dropdown
                options={sortOptions(socialBackgroundDiscrimination)}
                placeholder="Wurdest du jemals aufgrund deiner sozialen Herkunft Vorurteilen ausgesetzt,
                herabwürdigend behandelt, benachteiligt oder ausgeschlossen?"
                onChange={(value) => updateProfile('socialBackgroundDiscrimination', value)}
                label="Ausgrenzung?"
                currentValue={profile.socialBackgroundDiscrimination || undefined}
            />
            <Button
                disabled={!isValid}
                label={buttonText || 'Speichern'}
                type="submit"
            />
        </form>
    );
};
