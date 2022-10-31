import React, { FC, useState } from 'react';
import { authenticatedFetchPostCsv } from '../../utils/fetch.utils';

export const VoucherUpload: FC = () => {
    const [selectedCsvFile, setSelectedCsvFile] = useState();
    // const [isCsvFilePicked, setIsCsvFilePicked] = useState(false);

    const uploadCSVToFrontend = (event: any) => {
        setSelectedCsvFile(event.target.files[0]);
        // setIsCsvFilePicked(true);
    };

    const uploadCSVFile = async () => {
        const formData = new FormData();
        // @ts-ignore
        formData.append('file', selectedCsvFile);
        const result: Response = await authenticatedFetchPostCsv('/api/voucher/upload', formData);
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
