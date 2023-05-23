import React, { useEffect, useState } from "react";
import './css.css';

import * as c from '../../ComponentsUtils';
import { get } from "../../api";
import { adapterEvent, getToken, isNotBlank } from "../../utils";

import Profile from "../UserPageCom/Profile";

export default function Info(props) {
    const [email, setEmail] = useState('');
    const [nickname, setNickname] = useState('');

    const [messageToast, popToast] = useState(null);

    const userId = props.userId ?? "me";

    const setProfiles = (res) => {
        // 응답이 오면 정보를 셋팅하는 방법.
        setEmail(res.email);
        setNickname(res.nickname);
        props.setUserName(res.nickname);

        popToast(null);
    };

    const loadUserInfo = () => {
        if(!isNotBlank(getToken())) { return ; }
        
        get(`/api/v1/user/${userId}`)
            .then((res) => {
                setProfiles(res);
            })
            .catch((err) => {
                console.log("loadUserInfo Error");
                popToast(err);
            })
    };

    useEffect(() => {
        loadUserInfo();
      }, []);

    return (
        <c.Sheet className={props.className}>
            <div className="d-flex flex-column align-items-start m-5">
                <div className="container">
                    <div className="row">
                        <div className="d-flex flex-row">
                            <h1 className="fw-bold mb-0">개인 정보</h1>
                        </div>
                    </div>
                </div>
                <ul className="info-container">
                    <Profile 
                        iconName="text-secondary bi-person-square" 
                        head="이메일" 
                        content={email} 
                        handlerChange={adapterEvent(setEmail)} 
                        readonly={true}
                    />
                    <Profile 
                        iconName="text-primary bi-person-vcard-fill" 
                        head="활동명" 
                        content={nickname} 
                        handlerChange={adapterEvent(setNickname)} 
                        readonly={true}
                    />
                </ul>
            </div>
            <c.Toast title="에러 발생" body={messageToast} />
        </c.Sheet>
    );
}
