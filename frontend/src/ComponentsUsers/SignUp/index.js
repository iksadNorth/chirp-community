import React, { useState } from "react";
import './css.css';

import * as c from '../../ComponentsUtils';

import { post } from '../../api';
import { isNotBlank } from '../../utils';

import { Link, useNavigate } from 'react-router-dom';

export default function SignUp() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [passwordAgain, setPasswordAgain] = useState('');
    const [nickname, setNickname] = useState('');
    
    const [error, setError] = useState('');
    
    const history = useNavigate();
    
    const wrap = (setFunc) => {
        return (event) => 
            setFunc(event.target.value);
    };
    
    const handleSignUp = () => {
        if(password != passwordAgain) {
            setError("비밀 번호가 일관되지 않습니다.");
            return ;
        }

        post('/api/v1/user', {
            body: JSON.stringify({
                email: email,
                password: password,
                nickname: nickname,
            })
        })
        .then((response) => {
            setError(null);
            history("/login");
        })
        .catch((error) => {
            console.log("handleLogin error");
            setError(error);
        })
    };
    
    return (
        <c.Card>
        <div className="container custom-size custom-gap">
            <h1
                className="fw-bold"
            >Chirp</h1>
    
            <div className="spacer" />
            
            <div className="input-group input-group-lg justify-content-center">
                <span className="input-group-text" id="basic-addon1">
                    <i className="bi bi-envelope-at-fill"></i>
                </span>
                <input type="text" className="form-control" placeholder="이메일을 입력하세요." value={email} onChange={wrap(setEmail)} />
            </div>
            
            <div className="input-group input-group-lg justify-content-center">
                <span className="input-group-text" id="basic-addon1">
                    <i className="bi bi-file-lock2-fill"></i>
                </span>
                <input type="password" className="form-control" placeholder="비밀 번호를 입력하세요." value={password} onChange={wrap(setPassword)} />
            </div>

            <div className="input-group input-group-lg justify-content-center">
                <span className="input-group-text" id="basic-addon1">
                    <i className="bi bi-check-all"></i>
                </span>
                <input type="password" className="form-control" placeholder="비밀 번호를 똑같이 입력하세요." value={passwordAgain} onChange={wrap(setPasswordAgain)} />
            </div>

            <div className="input-group input-group-lg justify-content-center">
                <span className="input-group-text" id="basic-addon1">
                    <i className="bi bi-person-heart"></i>
                </span>
                <input type="text" className="form-control" placeholder="닉네임을 입력하세요." value={nickname} onChange={wrap(setNickname)} />
            </div>
    
            <div className="d-flex justify-content-end btn-gap">
                <Link 
                    className="btn btn-outline-dark rounded-pill fw-bold" 
                    to="/login"
                >로그인 화면으로</Link>
            </div>
            
            <div>
                <button 
                    className="btn btn-dark btn-lg rounded-pill fw-bold" 
                    onClick={handleSignUp}
                >회원 가입</button>
            </div>

            {(error !== '' && isNotBlank(error)) &&
            <div>
                <span className="alert alert-danger" role="alert">
                    {error}
                </span>
            </div>
            }
        </div>
        </c.Card>
      );
}
