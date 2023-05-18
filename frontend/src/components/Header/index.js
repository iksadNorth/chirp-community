import React from "react";
import 'bootstrap/dist/css/bootstrap.css';
import './css.css';

export default function Header() {
    // 데이터 로드 후.    
    const boards = ['board1', 'board2', 'board3', 'board4', 'board5', 'board6'];

    return (
    <header>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
        <div className="container">

            <a className="navbar-brand" href="#">
                <img 
                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTjNBOHJqRMYnlA7ExBs3gqteoucTcwXZtuhA&usqp=CAU"
                    height="50"
                    className="img-thumnails"
                />
            </a>

            <ul className="navbar-nav">
                <li className="nav-item">
                    <a className="nav-link" href="#">Login</a>
                </li>
                <li className="nav-item">
                    <a className="nav-link" href="#">MyPage</a>
                </li>
            </ul>

        </div>

        </nav>

        <div className="container d-flex flex-row my-3">

            {boards.map((item) => 
                <a className="px-3 fw-light menu-over"
                   href="#" 
                >{item}
                </a>
            )}

        </div>

        <hr />
    </header>
    );
}
