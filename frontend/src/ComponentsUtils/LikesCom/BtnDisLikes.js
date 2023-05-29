import React from 'react';
import './css.css';

export default function BtnDisLikes({ flag, handlerClick }) {
    return (
        <div>
            {flag ? (
                <button type="button" className="btn btn-danger" onClick={handlerClick}>
                    <i className="bi bi-hand-thumbs-down"></i>
                </button>
            ) : (
                <button type="button" className="btn btn-outline-danger" onClick={handlerClick}>
                    <i className="bi bi-hand-thumbs-down"></i>
                </button>
            )}
        </div>
    );
}