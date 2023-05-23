import React from "react";
import './css.css';

export default function Sheet(props) {
    return (
        <div className="container d-block bg-body-secondary p-4 py-md-5 ${props.className}" tabIndex="-1" role="dialog" id="modalSheet">
        <div className="container" role="document">
            <div className={`rounded-4 shadow border border-dark sheet-size ${props.className}`}>
                {props.children}
            </div>
        </div>
        </div>
    );
}
