import React, { useState } from "react";
import './css.css';

import { Link } from 'react-router-dom';

import * as c from '../../ComponentsUtils';
import List from "../../ComponentsUtils/List/List";
import { del, get } from "../../api";
import { addParams, pageRequest } from "../../utils";

import TitleWithIcon from './TitleWithIcon';

export default function ArticleList(props) {
    const head = {
        title: (<i class="bi bi-postcard"></i>),
        content: (<i class="bi bi-postcard"></i>),
        board: (<i class="bi bi-archive"></i>),
        createdAt: (<i class="bi bi-calendar-date"></i>),
        numLikes: (<i class="bi bi-hand-thumbs-up"></i>),
        numComments: (<i class="bi bi-chat-dots"></i>),
    };

    const headEl = (row) => (
            <div className="container">
                <div className="row text-size-auto">
                    <div className="col-2"><strong>{row.board}</strong></div>
                    <div className="col"><strong>{row.title}</strong></div>
                    <div className="col-2"><strong>{row.createdAt}</strong></div>
                    <div className="col-1"><strong>{row.numLikes}</strong></div>
                    <div className="col-1"><strong>{row.numComments}</strong></div>
                    <div className="col-1"><strong><i class="bi bi-trash3"></i></strong></div>
                </div>
            </div>
    );
    const rowEl = (row) => (
            <div className="container">
                <div className='row'>
                    <div className="col-2">{row.board ?? "[X]"}</div>
                    <Link className="col no-deco" to={row.id ? `/article/${row.id}` : '#'}>{row.title?? "[X]"}</Link>
                    <div className="col-2">{row.createdAt ?? "[X]"}</div>
                    <div className="col-1">{row.numLikes ?? 0}</div>
                    <div className="col-1">{row.numComments ?? 0}</div>
                    <button type="button" className="col-1 btn btn-danger"
                        onClick={() => {
                            if(row.id) {
                                deleteRow(row.id);
                            }
                        }}
                    ></button>
                </div>
            </div>
    );

    const loadData = (page, keyword) => {
        // const sample = [
        //     {
        //         id: 1,
        //         title: "제목1",
        //         board: "게시판1",
        //         createdAt: "날짜1",
        //         numLikes: "50",
        //         numComments: "10",
        //     },
        //     {
        //         id: 2,
        //         title: "제목2",
        //         board: "게시판2",
        //         createdAt: "날짜2",
        //         numLikes: "75",
        //         numComments: "34",
        //     },
        // ];
        // setData(sample);

        const size = 5;
        const sort_field = "createdAt";
        const sort_asc = true;

        get(addParams(`/api/v1/user/me/article`, {
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
    const [iconName, headTitle] = ["bi-signpost-2-fill", "내가 쓴 글"];

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
