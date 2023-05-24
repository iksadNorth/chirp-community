import React from 'react';
import './css.css';
import { Link } from "react-router-dom";

import UnAuth from '../ComponentsUtils/AuthorizedCom/UnAuth';

export default function NoAuth(props) {
    return (
        <UnAuth>
            <div className="d-flex flex-row">
                <li className="nav-item">
                    <Link className="nav-link" to="/login">Login</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/signup">SignUp</Link>
                </li>
            </div>
        </UnAuth>
    );
}