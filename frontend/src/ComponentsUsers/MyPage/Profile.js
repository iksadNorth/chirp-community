import React from "react";
import * as c from '../';

export default function Profile(props) {
    const iconName = props.iconName;
    const head = props.head;
    const content = props.content;
    const handlerChange = props.handlerChange;
    const readonly = props.readonly;
    const no_load = props.no_load;

    return (
        <li className="d-flex flex-row info-item">
            <i className={`bi icon-size ${iconName}`}></i>

            <div className="container">
                <div className='row'>
                    <div className='col'>
                        <h5 className="mb-1 text-start"><strong>{head}</strong></h5>
                    </div>
                    <div className='col-0'>

                    </div>
                </div>
                <div className='row'>
                    <div className='col'>
                        <c.RNW 
                            value={content} 
                            handlerChange={handlerChange} 
                            readonly={readonly} 
                            no_load={no_load}
                            className="border rounded-2 text-start info-item-text "
                        ></c.RNW>
                    </div>
                    <div className='col-0'>

                    </div>
                </div>
            </div>
    </li>
)};
