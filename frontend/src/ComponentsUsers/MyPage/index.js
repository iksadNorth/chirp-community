import React from "react";
import './css.css';

import * as c from '../';

import Info from "./Info";
import ArticleList from "./ArticleList";
import CommentList from "./CommentList";

export default function Login() {
  return (
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
  );
}