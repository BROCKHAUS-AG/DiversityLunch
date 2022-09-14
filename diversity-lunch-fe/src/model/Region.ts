import { Identifiable } from '../data/generic/Identifiable';
import { Country } from './Country';

export interface Region extends Identifiable {
    descriptor: string,
    isPartOf: Country,
}
