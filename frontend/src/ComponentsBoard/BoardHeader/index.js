import { Link, useParams } from "react-router-dom";
import { del } from "../../api";


function BoardHeader(props) {

    const { '*': id } = useParams();

    console.log('BoardHeader props:', props);

    return (
        <div className="ArticleHeader">
            {/* 게시판 게시글 관련 헤드 () */}
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container">
                    <a class="navbar-brand" href="#!">{props.boardName} </a>
                    <div>
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                            {props.onArticle === 'on' && props.mode === 'on' && (
                                <li className="nav-item">
                                    <Link to={`/create/article/${props.boardId}`} className="nav-link active" aria-current="page">글 수정</Link>
                                </li>
                            )}
                            {props.onArticle === 'on' && props.mode === 'on' && (

                                <li className="nav-item">
                                <Link to={`/board/${props.boardId}`} className="nav-link active" aria-current="page" 
                                >글 삭제</Link>
                            </li>
                            )}

                            <li className="nav-item">
                                <Link to={`/create/article/${props.boardId}`} className="nav-link active" aria-current="page">글쓰기</Link>
                            </li>

                        </ul>
                    </div>
                </div>
            </nav >
        </div >
    );
}

export default BoardHeader;




