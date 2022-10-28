import React, { FC, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { AccountsState } from '../../data/accounts/accounts-reducer';
import { AppStoreState } from '../../store/Store';
import { ProfilesState } from '../../data/profiles/profiles-reducer';
import { LoadingAnimation } from '../Shared/LoadingAnimation';
import { authenticatedFetchPostCsv } from '../../utils/fetch.utils';
import { globalErrorSlice } from '../../data/error/global-error-slice';

export const UserList: FC = () => {
    const accountsState: AccountsState = useSelector((store: AppStoreState) => store.accounts);
    const profilesState: ProfilesState = useSelector((store: AppStoreState) => store.profiles);
    const [uploadState, setUploadState] = useState(null);
    const dispatch = useDispatch();

    useEffect(() => {

    }, []);

    if (!accountsState.fetched || !profilesState.fetched) {
        return <LoadingAnimation />;
    }
    const onFileChange = event => {
        setUploadState()
    }
    const uploadCSV = (data: FormData) => {
        dispatch(async () => {
            try {
                const response = await authenticatedFetchPostCsv('api/voucher/upload', data);

                if (!response.ok) {
                    dispatch(globalErrorSlice.httpError({ statusCode: response.status }));
                } else {
                    const result: FormData = await response.formData();

                    dispatch(setUploadState(result));
                }
            } catch (error) {
                dispatch(globalErrorSlice.error(undefined));
            }
        });
    };
    return (
        <div className="CSVupload">
            <h3>Upload</h3>
            <p>Zum Uploaden ziehe .csv Files im Standardformat in das Upload Fenster</p>
            <input className="fileUpload" type="file" />
            <button onClick={uploadCSV(file)}>Upload</button>
        </div>

    );
};
