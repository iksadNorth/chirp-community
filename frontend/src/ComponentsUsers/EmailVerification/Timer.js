import React, { useState, useEffect } from 'react';

export default function TimerComponent(props) {
    const [remainingTime, setRemainingTime] = useState(props.timeSec ?? 3*60);
    const [displayTime, setDisplayTime] = useState('');

    const [timer, setTimer] = useState();

    const func_count_timer = () => {
        setRemainingTime(prevTime => prevTime - 1);
    };
    const func_kill_timer = (clock) => {return () => {
            clearInterval(clock);
            setTimer(null);
        }};
    const gap_rendering_millisec = 1000; 

    const handleTerminated = props.handleTerminated ?? (() => {});

    useEffect(() => {
        const var_timer = setInterval(func_count_timer, gap_rendering_millisec);
        setTimer(var_timer);

        return func_kill_timer(var_timer);
    }, []);

    useEffect(() => {
        if(remainingTime < 0) {
            func_kill_timer(timer)();
            handleTerminated();
            return ;
        }
        const minutes = Math.floor(remainingTime / 60);
        const seconds = Math.floor(remainingTime % 60);

        const padding = (digit) => String(digit).padStart(2, '0');

        const format = `${padding(minutes)} : ${padding(seconds)}`

        setDisplayTime(format);
    }, [remainingTime]);

    return (
        <div className={`${props.className}`}>
            {displayTime}
        </div>
    );
}
