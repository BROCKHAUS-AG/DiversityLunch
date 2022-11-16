import { StatusCode } from './StatusCode';

export type StatusCodeHandler = {
    [key in StatusCode]: (_: Response) => void;
};
