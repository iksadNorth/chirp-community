import React, { useState } from "react";
import { adapterEvent } from "../../utils";

export default function SearchInput(props) {
    const className = props.className;
    const handleSearch = props.handleSearch;

    const [text, setText] = useState("");

    const whatKey = (key, handler) => {
        return (event) => {
            if(event.key === key) {
                handler(event);
            }
        }
    }
    return (
        <div className={`input-group mb-3 ${className}`}>
            <input type="text" className="form-control" onChange={adapterEvent(setText)} onKeyDown={whatKey('Enter', adapterEvent(handleSearch))} />
            <button className="btn btn-outline-secondary" type="button" onClick={() => handleSearch(text)}>
                <i className="bi bi-search"></i>
            </button>
        </div>
    );
}