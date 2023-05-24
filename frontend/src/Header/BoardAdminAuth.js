import React from 'react';
import './css.css';
import { Link } from "react-router-dom";

import BoardAdmin from '../ComponentsUtils/AuthorizedCom/BoardAdmin';

import { delToken } from '../utils';

import { useDispatch, useSelector } from "react-redux";
import { logout } from "../store/actions/AuthAction";

export default function BoardAdminAuth(props) {
    const nickname = useSelector(state => state.AuthReducer.nickname);
    const dispatch = useDispatch();
    return (
        <BoardAdmin>
            <div className="d-flex flex-row">
                <li className="nav-item">
                    <Link className="nav-link" to="/myPage">{nickname} 님</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" onClick={() => {
                        delToken();
                        dispatch(logout());
                        }}>Logout</Link>
                </li>
            </div>
        </BoardAdmin>
    );
}