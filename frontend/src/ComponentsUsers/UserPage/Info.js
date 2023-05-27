import React, { useEffect, useState } from "react";
import './css.css';

import * as c from '../../ComponentsUtils';
import { get, patch } from "../../api";
import { adapterEvent, adapterCheckBoxEvent, getToken, isNotBlank } from "../../utils";

import Profile from "../UserPageCom/Profile";
import PrimeAdmin from '../../ComponentsUtils/AuthorizedCom/PrimeAdmin';
import { useDispatch } from "react-redux";
import { login } from "../../store/actions/AuthAction";

const isThisBoardAdmin = (role) => {
    return role == 'BOARD_ADMIN';
}

export default function Info(props) {
    const [email, setEmail] = useState('');
    const [nickname, setNickname] = useState('');

    const [messageToast, popToast] = useState(null);
    const [isBoardAdmin, setBoardAdmin] = useState(false);

    const userId = props.userId ?? "me";

    const dispatch = useDispatch();

    const setProfiles = (res) => {
        // 응답이 오면 정보를 셋팅하는 방법.
        setEmail(res.email);
        setNickname(res.nickname);
        props.setUserName(res.nickname);
        setBoardAdmin(isThisBoardAdmin(res.role));

        popToast(null);
    };

    const loadUserInfo = () => {        
        get(`/api/v1/user/${userId}`)
            .then((res) => {
                setProfiles(res);
            })
            .catch((err) => {
                console.log("loadUserInfo Error");
                popToast(err);
            })
    };

    const updateRole = (isSwitchOn) => {
        if(!isNotBlank(getToken())) { return ; }
        
        const isBoardAdminBySwitch = isSwitchOn ? "BOARD_ADMIN" : "USER";
        if(isBoardAdminBySwitch == isBoardAdmin) { return ; }
        
        patch(`/api/v1/user/${userId}`, {
            body: JSON.stringify({
                role: isBoardAdminBySwitch,
            }),
        })
        .then((res) => {
            setProfiles(res);
            dispatch(login(res.token));
        })
        .catch((err) => {
            console.log("updateRole Error");
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
                        <div className="col d-flex flex-row">
                            <h1 className="fw-bold mb-0">개인 정보</h1>
                        </div>
                        <div className="col-auto d-flex flex-row">
                            <PrimeAdmin>
                                <div className="form-check form-switch">
                                    <label className="form-check-label">게시판 관리자 권한</label>
                                    <input className="form-check-input" type="checkbox" role="switch" id="switch" 
                                        checked={isBoardAdmin} onChange={adapterCheckBoxEvent(updateRole)} 
                                    />
                                </div>
                            </PrimeAdmin>
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
