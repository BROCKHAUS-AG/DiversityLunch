import { Diet } from '../enums/diet.type';
import { Gender } from '../enums/gender.type';
import { Religion } from '../enums/religion.type';
import { Project } from '../enums/project.type';
import { Education } from '../enums/education.type';
import { Hobby } from '../enums/hobby.type';
import { WorkExperience } from '../enums/workexperience.type';
import { Country } from '../enums/country.type';
import { Language } from '../enums/language.type';

export type ProfileDto = {
    id?: number,
    name: string,
    email: string,
    birthYear: number,
    currentProject: Project,
    gender: Gender,
    originCountry: Country,
    motherTongue: Language,
    religion: Religion,
    hobby: Hobby,
    education: Education,
    workExperience: WorkExperience,
    diet: Diet,
}
