import React, { useState } from 'react';
import './index.css';

import { Link } from 'react-router-dom';

import * as u from '../../ComponentsUtils';
import IconTable from './IconTable'; 
import { toDate } from '../../utils';

export default function BoardListCom(props) {
    const loadData = (page) => {
        props.funcLoadData(page)
        .then(thenResponse)
        .catch(catchResponse);
    };

    const thenResponse = (res) => {
        setData(res.content);
        setNumTotalPages(res.totalPages);
    };
    const catchResponse = (err) => {
        console.log("loadData error");
    };

    const headEl = (row) => (
            <div className="container">
                <div className="row text-size-auto">
                    <div className="col-1"><strong>{row.createdAt}</strong></div>
                    <div className="col"><strong>{row.title}</strong></div>
                    <div className="col-2"><strong>{row.writer}</strong></div>
                    <div className="col-1"><strong>{row.views}</strong></div>
                </div>
            </div>
    );
    const rowEl = (row) => (
            <div className="container" key={row.id} >
                <div className='row'>
                    <div className="col-1">{toDate(row.createdAt)}</div>
                    <Link className="col no-deco" to={row.articleLink}>{row.title}</Link>
                    <Link className="col-2 no-deco" to={row.writerLink}>{row.nickname}</Link>
                    <div className="col-1">{row.views}</div>
                </div>
            </div>
    );
    const setDefault = (row) => {
        return {
            id: row.id,
            createdAt: row.createdAt ?? '1970-01-01',
            articleLink: row.id ? `/article/${row.id}` : '#',
            title: row.title ?? '[X]',
            writerLink: row.writer?.id ? `/user/${row.writer?.id}` : '#',
            nickname: row.writer?.nickname ?? '[X]',
            views: row.views ?? 0,
        };
    };
    
    const [data, setData] = useState([]);
    const [numTotalPages, setNumTotalPages] = useState(1);

    return (
        <div>
            <u.ListWithPageBar loadData={loadData} numTotalPages={numTotalPages} radius={2}>
                {headEl(IconTable)}
                {data.map(setDefault).map((item) => rowEl(item))}
            </u.ListWithPageBar>
        </div>
    );
}