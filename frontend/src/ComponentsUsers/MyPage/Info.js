import React, { useEffect, useState } from "react";
import './css.css';

import * as c from '../../ComponentsUtils';
import { get, patch } from "../../api";
import { adapterEvent, getToken, hasSomethingInString, isNotBlank } from "../../utils";

import Profile from "../UserPageCom/Profile";
import { useDispatch } from "react-redux";
import { login } from "../../store/actions/AuthAction";

export default function Info(props) {
    const defaultPassword = '[비공개]';

    const [email, setEmail] = useState('');
    const [password, setPW] = useState(defaultPassword);
    const [nickname, setNickname] = useState('');

    const [readonly, setReadOnly] = useState(true);
    const [messageToast, popToast] = useState(null);

    const dispatch = useDispatch();

    const updateInfo = () => {
        // 입력 가능한 요소로 변경.
        setReadOnly(false);
        setPW('');
    };

    const setInfo = () => {
        // 입력 불가능한 요소로 변경.
        setReadOnly(true);
        sendUpdateQuery();
        setPW(defaultPassword);
    };

    const setProfiles = (res) => {
        // 응답이 오면 정보를 셋팅하는 방법.
        setEmail(res.email);
        setNickname(res.nickname);

        popToast(null);
    };

    const sendUpdateQuery = () => {
        if(!isNotBlank(getToken())) { return ; }

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

        patch(`/api/v1/user/me`, {
            body: JSON.stringify(body),
        })
            .then((res) => {
                setProfiles(res);
                dispatch(login(res.token));
            })
            .catch((err) => {
                console.log("sendUpdateQuery Error");
                popToast(err);
            })
    };

    const loadUserInfo = () => {
        if(!isNotBlank(getToken())) { return ; }
        
        get(`/api/v1/user/me`)
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
