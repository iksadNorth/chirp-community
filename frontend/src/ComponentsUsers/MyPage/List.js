import Page from './PaginationBar';
import SearchInput from './SearchInput';
import ListCom from './ListCom';

export default function List(props) {
    return (
        <div className={`container ${props.className}`}>
            <div className='row'>
                {/* 게시글 목록 */}
                <ListCom>
                    {props.children}
                </ListCom>
            </div>
            <div className='row'>
                {/* 페이지네이션 */}
                <Page className="mb-3"
                    numTotalPages={props.numTotalPages}
                    handlePage={props.handlePage}
                    radius={props.radius}
                    update={props.update}
                />
            </div>
            <div className='row'>
                {/* 검색창 */}
                <SearchInput handleSearch={props.handleSearch} />
            </div>
        </div>
    );
}