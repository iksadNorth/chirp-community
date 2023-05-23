import './index.css';

import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { get } from '../../api';

import BoardHeader from '../BoardHeader';
import ArticleHeader from '../ArticleHeader';
import Comment from '../Comment';

let a = 0;

function ArticlePage() {

    const { '*': id } = useParams();
    const [article, setArticle] = useState({});
    const [writer, setwriter] = useState({});

    useEffect(() => {
        get(`/api/v1/article/${id}`)
            .then((data) => {
                setArticle(data);
                setwriter(data.writer);
                console.log('article_origin:', data);
            })
            .catch((err) => {
                console.log(err);
            })
    }, [])

    console.log('board_id :', id);
    console.log('article :', article);


    return (
        <div className="Article">

            <BoardHeader id={id} />

            {/* 게시글 페이지 */}
            <div class="container mt-5">
                <div class="row justify-content-center">
                    <div class="col-lg-8">

                        {/* 게시글 */}
                        <article>
                            {/* 게시글 헤더 : 제목, 작성일, 작성자 */}
                            <ArticleHeader article={article} writer={writer} />
                            {/* 게시글 내용 */}
                            <section class="mb-5">
                                <p class="fs-5 mb-4">{article.content}</p>
                            </section>
                        </article>

                        {/* 댓글 */}
                        <Comment />

                    </div>
                </div>
            </div>

        </div>
    );
}

export default ArticlePage;
