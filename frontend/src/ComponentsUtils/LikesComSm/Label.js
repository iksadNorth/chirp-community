import React from 'react';
import './css.css';

export default function Label({ digit }) {
    return (
        <div>
            {digit > 0 ? (
                <button className={`btn btn-sm btn-success`} disabled
                >{digit}</button>
            ) : digit == 0 ? (
                <button className={`btn btn-sm btn-secondary`} disabled
                >{digit}</button>
            ) : (
                <button className={`btn btn-sm btn-danger`} disabled
                >{digit}</button>
            )}
        </div>
    );
}