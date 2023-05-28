import React from 'react';
import './css.css';

export default function BtnLikes({ flag, handlerClick }) {
    return (
        <div>
            {flag ? (
                <button type="button" className="btn btn-success" onClick={handlerClick}>
                    <i className="bi bi-hand-thumbs-up"></i>
                </button>
            ) : (
                <button type="button" className="btn btn-outline-success" onClick={handlerClick}>
                    <i className="bi bi-hand-thumbs-up"></i>
                </button>
            )}
        </div>
    );
}