import React from 'react';

type GenericErrorPageProps = {
    errorMessage?: string,
};

export const GenericErrorPage: React.FC<GenericErrorPageProps> = (props: GenericErrorPageProps) => {
    const { errorMessage } = props;
    return (
        <div>{errorMessage}</div>
    );
};

GenericErrorPage.defaultProps = {
    errorMessage: 'Ein unerwarteter Fehler ist aufgetreten.',
};
