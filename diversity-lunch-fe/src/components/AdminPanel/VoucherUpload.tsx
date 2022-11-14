import React, { FC, useEffect, useState } from 'react';
import { authenticatedFetchGet, authenticatedFetchPostCsv } from '../../utils/fetch.utils';
import { PopUp } from './userAdministration/PopUp';
import { VoucherList } from './VoucherList';

export const VoucherUpload: FC = () => {
    const [selectedCsvFile, setSelectedCsvFile] = useState();
    const [uploadSuccess, setUploadSuccess] = useState(false);
    const [voucherCountState, setVoucherCountState] = useState('');
    const [showVoucherList, setShowVoucherList] = useState(false);
    const [voucherList, setVoucherList] = useState<[]>([]);

    useEffect(() => {
        UpdateVoucherAmount();
    }, [voucherCountState]);

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
            setVoucherCountState(JSON.stringify(await amountResp.json()));
        } else {
            setVoucherCountState('FEHLER');
        }
    };

    const getAllVouchers = async () => {
        const response : Response = await authenticatedFetchGet('api/voucher/all');
        if (response.status === 200) {
            setVoucherList(await response.json());
        } else {
            // pass
        }
    };

    const toggleVoucherList = async () => {
        await getAllVouchers();
        setShowVoucherList(true);
    };

    return (
        <div className="test">
            <div className="CSVUploadContainer">
                <p className="CSVUploadTitle">Gutschein Upload</p>

                <div>
                    <p>
                        Es sind
                        {' '}
                        {voucherCountState}
                        {' '}
                        Gutscheine vorhanden.
                    </p>
                </div>
                <div>
                    <button onClick={toggleVoucherList}>Gutscheinliste</button>
                    {showVoucherList
                        && <VoucherList vouchers={voucherList} />}
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
