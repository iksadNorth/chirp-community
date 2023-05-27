import React from 'react';
import './css.css';
import CheckAuth from './CheckAuth';

export default function UserVerifedWithEmail(props) {
    return (
        <CheckAuth
            className={`${props.className}`}
            roles={["USER_VERIFIED_WITH_EMAIL"]}
        >{props.children}</CheckAuth>
    );
}