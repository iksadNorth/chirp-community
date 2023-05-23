import React, { useState } from "react";
import './css.css';

import * as c from '../../ComponentsUtils';

import Info from "./Info";
import ArticleList from "../UserPageCom/ArticleList";
import CommentList from "../UserPageCom/CommentList";
import { useParams } from "react-router-dom";

export default function UserPage() {
  const { userId } = useParams(0);
  const [ userName, setUserName ] = useState(null);

  return (
    <c.Sheet className="total-size container">
      <div className="row">
        <div className="col">
          <Info className="info-size" userId={userId} setUserName={setUserName}/>
        </div>
        <div className="col-0">
          
        </div>
      </div>
      <div className="row">
        <div className="col">
            <ArticleList className="list-size" userId={userId} userName={userName} readonly={true} />
        </div>
        <div className="col">
            <CommentList className="list-size" userId={userId} userName={userName} readonly={true} />
        </div>
      </div>
    </c.Sheet>
  );
}