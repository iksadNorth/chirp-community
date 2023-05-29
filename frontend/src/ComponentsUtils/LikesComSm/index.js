import React, { useEffect, useState } from 'react';
import './css.css';
import { get, post } from '../../api';

import * as u from '..';

import BtnDisLikes from './BtnDisLikes';
import BtnLikes from './BtnLikes';
import Label from './Label';

export default function LikesCom(props) {
    const [message, setMessage] = useState('');

    const [flagLikes, setFlagLikes] = useState(false);
    const [labelLikes, setLabelLikes] = useState(0);
    const [flagDisLikes, setFlagDisLikes] = useState(false);

    const transResponse = (res) => {
        setLabelLikes(res.numLikes);

        if(res.likeType == "LIKE") {
            setFlagLikes(true);
            setFlagDisLikes(false);
        } else if(res.likeType == "DISLIKE") {
            setFlagLikes(false);
            setFlagDisLikes(true);
        } else {
            setFlagLikes(false);
            setFlagDisLikes(false);
        }
    };

    const loadLikesState = () => {
        if(!props.id) {
            return ;
        }

        get(`/api/v1/article_comment/${props.id}/sum-likes`)
        .then(res => {
            transResponse(res);
        })
        .catch(err => {
            console.log(`loadLikesState error`);
            setMessage(err);
        });
    };
    const toggleLikes = () => {
        if(!props.id) {
            return ;
        }
        
        post(`/api/v1/article_comment/${props.id}/like`)
        .then(res => {
            transResponse(res);
        })
        .catch(err => {
            console.log(`toggleLikes error`);
            setMessage(err);
        });
    };
    const toggleDisLikes = () => {
        if(!props.id) {
            return ;
        }
        
        post(`/api/v1/article_comment/${props.id}/dislike`)
        .then(res => {
            transResponse(res);
        })
        .catch(err => {
            console.log(`toggleDisLikes error`);
            setMessage(err);
        });
    };

    useEffect(() => {
        loadLikesState();
    }, []);

    return (
        <div className='LikesComSm-container m-2'>
            <Label digit={labelLikes}/>
            <BtnLikes flag={flagLikes} handlerClick={toggleLikes}/>
            <BtnDisLikes flag={flagDisLikes} handlerClick={toggleDisLikes}/>
            <u.Toast title="에러 발생" body={message} />
        </div>
    );
}