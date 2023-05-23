import React, { useEffect, useState } from "react";
import './css.css';

import * as c from '../../ComponentsUtils';

import Info from "./Info";
import ArticleList from "./ArticleList";
import CommentList from "./CommentList";
import { getToken } from "../../utils";

export default function Login() {
  const [accessToken, setAccesstoken] = useState(null);

  useEffect(() => {
    setAccesstoken(getToken());
  }, []);

  return (accessToken ? 
    <c.Sheet className="total-size container">
      <div className="row">
        <div className="col">
          <Info className="info-size"/>
        </div>
        <div className="col-0">
          
        </div>
      </div>
      <div className="row">
        <div className="col">
            <ArticleList className="list-size" />
        </div>
        <div className="col">
            <CommentList className="list-size" />
        </div>
      </div>
    </c.Sheet>

    : 

    <c.Sheet className="total-size container">
      <img className="m-3" src="401-Unauthorized-t.jpg" alt="401 unauthorized" />
    </c.Sheet>
  );
}