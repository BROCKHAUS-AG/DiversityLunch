import React from 'react';

// Styles
import './tile-icon-link.scss';

// Navigation
import { Link } from 'react-router-dom';

type TilesProps = {
    title: string;
    icon: string;
    link: string;
};

export const TileIconLink = (props:TilesProps) => {
    const { title, icon, link } = props;
    return (
        <div>
            <Link to={link} className="Tile-link">
                <div className="Tile">
                    <img alt="TileIconLink Icon" className="Tile-icon" src={icon} />
                </div>
                <h5 className="Tile-title">{title}</h5>
            </Link>
        </div>
    );
};
