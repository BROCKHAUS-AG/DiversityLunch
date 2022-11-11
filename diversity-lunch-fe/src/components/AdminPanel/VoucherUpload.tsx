import React, { FC, useEffect, useState } from 'react';
import { authenticatedFetchGet, authenticatedFetchPostCsv } from '../../utils/fetch.utils';
import { PopUp } from './userAdministration/PopUp';

export const VoucherUpload: FC = () => {
    const [selectedCsvFile, setSelectedCsvFile] = useState();
    const [uploadSuccess, setUploadSuccess] = useState(false);
    const [voucherState, setVoucherstate] = useState('');
    useEffect(() => {
        UpdateVoucherAmount();
    }, []);

    const uploadCSVToFrontend = (event: any) => {
        setSelectedCsvFile(event.target.files[0]);
        // setIsCsvFilePicked(true);
    };

    const uploadCSVFile = async () => {
        const formData = new FormData();
        // @ts-ignore
        formData.append('file', selectedCsvFile);
        const result: Response = await authenticatedFetchPostCsv('/api/voucher/upload', formData);
        if (result.status === 200) {
            setUploadSuccess(true);
        } else {
            setUploadSuccess(false);
        }
    };

    const UpdateVoucherAmount = async () => {
        const amountResp : Response = await authenticatedFetchGet('api/voucher/amount');
        if (amountResp.status === 200) {
            setVoucherstate(JSON.stringify(amountResp.json()));
        } else {
            setVoucherstate('FEHLER');
        }
    };

    return (
        <div className="test">
            <div className="CSVUploadContainer">
                <p className="CSVUploadTitle">Gutschein Upload</p>

                <div>
                    <p>
                        Es sind {voucherState} Gutscheine vorhanden.
                    </p>
                </div>

                <div>
                    <label>
                        <p>Zum Uploaden ziehe .csv Files im Standardformat in das Upload Fenster</p>
                        <input type="file" accept=".csv" onChange={uploadCSVToFrontend} />
                        <button onClick={uploadCSVFile}>Upload</button>
                    </label>
                </div>
            </div>
            {
                uploadSuccess
                && (
                    <PopUp
                        message="Der Upload war erfolgreich!"
                        buttonText="Okay"
                        onButtonClick={() => setUploadSuccess(false)}
                    />
                )
            }
        </div>
    );
};
