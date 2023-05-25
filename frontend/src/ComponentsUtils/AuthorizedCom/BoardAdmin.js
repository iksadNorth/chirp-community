import React from 'react';
import './css.css';
import CheckAuth from './CheckAuth';

export default function BoardAdmin(props) {
    return (
        <CheckAuth
            className={`${props.className}`}
            roles={["BOARD_ADMIN"]}
        >{props.children}</CheckAuth>
    );
}