import React, { FC, useState } from 'react';
import { authenticatedFetchPostCsv } from '../../utils/fetch.utils';
import {GeneralPopup} from "../Shared/GeneralPopup";

export const VoucherUpload: FC = () => {
    const [selectedCsvFile, setSelectedCsvFile] = useState();
    const [uploadSuccess, setUploadSuccess] = useState(false);

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
            window.alert("Yeeey");
        } else {
            setUploadSuccess(false);
        }
    };

    return (
        <div className="CSVUploadContainer">
            <p className="CSVUploadTitle">Gutschein Upload</p>
            <div>
                <label>
                    <p>Zum Uploaden ziehe .csv Files im Standardformat in das Upload Fenster</p>
                    <input type="file" accept=".csv" onChange={uploadCSVToFrontend} />
                    <button onClick={uploadCSVFile}>Upload</button>
                </label>
            </div>
        </div>
    );
};
