import { Hobby } from './Hobby';
import { Identifiable } from '../data/generic/Identifiable';
import { Country } from './Country';
import { Culture } from './Culture';
import { Industry } from './Industry';
import { Gender } from './Gender';
import { Diet } from './Diet';
import { Education } from './Education';
import { Project } from './Project';
import { Language } from './Language';
import { WorkExperience } from './WorkExperience';
import { Religion } from './Religion';

export interface Profile extends Identifiable{
    firstname: string,
    lastname: string,
    birthyear: number,
    email: string,
    profilePicture: string,
    hobbies: Hobby,
    project: Project
    country: Country,
    culture: Culture,
    diet: Diet,
    education: Education,
    gender: Gender,
    industry: Industry,
    motherTongue: Language,
    religion: Religion,
    workExperience: WorkExperience
}
