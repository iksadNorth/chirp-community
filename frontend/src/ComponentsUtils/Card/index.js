import React from "react";
import './css.css';

export default function Card(props) {
    return (
        <div class="modal modal-sheet position-static d-block bg-body-secondary p-4 py-md-5" tabindex="-1" role="dialog" id="modalSheet">
        <div class="modal-dialog" role="document">
            <div class="modal-content rounded-4 shadow">
                {props.children}
            </div>
        </div>
        </div>
    );
}
