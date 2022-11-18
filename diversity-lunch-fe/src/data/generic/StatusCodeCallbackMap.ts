import { StatusCode } from './StatusCode';

export type StatusCodeCallbackMap = {
    [key in StatusCode]?: (_: Response) => void;
};
