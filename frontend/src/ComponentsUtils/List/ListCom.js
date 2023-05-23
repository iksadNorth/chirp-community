import React from 'react';

export default function ListCom({ children }) {
    return (
        <ul className="list-group list-group-flush mb-3">
            {React.Children.map(children, (child, i) =>
                (child ? 
                <ul className={`list-group-item`}>
                    {child}
                </ul>
                : null)
            ).filter(Boolean)}
        </ul>
    );
}