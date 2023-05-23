import React from 'react';
import './css.css';

export default function ButtonSet(props) {
    const clickCreate = () => {
        props.setFlag(false);
    };
    const clickUpdate = () => {
        props.setFlag(true);
        props.createData();
    };
    const clickCancel = () => {
        props.setFlag(true);
    };
    return (
        <div className='button-set'>
            {props.flag ? 
            <div className='container'>
                <button
                    className='btn btn-success'
                    onClick={clickCreate}
                    >
                    <i class="bi bi-plus-square"></i>
                </button>
            </div>
            :
            <div className='container'>
                <button
                    className='btn btn-info'
                    onClick={clickUpdate}
                >
                    <i class="bi bi-check-square"></i>
                </button>
                <button
                    className='btn btn-secondary'
                    onClick={clickCancel}
                >
                    <i class="bi bi-x-square"></i>
                </button>
            </div>
            }
        </div>
    );
}