import { Hobby } from './Hobby';
import { Identifiable } from '../data/generic/Identifiable';

export interface Profile extends Identifiable{
    firstname: string,
    lastname: string,
    birthyear: number,
    email: string,
    profilePicture: string,
    hobbies: Hobby[],
}
