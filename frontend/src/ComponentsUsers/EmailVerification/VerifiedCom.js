import React from 'react';
import './css.css';

import * as c from '../../ComponentsUtils';

export default function VerifiedCom() {
    return (
        <c.TitleWithIcon 
            iconName="text-success bi-envelope-check" 
            head="이메일 인증 유저" 
        />
    );
}