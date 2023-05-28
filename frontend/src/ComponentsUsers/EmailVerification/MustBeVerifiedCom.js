import React, { useState, useEffect } from 'react';
import './css.css';

import { adapterEvent, addParams, hasSomethingInString } from "../../utils";

import Profile from "../UserPageCom/Profile";
import { post, patch } from '../../api';

import { useSelector, useDispatch } from 'react-redux';
import { login } from '../../store/actions/AuthAction';

import { useLocation } from 'react-router-dom';
import queryString from 'query-string';

import Timer from './Timer';

export default function MustBeVerifiedCom(props) {
    const [code, setCode] = useState('');
    const [readonly, setReadonly] = useState(true);
    const [loading, isLoading] = useState(false);

    const dispatch = useDispatch();
    const id = useSelector(state => state.AuthReducer.id);

    const location = useLocation();
    const urlParams = queryString.parse(location.search);

    const setMessage = props.setMessage ?? (() => {});
    const timeSec = 180;

    const sendGetCodeQuery = () => {
        isLoading(true);

        post(`/api/v1/auth/email_verification_code`)
            .then(() => {
                setMessage("보안코드 이메일 전송 완료!");
                setReadonly(false);
            })
            .catch((err) => {
                console.log("sendGetCodeQuery Error");
                setMessage(`인증 코드 받기 실패. ${err}`);
            })
            .finally(() => {
                isLoading(false);
                setCode('');
            });
    };

    const verifyCodeQuery = (arg_id, arg_code) => {
        patch(addParams(`/api/v1/user/${arg_id}/role/user_verified_by_email`, {
            code: arg_code,
        }))
            .then((res) => {
                setMessage("보안코드 이메일 인증 완료!");
                setReadonly(true);
                dispatch(login(res.token));
            })
            .catch((err) => {
                console.log("verifyCodeQuery Error");
                setMessage(`보안코드 이메일 인증 실패. ${err}`);
            })
    };

    useEffect(() => {
        if(!hasSomethingInString(urlParams.user_id)) {
            return ;
        } else if(!hasSomethingInString(urlParams.code)) {
            return ;
        } else {
            verifyCodeQuery(urlParams.user_id, urlParams.code);
        }
    }, []);
    return (
        <div className='container p-3'>
            <div className='row'>

                <div className='col-auto'>
                    <Profile 
                        iconName="text-warning bi-envelope-x" 
                        head="이메일 인증 필요" 
                        content={code} 
                        handlerChange={adapterEvent(setCode)} 
                        readonly={readonly}
                    />
                </div>

                <div className='col my-4'>
                    {readonly ? 
                    <button 
                        className='btn btn-outline-dark'
                        onClick={sendGetCodeQuery}
                    >보안 코드 받기</button>
                        :
                    <button 
                        className='btn btn-dark'
                        onClick={() => verifyCodeQuery(id, code)}
                    >인증 확인</button>
                    }
                </div>

            </div>


            <div className='row'>

                {!readonly ? 
                <div className='col m-2'>
                    <Timer timeSec={timeSec} handleTerminated={() => {setReadonly(true)}} />
                </div>
                : null}

            </div>


            <div className='row'>

                <div className='col text-start'>
                    <strong>
                        아래 적힌 이메일로 보안 코드 6자리를 보내드립니다. <br/>
                        이메일의 보안 코드를 위 입력창에 적어주시거나 <br/>
                        이메일의 링크 주소를 클릭해주세요. <br/>
                        3분의 시간 제한이 있습니다. 유의해주시기 바랍니다.
                    </strong>
                </div>

                <div className='col-auto my-2'>
                    {loading ?
                    <div className="spinner-border" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </div>
                    : null}
                </div>

            </div>
        </div>
    );
}