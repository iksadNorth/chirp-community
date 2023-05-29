import React, { useEffect, useState } from 'react';
import './css.css';
import { get, post } from '../../api';

import * as u from '../../ComponentsUtils';

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

        get(`/api/v1/article/${props.id}/sum-likes`)
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
        
        post(`/api/v1/article/${props.id}/like`)
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
        
        post(`/api/v1/article/${props.id}/dislike`)
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
        <div className='custom-container-size m-2 p-2 border rounded-4 shadow-sm'>
            <div className="row custom-elements-size">
                <div className="col-auto">
                    <BtnLikes flag={flagLikes} handlerClick={toggleLikes}/>
                </div>
                <div className="col">
                    <Label digit={labelLikes}/>
                </div>
                <div className="col-auto">
                    <BtnDisLikes flag={flagDisLikes} handlerClick={toggleDisLikes}/>
                </div>
            </div>
            <u.Toast title="에러 발생" body={message} />
        </div>
    );
}