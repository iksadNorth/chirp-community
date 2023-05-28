import React from 'react';
import './css.css';

export default function Label({ digit }) {
    return (
        <div>
            {digit > 0 ? (
                <button type="button" className={`btn btn-success`} disabled
                ><i className="bi bi-emoji-smile mx-2"></i> {digit}</button>
            ) : digit == 0 ? (
                <button type="button" className={`btn btn-secondary`} disabled
                ><i className="bi bi-emoji-neutral mx-2"></i> {digit}</button>
            ) : (
                <button type="button" className={`btn btn-danger`} disabled
                ><i className="bi bi-emoji-frown mx-2"></i> {digit}</button>
            )}
        </div>
    );
}