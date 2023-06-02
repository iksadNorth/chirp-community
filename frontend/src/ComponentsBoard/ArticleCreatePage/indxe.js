import './index.css';

import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';

import { get, post, patch } from '../../api';
import { isNotBlank, getToken } from "../../utils";

import BoardHeader from '../BoardHeader';




function ArticleCreatePage(props) {

    const [boardName, setBoardName] = useState('');
    const [title, settitle] = useState('');
    const [content, setContent] = useState('');
    const [writer, setwriter] = useState('');
    const { boardId: boardId, articleId: articleId } = useParams();
    let mode = 'create';
    if (articleId != null) {
        mode = 'update';
    }

    console.log('boardId:', boardId);
    console.log('articleId:', articleId);
    console.log('mode:', mode);

    const loadUserInfo = () => {
        if (!isNotBlank(getToken())) { return; }
        get(`/api/v1/user/me`)
            .then((res) => {
                setwriter(res.nickname);
                console.log('res:', res);
                console.log('writer:', res.nickname);
            })
            .catch((err) => {
                console.log("loadUserInfo Error");
            })
    };

    useEffect(() => {
        loadUserInfo();
        get(`/api/v1/board/${boardId}`)
            .then((res) => {
                console.log('board res:', res);
                setBoardName(res.name);
            })
            .catch((err) => {
                console.log(err);
            })
        if (mode === 'update') {
            get(`/api/v1/article/${articleId}`)
                .then((res) => {
                    console.log('Update, article:', res);
                    settitle(res.title);
                    setContent(res.content);
                })
                .catch((err) => {
                    console.log('err:', err);
                })
        }
    }, [])




    return (
        <div className="ArticleCreatePage">

            <BoardHeader boardName={boardName} mode='off' />

            <div class="container mt-5">
                <div class="row justify-content-center">
                    <div class="col-lg-8">

                        {/* 게시글 */}
                        <article>
                            {/* 게시글 헤더 : 제목, 작성일, 작성자 */}
                            <div className="ArticleHeader">
                                <header class="mb-4">
                                    {/* 게시글 제목 */}
                                    <h1 class="fw-bolder mb-1" style={{ display: 'flex' }}>
                                        <textarea class="fs-5 mb-4" style={{ width: '100%' }} placeholder='제목' onChange={(e) => settitle(e.target.value)}
                                            value={mode === "update" ? title : null}>
                                        </textarea>
                                    </h1>
                                    {/* 작성일, 작성자 */}
                                    <div class="d-flex justify-content-start flex-direction: column-reverse">
                                        <div class="text-muted fst-italic mb-2">작성일</div>
                                    </div>
                                    <div class="d-flex justify-content-start flex-direction: column-reverse text-muted fst-italic mb-2">
                                        작성자 : {writer}
                                        {/* 작성자 : '{writer}' */}
                                    </div>
                                </header>
                            </div>

                            {/* 게시글 내용 */}
                            < div className="mb-5" style={{ display: 'flex', height: '400px' }}>
                                <textarea class="fs-5 mb-4" style={{ width: '100%', overflow: 'scroll' }} placeholder='본문' onChange={(e) => setContent(e.target.value)}
                                    value={mode === "update" ? content : null}
                                ></textarea>
                            </div>

                        </article>


                        <button className="btn btn-secondary d-flex flex-row-reverse" onClick={(event) => {
                            event.preventDefault();
                            if (mode === 'update') {
                                patch(`/api/v1/article/${articleId}`, {
                                    body: JSON.stringify({
                                        id: articleId,
                                        title: title,
                                        content: content
                                    })
                                })
                                    .then((res) => {
                                        console.log('patch res:', res);
                                    })
                                    .catch((err) => {
                                        console.log('patch err:', err);
                                    })
                            
                            } else {
                                post(`/api/v1/article`, {
                                    body: JSON.stringify({
                                        title: title,
                                        content: content,
                                        board_id: boardId
                                    })
                                })
                                    .then((res) => {
                                        console.log(res);
                                    })
                                    .catch((err) => {
                                        console.log('에러발생 :', err);
                                    })
                            }
                        }}>
                            <Link to={`/board/${boardId}`} className="nav-link active" aria-current="page">{mode === 'create' ? '생성' : '수정'}</Link>
                        </button>

                    </div>
                </div>
            </div >


        </div >
    );
}

export default ArticleCreatePage;
