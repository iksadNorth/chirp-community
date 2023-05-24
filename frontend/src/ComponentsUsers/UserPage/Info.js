import React, { useEffect, useState } from "react";
import './css.css';

import * as c from '../../ComponentsUtils';
import { get, patch } from "../../api";
import { adapterEvent, getToken, isNotBlank, isPrimeAdmin, isThisBoardAdmin } from "../../utils";

import Profile from "../UserPageCom/Profile";

export default function Info(props) {
    const [email, setEmail] = useState('');
    const [nickname, setNickname] = useState('');

    const [messageToast, popToast] = useState(null);
    const [isBoardAdmin, setBoardAdmin] = useState(false);

    const userId = props.userId ?? "me";

    const setProfiles = (res) => {
        // 응답이 오면 정보를 셋팅하는 방법.
        setEmail(res.email);
        setNickname(res.nickname);
        props.setUserName(res.nickname);
        setBoardAdmin(isThisBoardAdmin(res.role));

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

    const updateRole = () => {
        if(!isNotBlank(getToken())) { return ; }
        
        patch(`/api/v1/user/${userId}`, {
            body: JSON.stringify({
                role: isBoardAdmin ? "BOARD_ADMIN" : "USER",
            }),
        })
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

    useEffect(() => {
        updateRole();
    }, [isBoardAdmin]);

    return (
        <c.Sheet className={props.className}>
            <div className="d-flex flex-column align-items-start m-5">
                <div className="container">
                    <div className="row">
                        <div className="col d-flex flex-row">
                            <h1 className="fw-bold mb-0">개인 정보</h1>
                        </div>
                        <div className="col-auto d-flex flex-row">
                            { isPrimeAdmin() ? 
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" role="switch" id="switch" 
                                    value={isBoardAdmin} onChange={adapterEvent(setBoardAdmin)} 
                                />
                                <label class="form-check-label" for="switch">게시판 관리자 권한</label>
                            </div>
                        : null }
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
