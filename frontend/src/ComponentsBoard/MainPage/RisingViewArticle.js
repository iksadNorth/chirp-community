import React from 'react';
import './index.css';

import BoardListComBestViews from './BoardListComBestViews';
import { get } from '../../api';
import { addParams, pageRequest } from '../../utils';

export default function RisingViewArticle(props) {
    const funcLoadData = (page) => {
        const size = 5;
        const sort_field = "createdAt";
        const sort_asc = false;

        return get(addParams(
            `/api/v1/article/best/views`, 
            pageRequest(page, size, sort_field, sort_asc)
        ));
    };

    return (
        <div>
            <BoardListComBestViews funcLoadData={funcLoadData}>
            </BoardListComBestViews>
        </div>
    );
}