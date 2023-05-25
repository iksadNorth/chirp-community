import React, { useEffect, useState } from 'react';
import './css.css';

import { useSelector } from 'react-redux';

export default function CheckAuth(props) {
    const roles = props.roles ?? [];
    const unAuth = props.unAuth ?? false;

    const role = useSelector(state => state.AuthReducer.role);
    const isLogined = useSelector(state => state.AuthReducer.isLogined);
    const [check, setCheck] = useState(false);

    const checkAuth = () => {
        if(unAuth) {
            return !isLogined;
        } else {
            return isLogined && roles.includes(role);
        }
    };

    useEffect(() => {
        setCheck(checkAuth());
    }, [role]);

    return (
        <div className={`${props.className}`}>
            {check ? 
                <div>
                    {props.children}
                </div>
            : null }
        </div>
    );
}