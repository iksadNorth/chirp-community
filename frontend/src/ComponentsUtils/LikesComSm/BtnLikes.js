import React from 'react';
import './css.css';

export default function BtnLikes({ flag, handlerClick }) {
    return (
        <div>
            {flag ? (
                <div className="text-success LikesComSm-btn" onClick={handlerClick}>
                    <i className="bi bi-hand-thumbs-up-fill"></i>
                </div>
            ) : (
                <div className="text-success LikesComSm-btn" onClick={handlerClick}>
                    <i className="bi bi-hand-thumbs-up"></i>
                </div>
            )}
        </div>
    );
}