import React, { useState } from "react";
import './css.css';

import { Link } from 'react-router-dom';

import * as c from '../../ComponentsUtils';
import List from "../../ComponentsUtils/List/List";
import { del, get } from "../../api";
import { addParams, pageRequest, toDate } from "../../utils";

import TitleWithIcon from '../../ComponentsUtils/TitleWithIcon';

export default function ArticleList(props) {
    const head = {
        title: (<i className="bi bi-postcard"></i>),
        content: (<i className="bi bi-postcard"></i>),
        board: (<i className="bi bi-archive"></i>),
        createdAt: (<i className="bi bi-calendar-date"></i>),
        numLikes: (<i className="bi bi-hand-thumbs-up"></i>),
        numComments: (<i className="bi bi-chat-dots"></i>),
    };

    const headEl = (row) => (
            <div className="container">
                <div className="row text-size-auto">
                    <div className="col-2"><strong>{row.board}</strong></div>
                    <div className="col"><strong>{row.title}</strong></div>
                    <div className="col-2"><strong>{row.createdAt}</strong></div>
                    <div className="col-1"><strong>{row.numLikes}</strong></div>
                    <div className="col-1"><strong>{row.numComments}</strong></div>
                    {props.readonly ? null : <div className="col-1"><strong><i className="bi bi-trash3"></i></strong></div>}
                </div>
            </div>
    );
    const rowEl = (row) => (
            <div className="container" key={row.id} >
                <div className='row'>
                    <Link className="col-2 no-deco" to={row.board.id ? `/board/${row.board.id}` : '#'}>{row.board.name ?? "[X]"}</Link>
                    <Link className="col no-deco" to={row.id ? `/article/${row.id}` : '#'}>{row.title ?? "[X]"}</Link>
                    <div className="col-2">{toDate(row.createdAt) ?? "[X]"}</div>
                    <div className="col-1">{row.numLikes ?? 0}</div>
                    <div className="col-1">{row.numComments ?? 0}</div>
                    {props.readonly ? null : <button type="button" className="col-1 btn btn-danger"
                        onClick={() => {
                            if(row.id) {
                                deleteRow(row.id);
                            }
                        }}
                    ></button>}
                </div>
            </div>
    );

    const loadData = (page, keyword) => {
        const size = 5;
        const sort_field = "createdAt";
        const sort_asc = true;

        get(addParams(`/api/v1/user/${userId}/article`, {
            ...pageRequest(page, size, sort_field, sort_asc),
            keyword: keyword,
        }))
        .then((res) => {
            setData(res.content);
            setNumTotalPages(res.totalPages);
        })
        .catch(err => {
            console.log("loadData error");
            popToast(err);
        });
    };

    const deleteRow = (id) => {
        del(`/api/v1/article/${id}`)
        .then(() => {
            doUpdate(update + 1);
        })
        .catch(err => {
            console.log("deleteRow error");
            popToast(err);
        });
    };
    const userId = props.userId ?? "me";
    const userName = props.userName ?? "[익명의 유저]";

    const [iconName, headTitle] = ["bi-signpost-2-fill", `${userName}가 쓴 글`];

    const [data, setData] = useState([]);
    const [messageToast, popToast] = useState(null);

    const [numTotalPages, setNumTotalPages] = useState(1);
    const [update, doUpdate] = useState(0);

    return (
        <c.Sheet className={`py-5 ${props.className}`}>            
            <div className="row">
                <div className="col">
                <TitleWithIcon iconName={iconName} head={headTitle} />
                </div>
            </div>
            <div className="row">
                <div className="col">
                    <List loadData={loadData} numTotalPages={numTotalPages} radius={3} update={update}>
                        {headEl(head)}
                        {data.map((item) => rowEl(item))}
                    </List>
                </div>
            </div>
            <c.Toast title="에러 발생" body={messageToast} />
        </c.Sheet>
    );
}
