import React, { useState } from "react";
import './css.css';

import { post } from '../../api';
import { setToken, getToken } from '../../utils';

export default function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [token, setVarToken] = useState('');

    const wrap = (setFunc) => {
        return (event) => 
            setFunc(event.target.value);
    };

    const handleLogin = () => {
        post('/api/v1/auth/login', {
            body: JSON.stringify({
                email: email,
                password: password,
            })
        })
        .then((response) => {
            setToken(response.token);
            setVarToken(getToken());
        })
        .catch((error) => {
            console.log("handleLogin error");
        })
    };

  return (
    <div className="container custom-size custom-gap border rounded-5 shadow">
        <h1
            className="fw-bold"
        >Chirp</h1>
        
        <div className="input-group input-group-lg justify-content-center">
            <span className="input-group-text" id="basic-addon1">
                <i className="bi bi-envelope-at-fill"></i>
            </span>
            <input type="email" className="form-control" placeholder="이메일을 입력하세요." value={email} onChange={wrap(setEmail)} />
        </div>
        
        <div className="input-group input-group-lg justify-content-center">
            <span className="input-group-text" id="basic-addon1">
                <i className="bi bi-file-lock2-fill"></i>
            </span>
            <input type="password" className="form-control" placeholder="비밀 번호를 입력하세요." value={password} onChange={wrap(setPassword)} />
        </div>
        
        <div>
            <button 
                className="btn btn-dark btn-lg rounded-pill fw-bold" 
                onClick={handleLogin}
            >로그인</button>
        </div>
        
        <div>
            {email}
        </div>

        <div>
            {password}
        </div>

        <div>
            {token}
        </div>
    </div>
  );
}