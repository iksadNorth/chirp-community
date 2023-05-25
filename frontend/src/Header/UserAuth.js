import React from 'react';
import './css.css';
import { Link } from "react-router-dom";

import SiteUser from '../ComponentsUtils/AuthorizedCom/SiteUser';

import LogOut from './LogOut';

import { useSelector } from "react-redux";

export default function UserAuth(props) {
    const nickname = useSelector(state => state.AuthReducer.nickname);
    return (
        <SiteUser>
            <div className="d-flex flex-row">
                <li className="nav-item">
                    <Link className="nav-link" to="/myPage">{nickname} 님</Link>
                </li>
                <li className="nav-item">
                    <LogOut />
                </li>
            </div>
        </SiteUser>
    );
}