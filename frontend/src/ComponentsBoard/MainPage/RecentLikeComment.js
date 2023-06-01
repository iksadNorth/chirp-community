import React from 'react';
import './index.css';

import RecentListCom from './RecentListCom';
import { get } from '../../api';
import { addParams, pageRequest } from '../../utils';

export default function RecentLikeComment(props) {
    const setDefault = (row) => {
        return {
            id: row.id,
            createdAt: row.createdAt ?? '1970-01-01',
            articleLink: row.id ? `/article/${row.id}` : '#',
            title: row.content ?? '[X]',
        };
    };

    const funcLoadData = (page) => {
        const size = 5;
        const sort_field = "createdAt";
        const sort_asc = false;

        return get(addParams(
            `/api/v1/article_comment`, 
            pageRequest(page, size, sort_field, sort_asc)
        ));
    };

    return (
        <div>
            <RecentListCom funcLoadData={funcLoadData} funcSetDefault={setDefault}/>
        </div>
    );
}