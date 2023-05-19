import React, { useState } from "react";
import './css.css';

import * as c from '../';

import { post } from '../../api';
import { setToken, isNotBlank } from '../../utils';

import { Link, useNavigate } from 'react-router-dom';

export default function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [error, setError] = useState('');

    const history = useNavigate();

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
            setError(null);
            history(-1);
        })
        .catch((error) => {
            console.log("handleLogin error");
            setError(error.errorMessage);
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
            <input type="email" className="form-control" placeholder="이메일을 입력하세요." value={email} onChange={wrap(setEmail)} />
        </div>
        
        <div className="input-group input-group-lg justify-content-center">
            <span className="input-group-text" id="basic-addon1">
                <i className="bi bi-file-lock2-fill"></i>
            </span>
            <input type="password" className="form-control" placeholder="비밀 번호를 입력하세요." value={password} onChange={wrap(setPassword)} />
        </div>

        <div className="d-flex justify-content-end btn-gap">
            <Link 
                className="btn btn-outline-dark rounded-pill fw-bold" 
                to="/signup"
            >회원가입</Link>
        </div>
        
        <div>
            <button 
                className="btn btn-dark btn-lg rounded-pill fw-bold" 
                onClick={handleLogin}
            >로그인</button>
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