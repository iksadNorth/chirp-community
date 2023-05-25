import React from 'react';
import './css.css';
import CheckAuth from './CheckAuth';

export default function User(props) {
    return (
        <CheckAuth
            className={`${props.className}`}
            roles={["USER"]}
        >{props.children}</CheckAuth>
    );
}