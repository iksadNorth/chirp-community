import React from 'react';
import './index';

export default function CardWithThumbnail(props) {
    return (
        <div className="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
            <div className="col p-4 d-flex flex-column position-static">
                <strong className="d-inline-block mb-2 text-primary">조회수 최고 게시판</strong>
                <h3 className="mb-0">롤 게시판</h3>
                <div className="mb-1 text-muted">Nov 12</div>
                <p className="card-text mb-auto">몇 일간 조휘수 가장 많은 게시글 내용</p>
                <a href="#" className="stretched-link">Continue reading</a>
            </div>
            <div className="col-auto d-none d-lg-block">
                <svg className="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" 
                role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"
                >
                    <title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#55595c"></rect>
                    <text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text>
                </svg>
            </div>
        </div>
    );
}