import React from 'react';
import './css.css';
import { Link } from "react-router-dom";

import PrimeAdmin from '../ComponentsUtils/AuthorizedCom/PrimeAdmin';

import LogOut from './LogOut';

import { useSelector } from "react-redux";

export default function PrimeAdminAuth(props) {
    const nickname = useSelector(state => state.AuthReducer.nickname);
    return (
        <PrimeAdmin>
            <div className="d-flex flex-row">
                <li className="nav-item">
                    <Link className="nav-link" to="/update/board">DashBoard</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/myPage">{nickname} 님</Link>
                </li>
                <li className="nav-item">
                    <LogOut />
                </li>
            </div>
        </PrimeAdmin>
    );
}