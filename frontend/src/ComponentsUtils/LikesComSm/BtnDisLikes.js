import React from 'react';
import './css.css';

export default function BtnDisLikes({ flag, handlerClick }) {
    return (
        <div>
            {flag ? (
                <div className="text-danger LikesComSm-btn" onClick={handlerClick}>
                    <i className="bi bi-hand-thumbs-down-fill"></i>
                </div>
            ) : (
                <div className="text-danger LikesComSm-btn" onClick={handlerClick}>
                    <i className="bi bi-hand-thumbs-down"></i>
                </div>
            )}
        </div>
    );
}