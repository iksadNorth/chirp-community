


function BoardHeader(props) {

    console.log('ArticleHeader :', props.id);

    return (
        <div className="ArticleHeader">
            {/* 게시판 게시글 관련 헤드 () */}
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container">
                    <a class="navbar-brand" href="#!">게시판 id : {props.id} </a>
                    <div>
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                            <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">글쓰기</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    );
}

export default BoardHeader;




