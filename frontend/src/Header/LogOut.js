import React from 'react';
import './css.css';

import { Link } from "react-router-dom";

import { useDispatch } from "react-redux";
import { logout } from "../store/actions/AuthAction";

export default function LogOut() {
    const dispatch = useDispatch();

    const handlerLogOut = () => {
        dispatch(logout());
    };
    return (
        <Link className="nav-link" onClick={handlerLogOut}
        >LogOut</Link>
    );
}