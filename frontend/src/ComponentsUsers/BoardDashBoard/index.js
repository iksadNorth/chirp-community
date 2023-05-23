import React, { useState } from 'react';
import './css.css';
import * as u from '../../ComponentsUtils';
import BoardList from './BoardList';

export default function Component(props) {
    return (
        <u.Sheet>
            <div className='container container-size'>
                <div className='row'>
                    <div className='col-auto '>
                        <u.TitleWithIcon iconName="bi-kanban" head="게시판 관리 대시보드"/>
                    </div>
                    <div className='col '>
                        
                    </div>
                </div>
                <div className='row'>
                    <div className='col'>
                        <BoardList />
                    </div>
                </div>
            </div>
        </u.Sheet>
    );
} 