import { Profile } from '../types/Profile';
import { ProfileDto } from '../types/dtos/ProfileDto';

export const mapProfileToDto = (profile: Profile): ProfileDto => ({
  id: profile.id,
  name: profile.name,
  email: profile.email,
  birthYear: profile.birthYear,
  currentProject: profile.project,
  gender: profile.gender,
  originCountry: profile.originCountry,
  motherTongue: profile.motherTongue,
  religion: profile.religion,
  hobby: profile.hobby,
  education: profile.education,
  workExperience: profile.workExperience,
  diet: profile.diet,
});

export const mapDtoToProfile = (profile: ProfileDto): Profile => ({
  id: profile.id,
  name: profile.name,
  email: profile.email,
  birthYear: profile.birthYear,
  project: profile.currentProject,
  gender: profile.gender,
  originCountry: profile.originCountry,
  motherTongue: profile.motherTongue,
  religion: profile.religion,
  hobby: profile.hobby,
  education: profile.education,
  workExperience: profile.workExperience,
  diet: profile.diet,
});
