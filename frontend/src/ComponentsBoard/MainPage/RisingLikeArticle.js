import React from 'react';
import './index.css';

import BoardListComBestLikes from './BoardListComBestLikes';
import { get } from '../../api';
import { addParams, pageRequest } from '../../utils';

export default function RisingLikeArticle(props) {
    const funcLoadData = (page) => {
        const size = 5;

        return get(addParams(
            `/api/v1/article/best/likes`, 
            pageRequest(page, size)
        ));
    };

    return (
        <div>
            <BoardListComBestLikes funcLoadData={funcLoadData}>
            </BoardListComBestLikes>
        </div>
    );
}