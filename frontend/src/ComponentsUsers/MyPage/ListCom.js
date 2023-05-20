import React from 'react';

export default function ListCom({ children }) {
    return (
        <ul className="list-group list-group-flush mb-3">
            {React.Children.map(children, (child, i) =>
                <ul className={`list-group-item ${i%2==1 ? "bg-dark text-white" : ""}`}>
                    {child}
                </ul>
            )}
        </ul>
    );
}