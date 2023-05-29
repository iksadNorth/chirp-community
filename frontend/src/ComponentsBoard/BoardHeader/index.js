import { Link, useParams } from "react-router-dom";
import { del } from "../../api";


function BoardHeader(props) {

    const { '*': id } = useParams();
    console.log('BoardHeader props:', props);


    const handleDeleteArticle = async () => {
        // 게시글 삭제 API 호출
        await del(`/api/v1/article/${props.articleId}`);

        // 게시판 페이지로 이동
        window.location.href = `/board/${props.boardId}`;
    };


    return (
        <div className="ArticleHeader">
            {/* 게시판 게시글 관련 헤드 () */}
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container">
                    <a class="navbar-brand" href="#!">{props.boardName} </a>
                    <div>
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                            {props.onArticle === 'on' && props.udMode === 'on' && (
                                <li className="nav-item">
                                    <Link to={`/board/${props.boardId}/article/${id}/updateArticle`} className="nav-link active" aria-current="page">글 수정</Link>
                                </li>
                            )}

                            {props.onArticle === 'on' && props.udMode === 'on' && (

                                <li className="nav-item">
                                    <Link to={`/board/${props.boardId}`} className="nav-link active" aria-current="page" onClick={handleDeleteArticle}>글 삭제</Link>
                                </li>
                            )}

                            <li className="nav-item">
                                <Link to={`/board/${props.boardId}/createArticle`} className="nav-link active" aria-current="page">글쓰기</Link>
                            </li>

                        </ul>
                    </div>
                </div>
            </nav >
        </div >
    );
}

export default BoardHeader;




