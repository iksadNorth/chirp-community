import React, { useState, useEffect } from "react";
import './css.css';

import { get } from "../../api";
import { pageRequest, addParams } from '../../utils';
import { Link } from "react-router-dom";

export default function Header() {
    const [boards, setBoards] = useState([]);

    // 데이터 로드 후.
    useEffect(() => {
        get(addParams('/api/v1/board', pageRequest(0, 10)))
            .then(res => {
                console.log(res.content);
                setBoards(res.content);
            })
            .catch(err => {
                console.log("initLoad error");
            });
    }, [])
    
    return (
    <header>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
        <div className="container">

            <Link className="navbar-brand" to="/">
                <img 
                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTjNBOHJqRMYnlA7ExBs3gqteoucTcwXZtuhA&usqp=CAU"
                    alt="Icon Of Chirp Community"
                    height="50"
                    className="img-thumnails"
                />
            </Link>

            <ul className="navbar-nav">
                <li className="nav-item">
                    <Link className="nav-link" to="/login">Login</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/mypage">MyPage</Link>
                </li>
            </ul>

        </div>

        </nav>

        <div className="container d-flex flex-row my-3">

            {boards.map((item) => 
                <div key={item.id}>
                    <Link
                        className="px-3 fw-light menu-over black-link"
                        to={`/board/${item.id}`}
                    >{item.name}</Link>
                </div>
            )}

        </div>

        <hr />
    </header>
    );
}
