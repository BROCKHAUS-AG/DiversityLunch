import React from 'react';

import '../../styles/component-styles/shared/loading-animation.scss';

type LoadingAnimationProps = {
    size?: 'default' | 'block-app';
};

export const LoadingAnimation: React.FC<LoadingAnimationProps> = (props: LoadingAnimationProps) => {
    const { size } = props;

    const getClassName = React.useCallback(() => {
        const blockingClass = size === 'block-app' ? ' blocking' : '';
        return `loading-animation${blockingClass}`;
    }, [size]);

    return (
        <div className={getClassName()}>
            <div className="animation">
                <div className="bounce1" />
                <div className="bounce2" />
                <div className="bounce3" />
            </div>
        </div>
    );
};

LoadingAnimation.defaultProps = {
    size: 'default',
};
