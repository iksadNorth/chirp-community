import { Link, useParams } from "react-router-dom";



function BoardHeader(props) {

    const { '*': id } = useParams();

    console.log(props.mode);

    return (
        <div className="ArticleHeader">
            {/* 게시판 게시글 관련 헤드 () */}
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container">
                    <a class="navbar-brand" href="#!">{props.boardName} </a>
                    <div>
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                            {props.mode === 'on' && (
                                < li class="nav-item">
                                    <Link to={`/create/article/${props.boardId}`} class="nav-link active" aria-current="page" href="#">글쓰기</Link>
                                </li>
                            )}
                        </ul>
                    </div>
                </div>
            </nav >
        </div >
    );
}

export default BoardHeader;




