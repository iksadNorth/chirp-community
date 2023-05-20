import './index.css';

import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
// import { pageRequest } from '../utils';
import { get } from '../../api';



function Board() {

  const { '*' : id } = useParams();
  const [articles, setArticles] = useState([]);

  useEffect(() => {
    get(`/api/v1/board/${id}/article`)
    .then((data) => {
      console.log('articles_origin :', data);
      setArticles(data.content);
    })
    .catch((err) => {
      console.log(err);
    })
  }, [id])


  console.log('articles_content :', articles);
  console.log('articles_content01 :', articles[0]);

  return (
    <div className="Board">
      <h2>게시판 id : {id}</h2>
      
      
        <table>
          <tr>
            <th scope="col">작성자</th>
            <th scope="col">제목</th>
            <th scope="col">조회수</th>
          </tr>
          {articles.map((articles) => (
            <tr>
              <td scope='col'>{articles.writer.nickname}</td>
              <td scope='col'>{articles.title}</td>
              <td scope='col'>{articles.views}</td>
            </tr>
          ))}
        </table>
      
    

    </div>
  );
}

export default Board;
