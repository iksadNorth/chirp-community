import React, { useState } from "react";
import './css.css';

import { Link } from 'react-router-dom';

import * as c from '../../ComponentsUtils';
import List from "../../ComponentsUtils/List/List";
import { del, get } from "../../api";
import { addParams, pageRequest, toDate } from "../../utils";

import TitleWithIcon from "../../ComponentsUtils/TitleWithIcon";

export default function CommentList(props) {
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
                <div className="row">
                    <div className="col-3"><strong>{row.board}</strong></div>
                    <div className="col"><strong>{row.content}</strong></div>
                    <div className="col-2"><strong>{row.createdAt}</strong></div>
                    <div className="col-1"><strong>{row.numLikes}</strong></div>
                    <div className="col-1"><strong><i class="bi bi-trash3"></i></strong></div>
                </div>
            </div>
    );
    const rowEl = (row) => (
            <div className="container">
                <div className="row no-deco">
                    <div className="col-3">{row.board ?? '[X]'}</div>
                    <Link className="col no-deco" to={row.articleId ? `/article/${row.articleId}` : '#'}>{row.content ?? '[X]'}</Link>
                    <div className="col-2">{toDate(row.createdAt )?? '[X]'}</div>
                    <div className="col-1">{row.numLikes ?? 0}</div>
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

    const loadData = () => {
        const sample = [
            {
                id: 1,
                articleId: 1,
                content: "제목1",
                board: "게시판1",
                createdAt: "2023-01-01",
                numLikes: "14",
            },
            {
                id: 2,
                articleId: 2,
                content: "제목2",
                board: "게시판2",
                createdAt: "2023-01-01",
                numLikes: "15",
            },
        ];
        setData(sample);

        // const size = 5;
        // const sort_field = "createdAt";
        // const sort_asc = true;

        // get(addParams(`/api/v1/user/me/comment`, pageRequest(page, size, sort_field, sort_asc)))
        // .then((res) => {
        //     setData(res.content);
        //     setNumTotalPages(res.totalPages);
        // })
        // .catch(err => {
        //     console.log("loadData error");
        //     popToast(err);
        // });
    };

    const deleteRow = (id) => {
        del(`/api/v1/comment/${id}`)
        .then(() => {
            doUpdate(update + 1);
        })
        .catch(err => {
            console.log("deleteRow error");
            popToast(err);
        });
    };
    const [iconName, headTitle] = ["bi-chat-dots", "내가 쓴 댓글"];

    const [data, setData] = useState([]);
    const [messageToast, popToast] = useState(null);

    const [numTotalPages, setNumTotalPages] = useState(1);
    const [update, doUpdate] = useState(0);
    
    return (
        <c.Sheet className={`py-5 container ${props.className}`}>
            <div className="row">
                <div className="col">
                    <TitleWithIcon iconName={iconName} head={headTitle} />
                </div>
            </div>
            <div className="row">
                <div className="col">
                    <List loadData={loadData} numTotalPages={numTotalPages} radius={3} update={update}>
                        {headEl(head)}
                        {data.map((row) => rowEl(row))}
                    </List>
                </div>
            </div>
            <c.Toast title="에러 발생" body={messageToast} />
        </c.Sheet>
    );
}
