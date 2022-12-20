import React, { FC, useEffect, useState } from 'react';
import { authenticatedFetchGet, authenticatedFetchPostCsv } from '../../../utils/fetch.utils';
import { PopUp } from '../pop-up/pop-up';
import { VoucherList } from '../voucher-list/voucher-list';

export const VoucherUpload: FC = () => {
    const [selectedCsvFile, setSelectedCsvFile] = useState();
    const [uploadSuccess, setUploadSuccess] = useState(false);
    const [voucherCountState, setVoucherCountState] = useState('');
    const [voucherList, setVoucherList] = useState<[]>([]);

    useEffect(() => {
        UpdateVoucherAmount();
        toggleVoucherList();
    }, [uploadSuccess]);

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
        const response : Response = await authenticatedFetchGet('api/voucher/admin/all');
        if (response.status === 200) {
            setVoucherList(await response.json());
        } else {
            // pass
        }
    };

    const toggleVoucherList = async () => {
        await getAllVouchers();
    };

    return (
        <div className="CSVUploadContainer">
            <p className="CSVUploadTitle">Gutschein Upload</p>

            <div>
                <p className="voucherCountState">
                    Es sind
                    {' '}
                    {voucherCountState}
                    {' '}
                    Gutscheine vorhanden.
                </p>
                <details>
                    <summary className="editListTitle">
                        Gutscheincodes anzeigen
                    </summary>
                    <VoucherList vouchers={voucherList} />
                </details>
            </div>

            <div>
                <label>
                    <p>Zum Uploaden ziehe .csv Files im Standardformat in das Upload Fenster</p>
                    <input type="file" accept=".csv" onChange={uploadCSVToFrontend} />
                    <button onClick={uploadCSVFile}>Upload</button>
                </label>
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
