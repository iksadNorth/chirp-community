import React, { useState, useEffect } from "react";

const limitedCursor = (cur, minVal, maxVal) => {
    return (cur < minVal) ? minVal : (cur > maxVal ? maxVal: cur);
}

const limitedRange = (cur, minVal, maxVal, r) => {
    const focus = limitedCursor(cur, minVal + r, maxVal - r);
    return focus - r;
}

export default function Page(props) {
    const numTotalPages = +props.numTotalPages;
    const radius = +props.radius;
    const handlePage = props.handlePage;

    const first_index = 0;
    const last_index = numTotalPages - 1;
    
    const [cursor, setCursor] = useState(0);
    const [indexes, setIndexes] = useState([]);

    const calculateArray = () => {
        const start = limitedRange(cursor, first_index, last_index, radius);
        const arr = [...Array(2*radius+1)]
                                .map((_, index) => (index + start))
                                .filter(page => first_index <= page && page <= last_index);
        setIndexes(arr);
    }

    useEffect(() => {
        const cursorInRange = limitedCursor(cursor, first_index, last_index);
        if(cursor != cursorInRange) {
            setCursor(cursorInRange);
        } else {
            calculateArray();

            handlePage(cursor);
        }
    }, [cursor])
    // handlePage에 의해 날라간 쿼리는 비동기 함수여서 늦게 갱신된다.
    // 해당 쿼리는 numTotalPages에 대한 정보를 담고 있다.
    // 즉, numTotalPages에 대한 정보는 늦게 갱신된다.
    // 이것 때문에 초기 indexes은 계산이 되지 않는 상황이 발생하고 계속 1페이지만 대기열에 놓게 된다.
    // 이런 현상을 막기 위해 아래와 같은 Line을 추가했다.
    useEffect(calculateArray, [props]);

    return (
        <ul className={`pagination d-flex justify-content-center ${props.className}`}>

            {/* 첫 페이지 버튼 */}
            <li className="page-item">
                <button 
                    className="page-link" 
                    onClick={() => {setCursor(first_index);}}
                >
                    <span aria-hidden="true">&laquo;</span>
                </button>
            </li>

            {/* 번호 버튼 */}
            <div className="d-flex flex-row">
                {indexes.map((idx, index) => (
                    <li className="page-item" key={index}>
                        <button 
                            className={`page-link ${cursor==idx ? "active" : ""}`} 
                            onClick={() => {setCursor(idx);}}
                        >{idx + 1}</button>
                    </li>
                ))}
            </div>

            {/* 마지막 페이지 버튼 */}
            <li className="page-item">
                <button className="page-link" 
                    onClick={() => {setCursor(last_index);}}
                >
                    <span aria-hidden="true">&raquo;</span>
                </button>
            </li>
        </ul>
    );
}