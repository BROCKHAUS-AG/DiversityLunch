import { Dispatch } from '@reduxjs/toolkit';
import { authenticatedFetchPostCsv } from '../../utils/fetch.utils';
import { globalErrorSlice } from '../error/global-error-slice';
import { Voucher } from '../../types/Voucher';
import { VoucherAction } from './voucher-reducer';

export function getAllAccounts(data: FormData) {
    return async (dispatch: Dispatch) => {
        try {
            const response = await authenticatedFetchPostCsv('api/voucher/upload', data);

            if (!response.ok) {
                dispatch(globalErrorSlice.httpError({ statusCode: response.status }));
            } else {
                const result : Voucher[] = await response.formData();

                dispatch(VoucherAction.update(result));
                dispatch(VoucherAction.initFetch(undefined));
            }
        } catch (error) {
            dispatch(globalErrorSlice.error(undefined));
        }
    };
}
