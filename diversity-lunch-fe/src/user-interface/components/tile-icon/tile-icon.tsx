import React from 'react';

// Styles
import './tile-icon.scss';

type TilesProps = {
    title: string;
    icon: string;
};

export const TileIcon = (props:TilesProps) => {
    const { title, icon } = props;
    return (
        <div className="ClickIcon-Wrapper">
            <span className="Tile-link">
                <div className="Tile">
                    <img alt="TileIconLink Icon" className="Tile-icon" src={icon} />
                </div>
                <h5 className={title.length < 1 ? 'Tile-title' : 'Tile-title-hide'}>{title}</h5>
            </span>
        </div>
    );
};
