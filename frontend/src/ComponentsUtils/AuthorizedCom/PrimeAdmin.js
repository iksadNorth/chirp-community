import React from 'react';
import './css.css';
import CheckAuth from './CheckAuth';

export default function PrimeAdmin(props) {
    return (
        <CheckAuth
            className={`${props.className}`}
            roles={["PRIME_ADMIN"]}
        >{props.children}</CheckAuth>
    );
}