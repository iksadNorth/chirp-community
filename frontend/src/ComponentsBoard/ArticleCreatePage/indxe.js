import './index.css';

import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';

import { get, post } from '../../api';
import { isNotBlank, getToken } from "../../utils";

import BoardHeader from '../BoardHeader';




function ArticleCreatePage() {

    const { '*': id } = useParams();

    const [boardId, setBoardId] = useState(0);
    const [boardName, setBoardName] = useState('');
    const [title, settitle] = useState('Title Test');
    const [content, setContent] = useState('');
    const [writer, setwriter] = useState();

    const loadUserInfo = () => {
        if(!isNotBlank(getToken())) { return ; }
        
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
        get(`/api/v1/board/${id}`)
            .then((res) => {
                setBoardId(res.id);
                setBoardName(res.name);
            })
            .then(() => {
                loadUserInfo();
            })
            .catch((err) => {
                console.log('err:', err);
            })
            
    })

    

       

    return (
        <div className="ArticleCreatePage">

            <BoardHeader boardName={boardName} mode='off'/>

            <div class="container mt-5">
                <div class="row justify-content-center">
                    <div class="col-lg-8">

                        {/* 게시글 */}
                        <article>
                            {/* 게시글 헤더 : 제목, 작성일, 작성자 */}
                            <div className="ArticleHeader">
                                <header class="mb-4">
                                    {/* 게시글 제목 */}
                                    <h1 class="fw-bolder mb-1" style={{ display: 'flex'}}>
                                        <input class="fs-5 mb-4" style={{ width: '100%'}} placeholder='제목' onChange={(e) => settitle(e.target.value)}></input>
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
                            <div class="mb-5"  style={{ display: 'flex', height: '400px' }}>
                                <textarea class="fs-5 mb-4" style={{ width: '100%', overflow: 'scroll' }}  placeholder='본문' onChange={(e) => setContent(e.target.value)}></textarea>
                            </div>
                        </article>


                        <button className="d-flex flex-row-reverse" onClick={(event) => {
                            event.preventDefault();
                            post('/api/v1/article', {
                                body: JSON.stringify({
                                    title: title,
                                    content: content,
                                    board_id: id
                                })
                            })
                                .then((res) => {
                                    console.log(res);
                                })
                                .catch((err) => {
                                    console.log('에러발생 :', err);
                                })
                        }}>
                            <Link to={`/board/${boardId}`} class="nav-link active" aria-current="page" href="#" > 생성</Link>
                        </button>

                    </div>
                </div>
            </div>


        </div>
    );
}

export default ArticleCreatePage;
