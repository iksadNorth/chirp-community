import React, { useState } from 'react';
import './css.css';
import * as u from '../../ComponentsUtils';
import { del, get, patch, post } from '../../api';
import { adapterEvent, addParams, isNotBlank, pageRequest, toDate, hasSomethingInString } from '../../utils';
import { IconTable, DefaultValue } from './Config';

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
    const updateRow = (id, oldVal, newVal) => {
        if(newVal == oldVal) { return ; }

        patch(`/api/v1/board/${id}`, {
            body: JSON.stringify({
                name: newVal,
            })
        })
        .then(res => {
            setUpdate(update + 1);
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
                    <div className="col-1"><strong>{row.change}</strong></div>
                    <div className="col-1"><strong>{row.cancel}</strong></div>
                </div>
            </div>
    );
    const newRowEl = () => (
            <div className="container">
                <div className="row text-size-auto">
                    <u.RNW className="col" readonly={false} handlerChange={adapterEvent(setNewName)}/>
                    <div className="col-2">{DefaultValue.createdAt}</div>
                    <button className="col-1 btn btn-danger" type="button" disabled ></button>
                    <button className="col-1 btn btn-info" type="button" disabled ></button>
                    <button className="col-1 btn btn-secondary" type="button" disabled ></button>
                </div>
            </div>
    );
    const RowEl = ({ row }) => {
        const [text, setText] = useState(row.name ?? DefaultValue.name);
        const [readMode, filp] = useState(true);

        const clickUpdate = () => {
            if(!readMode) {
                updateRow(row.id, row.name, text);
            }
            filp(!readMode);
            setText(row.name);
        };
        const clickCancel = () => {
            filp(true);
            setText(row.name);
        };

        return (
            <div className="container" key={row.id} >>
                <div className='row'>
                    <u.RNW className="col" readonly={readMode} handlerChange={adapterEvent(setText)} value={text}/>
                    <div className="col-2">{toDate(row.createdAt )?? DefaultValue.createdAt}</div>
                    <button className="col-1 btn btn-danger" type="button" 
                        onClick={() => {deleteRow(row.id);}}
                    ></button>
                    <button className={`col-1 btn ${readMode ? "btn-info" : "btn-success"}`} type="button" onClick={clickUpdate}></button>
                    <button className="col-1 btn btn-secondary" type="button" disabled={readMode} onClick={clickCancel}></button>
                </div>
            </div>
    );
    }

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
                {data.map(item => <RowEl row={item} />)}
            </u.List>
            <u.Toast title="에러 발생" body={error}/>
        </div>
    );
}