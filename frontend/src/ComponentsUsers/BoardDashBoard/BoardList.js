import React, { useState } from 'react';
import './css.css';
import * as u from '../../ComponentsUtils';
import { del, get, post } from '../../api';
import { adapterEvent, addParams, isNotBlank, pageRequest, toDate, hasSomethingInString } from '../../utils';
import { IconTable, DefaultValue } from './Config';
import { Link } from 'react-router-dom';

import ButtonSet from './ButtonSet';

export default function BoardList(props) {
    const loadData = (page, keyword) => {
        get(addParams(`/api/v1/board`, {
            ...pageRequest(page, 5, `name`),
            keyword: keyword,
        }))
        .then(res => {
            setData(res.content);
            setNumTotalPages(res.totalPages);
        })
        .catch(err => {
            console.log("loadData error");
            setError(err);
        });
    };
    const createRow = () => {
        if(!hasSomethingInString(newName)) { return ; }

        post(`/api/v1/board`, {
            body: JSON.stringify({
                name: newName,
            })
        })
        .then(res => {
            setUpdate(update + 1);
            setNewName('');
        })
        .catch(err => {
            console.log("deleteRow error");
            setError(err);
        });
    };
    const deleteRow = (id) => {
        if(!isNotBlank(id)) {
            return ;
        }

        del(`/api/v1/board/${id}`)
        .then(res => {
            setUpdate(update + 1);
        })
        .catch(err => {
            console.log("deleteRow error");
            setError(err);
        });
    };

    const headEl = (row) => (
            <div className="container">
                <div className="row text-size-auto">
                    <div className="col"><strong>{row.name}</strong></div>
                    <div className="col-2"><strong>{row.createdAt}</strong></div>
                    <div className="col-1"><strong>{row.delete}</strong></div>
                </div>
            </div>
    );
    const newRowEl = () => (
            <div className="container">
                <div className="row text-size-auto">
                    <u.RNW className="col" readonly={false} handlerChange={adapterEvent(setNewName)}/>
                    <div className="col-2">{DefaultValue.createdAt}</div>
                    <button className="col-1 btn btn-danger" type="button" disabled ></button>
                </div>
            </div>
    );
    const rowEl = (row) => (
            <div className="container" key={row.id} >
                <div className='row'>
                    <Link className="col no-deco" to={row.id ? `/article/${row.id}` : DefaultValue.url}>{row.name?? DefaultValue.name}</Link>
                    <div className="col-2">{toDate(row.createdAt )?? DefaultValue.createdAt}</div>
                    <button className="col-1 btn btn-danger" type="button" 
                        onClick={() => {deleteRow(row.id);}}
                    ></button>
                </div>
            </div>
    );

    const [data, setData] = useState([
        {
            id: 1,
            board: "게시판",
            title: "제목",
            createdAt: "2023-05-23",
            numLikes: 10,
            numComments: 100,
        }
    ]);
    const [numTotalPages, setNumTotalPages] = useState(1);

    const [update, setUpdate] = useState(1);

    const [error, setError] = useState('');

    const [flag, setFlag] = useState(true);
    const [newName, setNewName] = useState('');

    return (
        <div>
            <div className='d-flex justify-content-start'>
                <ButtonSet createData={createRow} flag={flag} setFlag={setFlag}/>
            </div>
            <u.List
                loadData={loadData} update={update}
                numTotalPages={numTotalPages} radius={3}
            >
                {headEl(IconTable)}
                {!flag && newRowEl()}
                {data.map(rowEl)}
            </u.List>
            <u.Toast title="에러 발생" body={error}/>
        </div>
    );
}