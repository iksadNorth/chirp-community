import React from "react";
import './css.css';

export default function Card(props) {
    return (
        <div className="modal modal-sheet position-static d-block bg-body-secondary p-4 py-md-5" tabIndex="-1" role="dialog" id="modalSheet">
        <div className="modal-dialog" role="document">
            <div className="modal-content rounded-4 shadow">
                {props.children}
            </div>
        </div>
        </div>
    );
}
