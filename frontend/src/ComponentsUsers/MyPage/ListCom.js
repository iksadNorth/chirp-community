import React from 'react';

export default function ListCom({ children }) {
    return (
        <ul className="list-group list-group-flush mb-3">
            {React.Children.map(children, (child, i) =>
                <ul className={`list-group-item`}>
                    {child}
                </ul>
            )}
        </ul>
    );
}