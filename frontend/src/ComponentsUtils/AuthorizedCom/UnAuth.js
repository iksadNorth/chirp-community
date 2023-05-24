import React from 'react';
import './css.css';
import CheckAuth from './CheckAuth';

export default function UnAuth(props) {
    return (
        <CheckAuth
            className={`${props.className}`}
            roles={[]} unAuth={true}
        >{props.children}</CheckAuth>
    );
}