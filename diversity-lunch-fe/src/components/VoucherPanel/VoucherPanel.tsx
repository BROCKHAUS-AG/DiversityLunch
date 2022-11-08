import React, { useState } from 'react';
import '../../styles/component-styles/voucherpanel/voucherPanel.scss';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { Button } from '../General/Button/Button';

export const VoucherPanel = () => {
    const [revealed, setRevealed] = useState(false);

    return (
        <div className="ShowVoucher">
            <DiversityIconContainer title="GUTSCHEIN" />
            <p className="ShowVoucher-text">Hier kannst du deinen Gutschein bekommen</p>
            {
                revealed
                    ? (
                        <div className="ShowVoucher-code">
                            <span className="ShowVoucher-code-text"> ABCDEFGHIJKLM </span>
                        </div>
                    )
                    : <Button label="FREISCHALTEN" onClick={() => setRevealed(true)} />
            }
        </div>
    );
};
