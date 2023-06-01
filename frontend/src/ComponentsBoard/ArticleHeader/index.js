import { Link } from 'react-router-dom';
import './index.css';

function ArticleHeader(props) {

    console.log('ArticleHeader Props:', props);

    return (
        <div className="ArticleHeader">
            <header class="mb-4">
                {/* 게시글 제목 */}

                <h1 class="fw-bolder mb-1">{props.title}</h1>
                {/* 작성일, 작성자 */}
                <div class="d-flex justify-content-start flex-direction: column-reverse">
                    <div class="text-muted fst-italic mb-2">작성일 : {props.createdAt}</div>
                </div>
                <div class="d-flex justify-content-start flex-direction: column-reverse text-muted fst-italic mb-2">
                    <Link to={`/user/${props.writer.id}`} className='nav-link active'>작성자 : {props.writer.nickname}</Link>
                </div>
            </header>
        </div>
    );
}

export default ArticleHeader;
