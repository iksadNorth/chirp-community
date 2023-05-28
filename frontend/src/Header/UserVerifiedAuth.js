import React from 'react';
import './css.css';
import { Link } from "react-router-dom";

import SiteUserVerifedWithEmail from '../ComponentsUtils/AuthorizedCom/SiteUserVerifedWithEmail';
import LogOut from './LogOut';

import { useSelector } from "react-redux";

export default function UserAuth(props) {
    const nickname = useSelector(state => state.AuthReducer.nickname);
    return (
        <SiteUserVerifedWithEmail>
            <div className="d-flex flex-row">
                <li className="nav-item">
                    <Link className="nav-link" to="/myPage">{nickname} 님</Link>
                </li>
                <li className="nav-item">
                    <LogOut />
                </li>
            </div>
        </SiteUserVerifedWithEmail>
    );
}