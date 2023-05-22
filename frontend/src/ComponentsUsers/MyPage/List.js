import Page from './PaginationBar';
import SearchInput from './SearchInput';
import ListCom from './ListCom';
import { useEffect, useState } from 'react';

export default function List(props) {
    const [page, setPage] = useState(0);
    const [keyword, SetKeyword] = useState('');

    useEffect(() => {
        props.loadData(page, keyword);
        console.log(keyword)
    }, [page, keyword, props.update]);
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
                    handlePage={setPage}
                    radius={props.radius}
                />
            </div>
            <div className='row'>
                {/* 검색창 */}
                <SearchInput handleSearch={SetKeyword} />
            </div>
        </div>
    );
}