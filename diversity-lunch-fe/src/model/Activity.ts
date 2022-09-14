import { Category } from './Category';
import { Identifiable } from '../data/generic/Identifiable';

export interface Activity extends Identifiable {
    category: Category,
}
