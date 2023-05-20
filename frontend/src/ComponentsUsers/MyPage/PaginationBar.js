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

    const [update, doUpdate] = useState(0);

    useEffect(() => {
        const cursorCalculated = limitedCursor(cursor, first_index, last_index);
        if(cursor != cursorCalculated) {
            setCursor(cursorCalculated);
        } else {
            const start = limitedRange(cursor, first_index, last_index, radius);
            const arr = [...Array(2*radius+1)]
                                    .map((_, index) => (index + start))
                                    .filter(page => first_index <= page && page <= last_index);
            setIndexes(arr);

            handlePage(cursor);
        }
    }, [cursor])

    useEffect(() => {
        handlePage(cursor);
    }, [update]);

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