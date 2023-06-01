import React from 'react';
import './index.css';

import BoardListCom from './BoardListCom';
import { get } from '../../api';
import { addParams, pageRequest } from '../../utils';

export default function RisingLikeArticle(props) {
    const funcLoadData = (page) => {
        const size = 5;
        const sort_field = "createdAt";
        const sort_asc = false;

        return get(addParams(
            `/api/v1/article/best/likes`, 
            pageRequest(page, size, sort_field, sort_asc)
        ));
    };

    return (
        <div>
            <BoardListCom funcLoadData={funcLoadData}>
            </BoardListCom>
        </div>
    );
}