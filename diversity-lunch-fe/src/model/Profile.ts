import { Hobby } from './Hobby';
import { Country } from './Country';
import { Gender } from './Gender';
import { Diet } from './Diet';
import { Education } from './Education';
import { Project } from './Project';
import { Language } from './Language';
import { WorkExperience } from './WorkExperience';
import { Religion } from './Religion';
import { SexualOrientation } from './SexualOrientation';
import { SocialBackground } from './SocialBackground';
import { Discrimination } from './Discrimination';

export interface Profile {
    id: number,
    name: string,
    birthYear: number,
    email: string,
    hobby: Hobby,
    project: Project
    originCountry: Country,
    diet: Diet,
    education: Education,
    gender: Gender,
    motherTongue: Language,
    religion: Religion,
    workExperience: WorkExperience,
    sexualOrientation: SexualOrientation,
    socialBackground: SocialBackground,
    discrimination: Discrimination
}
