import React, { useEffect, useState } from "react";
import './css.css';

import * as c from '..';
import { get, patch } from "../../api";
import { adapterEvent, decodeJwt, hasSomethingInString, isNotBlank } from "../../utils";

import Profile from "./Profile";

export default function Info(props) {

    const [id, setId] = useState(null);
    const [email, setEmail] = useState('');
    const [password, setPW] = useState('');
    const [nickname, setNickname] = useState('');

    const [readonly, setReadOnly] = useState(true);
    const [messageToast, popToast] = useState(null);

    const updateInfo = () => {
        // 입력 가능한 요소로 변경.
        setReadOnly(false);
    };

    const setInfo = () => {
        // 입력 불가능한 요소로 변경.
        setReadOnly(true);
        sendUpdateQuery();
    };

    const setProfiles = (res) => {
        // 응답이 오면 정보를 셋팅하는 방법.
        setEmail(res.email);
        setNickname(res.nickname);

        popToast(null);
    };

    const sendUpdateQuery = () => {
        if(!isNotBlank(id)) { return ; }

        const body = {};
        if(hasSomethingInString(email)) {
            body.email = email;
        }
        if(hasSomethingInString(password)) {
            body.password = password;
        }
        if(hasSomethingInString(nickname)) {
            body.nickname = nickname;
        }

        patch(`/api/v1/user/${id}`, {
            body: JSON.stringify({body}),
        })
            .then((res) => {
                setProfiles(res);
            })
            .catch((err) => {
                console.log("sendUpdateQuery Error");
                popToast(err.errorMessage);
            })
    };

    const loadUserInfo = () => {
        if(!isNotBlank(id)) { return ; }
        
        get(`/api/v1/user/${id}`)
            .then((res) => {
                setProfiles(res);
            })
            .catch((err) => {
                console.log("loadUserInfo Error");
                popToast(err.errorMessage);
            })
    };

    useEffect(() => {
        const payload = decodeJwt();
        setId(payload.ID);
    }, [])

    useEffect(() => {
        loadUserInfo();
      }, [id]);

    return (
        <c.Sheet className={props.className}>
            <div className="d-flex flex-column align-items-start m-5">
                <div className="container">
                    <div className="row">
                        <div className="d-flex flex-row">
                            <h1 className="fw-bold mb-0">개인 정보</h1>
                        </div>
                        <div className="d-flex flex-row-reverse">
                            <button 
                                type="button" className="btn btn-info"
                                onClick={(readonly) ? updateInfo : setInfo}
                            >{(readonly) ? "수정" : "완료"}</button>
                        </div>
                    </div>
                </div>
                <ul className="info-container">
                    <Profile 
                        iconName="text-secondary bi-person-square" 
                        head="이메일" 
                        content={email} 
                        handlerChange={adapterEvent(setEmail)} 
                        readonly={readonly}
                    />
                    <Profile 
                        iconName="text-warning bi-key" 
                        head="비밀 번호" 
                        content={password} 
                        handlerChange={adapterEvent(setPW)} 
                        readonly={readonly} no_load={true}
                    />
                    <Profile 
                        iconName="text-primary bi-person-vcard-fill" 
                        head="활동명" 
                        content={nickname} 
                        handlerChange={adapterEvent(setNickname)} 
                        readonly={readonly}
                    />
                </ul>
            </div>
            <c.Toast title="에러 발생" body={messageToast} />
        </c.Sheet>
    );
}
