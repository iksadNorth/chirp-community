import React, { useEffect, useState } from 'react';
import './index.css';

import { Link } from 'react-router-dom';

import * as u from '../../ComponentsUtils';
import { toDate } from '../../utils';

export default function RecentListCom(props) {
    const loadData = (page) => {
        props.funcLoadData(page)
        .then(thenResponse)
        .catch(catchResponse);
    };

    const thenResponse = (res) => {
        setData(res.content);
    };
    const catchResponse = (err) => {
        console.log("loadData error");
    };

    const rowEl = (row) => (
            <div className="container" key={row.id} >
                <div className='row'>
                    <Link className="col no-deco" to={row.articleLink}>{row.title}</Link>
                    <div className="col-1">{toDate(row.createdAt)}</div>
                </div>
            </div>
    );
    // const setDefault = (row) => {
    //     return {
    //         id: row.id,
    //         createdAt: row.createdAt ?? '1970-01-01',
    //         articleLink: row.id ? `/article/${row.id}` : '#',
    //         title: row.title ?? '[X]',
    //     };
    // };
    
    const [data, setData] = useState([]);

    useEffect(() => {
        loadData(0);
    }, []);

    return (
        <div>
            <u.ListCom>
                {data.map(props.funcSetDefault).map((item) => rowEl(item))}
            </u.ListCom>
        </div>
    );
}