import React from 'react';

// Styles
import '../../styles/component-styles/dashboard/tile.scss';

// Navigation
import { Link } from 'react-router-dom';

type TilesProps = {
    title: string;
    icon: string;
    link: string;
};

export const Tile = (props:TilesProps) => {
    const { title, icon, link } = props;
    return (
      <div>
          <Link to={link} className="Tile-link">
              <div className="Tile">
                  <img alt="Tile Icon" className="Tile-icon" src={icon} />
                </div>
              <h5 className="Tile-title">{title}</h5>
            </Link>
        </div>
    );
};
