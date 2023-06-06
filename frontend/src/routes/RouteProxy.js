import React, { useEffect } from 'react';
import { useLocation } from 'react-router';
import { generateUUID } from '../utils';

export default function ({ children }) {
    const location = useLocation();
    
    useEffect(() => {
        localStorage.setItem('page-session-id', generateUUID());
    }, [location]);

    return (
        <div>
            {children}
        </div>
    );
}