import { AppConfig } from './types/app-config.type';
import { ReactAppEnv } from './types/react-app-env.type';

declare global{
    // eslint-disable-next-line no-unused-vars
    interface Window{appConfig:any}
}

const reactAppEnv = process.env as unknown as ReactAppEnv;

const appConfig: AppConfig = {
    ...reactAppEnv,
    ...window?.appConfig,
};

export const APP_CONFIG: AppConfig = Object.freeze(appConfig);
