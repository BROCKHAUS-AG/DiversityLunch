import { StatusCode } from './StatusCode';

export type StatusCodeMap = {
    [key in StatusCode]: (_: Response) => void;
};
