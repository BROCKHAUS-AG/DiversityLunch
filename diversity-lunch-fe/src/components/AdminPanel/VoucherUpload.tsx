import React, {
    ChangeEvent, FC, useEffect, useState,
} from 'react';
import { authenticatedFetchGet, authenticatedFetchPostCsv } from '../../utils/fetch.utils';
import { PopUp } from './userAdministration/PopUp';

export const VoucherUpload: FC = () => {
    const [selectedCsvFileState, setSelectedCsvFileState] = useState<File | undefined>(undefined);
    const [uploadState, setUploadState] = useState(false);
    const [isError, setError] = useState(false);
    const [voucherState, setVoucherState] = useState('');
    useEffect(() => {
        updateVoucherAmount();
    }, []);

    const uploadCSVToFrontend = (event: ChangeEvent<HTMLInputElement>) => {
        if (event.target.files) {
            setSelectedCsvFileState(event.target.files[0]);
        }
    };

    const uploadCSVFile = async () => {
        const formData = new FormData();
        if (!selectedCsvFileState) {
            setUploadState(false);
            return;
        }
        formData.append('file', selectedCsvFileState);
        const result: Response = await authenticatedFetchPostCsv('/api/voucher/upload', formData);
        setUploadState(result.status === 200);
        if (result.status === 200) setSelectedCsvFileState(undefined);
    };

    const updateVoucherAmount = async () => {
        const amountResp : Response = await authenticatedFetchGet('api/voucher/amount');
        if (amountResp.status === 200) {
            setVoucherState(await amountResp.text());
        } else {
            setError(true);
        }
    };

    return (
        <div className="test">
            <div className="CSVUploadContainer">
                <p className="CSVUploadTitle">Gutschein Upload</p>

                <div>
                    <p>
                        {
                            voucherState && `${voucherState} Gutschein(e) vorhanden.`
                        }
                    </p>
                </div>

                <div>
                    <label>
                        <p>Zum Hochladen ziehe .csv Files im CSV-Format RFC4180 in das Upload Fenster</p>
                        <input type="file" accept=".csv" onChange={uploadCSVToFrontend} />
                        <button disabled={!selectedCsvFileState} onClick={uploadCSVFile}>Upload</button>
                    </label>
                </div>
            </div>
            {
                (uploadState)
                && (
                    <PopUp
                        message="Der Upload war erfolgreich!"
                        buttonText="Okay"
                        onButtonClick={() => setUploadState(false)}
                    />
                )
            }
            {
                (isError)
                && (
                    <PopUp
                        message="Ein Fehler ist aufgetreten"
                        buttonText="Okay"
                        onButtonClick={() => setError(false)}
                    />
                )
            }
        </div>
    );
};
