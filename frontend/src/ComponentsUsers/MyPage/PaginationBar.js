import React, { useState, useEffect } from "react";

const limitedCursor = (cur, minVal, maxVal) => {
    return (cur < minVal) ? minVal : (cur > maxVal ? maxVal: cur);
}

const limitedRange = (cur, minVal, maxVal, r) => {
    const L = 2*r+1;
    let start;
    if(maxVal < cur + r) {
        start = (maxVal + 1) - L ;
    } else if(cur - r < minVal) {
        start = minVal;
    } else {
        start = cur - r;
    }
    return start;
}

export default function Page(props) {
    const numTotalPages = +props.numTotalPages;
    const numPage = +props.numPage;
    const radius = +props.radius;
    const handlePage = props.handlePage;

    const first_index = 0;
    const last_index = numTotalPages - 1;
    
    const [cursor, setCursor] = useState(limitedCursor(numPage, first_index, last_index));
    const [indexes, setIndexes] = useState([]);


    useEffect(() => {
        const cursorCalculated = limitedRange(cursor, first_index, last_index);
        if(cursor != cursorCalculated) {
            setCursor(cursorCalculated);
        } else {
            const start = limitedRange(cursor, first_index, last_index, radius);
            const arr = [...Array(2*radius+1)].map((_, index) => (index + start));
            setIndexes(arr);

            handlePage(cursor);
        }
    }, [cursor])

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
            <li className="d-flex flex-row">
                {indexes.map((idx, index) => (
                    <li className="page-item" key={index}>
                        <button 
                            className={`page-link ${cursor==idx ? "active" : ""}`} 
                            onClick={() => {setCursor(idx);}}
                        >{idx + 1}</button>
                    </li>
                ))}
            </li>

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