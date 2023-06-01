import './index.css';

import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { get } from '../../api';

import { useSelector } from "react-redux";


import BoardHeader from '../BoardHeader';
import ArticleHeader from '../ArticleHeader';
import Comment from '../Comment';

import * as u from '../../ComponentsUtils';

function ArticlePage() {

    const { '*': id } = useParams();
    const [article, setArticle] = useState({});
    const [writer, setwriter] = useState({});
    const [boardId, setBoardId] = useState(0);
    const [boardName, setBoardName] = useState('');

    const nickname = useSelector(state => state.AuthReducer.nickname);
    const userId = useSelector(state => state.AuthReducer.id);

    console.log('userId:', userId);
    


    useEffect(() => {
        get(`/api/v1/article/${id}`)
            .then((data) => {
                setArticle(data);
                setwriter(data.writer);
                setBoardId(data.board.id)
                setBoardName(data.board.name);
                console.log('article_origin:', data);
                console.log('data.board:', data.board);
            })
            .catch((err) => {
                console.log(err);
            })
    }, [])


    console.log('article :', article);
    console.log('BoardName:', boardName)

    console.log('userId ??   article.id:', userId,  writer.id);


    return (
        <div className="Article">

            <BoardHeader boardId={boardId} boardName={boardName} onArticle='on' articleId={id} udMode={userId === writer.id ? 'on' : 'off'} />

            {/* 게시글 페이지 */}
            <div class="container mt-5">
                <div class="row justify-content-center">
                    <div class="col-lg-8">

                        {/* 게시글 */}
                        <article>
                            {/* 게시글 헤더 : 제목, 작성일, 작성자 */}
                            <ArticleHeader title={article.title} createdAt={article.createdAt} writer={writer} />
                            {/* 게시글 내용 */}
                            <section class="mb-5">
                                <p class="fs-5 mb-4">{article.content}</p>
                            </section>
                        </article>
                        
                        <div className="d-flex justify-content-center">
                            <u.LikesCom id={id} />    
                        </div>

                        

                        {/* 댓글 */}
                        <Comment article_id={id}/>

                    </div>
                </div>
            </div>

        </div>
    );
}

export default ArticlePage;
