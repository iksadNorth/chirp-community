import React, { useState } from 'react';
import './css.css';

import * as c from '../../ComponentsUtils';
import SiteUser from '../../ComponentsUtils/AuthorizedCom/SiteUser';
import SiteUserVerifedWithEmail from '../../ComponentsUtils/AuthorizedCom/SiteUserVerifedWithEmail';

import VerifiedCom from './VerifiedCom';
import MustBeVerifiedCom from './MustBeVerifiedCom';

export default function EmailVerification(props) {
    const [message, setMessage] = useState("");

    return (
        <c.Sheet>
            <SiteUser>
                <MustBeVerifiedCom setMessage={setMessage}/>
            </SiteUser>
            <SiteUserVerifedWithEmail className="pt-3" >
                <VerifiedCom />
            </SiteUserVerifedWithEmail>
            <c.Toast title="메시지" body={message} />
        </c.Sheet>
    );
}