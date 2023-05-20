import React from "react";

export default function SearchInput(props) {
    const className = props.className;
    const handleSearch = props.handleSearch;

    const whatKey = (key, handler) => {
        return (event) => {
            if(event.key === key) {
                handler(event);
            }
        }
    }
    return (
        <div className={`input-group mb-3 ${className}`}>
            <input type="text" className="form-control" onKeyDown={whatKey('Enter', handleSearch)} />
            <button className="btn btn-outline-secondary" type="button" onClick={handleSearch}>
                <i className="bi bi-search"></i>
            </button>
        </div>
    );
}