import './index.css';

import { Link, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { get } from '../../api';

import BoardHeader from '../BoardHeader';


function BoardPage() {

  const { '*': id } = useParams();
  const [articles, setArticles] = useState([]);
  const [boardName, setBoardName] = useState('');


  useEffect(() => {
    get(`/api/v1/board/${id}`)
      .then((res02) => {
        setBoardName(res02.name);
      })
    
    get(`/api/v1/board/${id}/article`)
      .then((res01) => {
        setArticles(res01.content);
      })
      .catch((err) => {
        console.log(err);
      })
  }, [id])

  console.log('articles_content :', articles);

  return (
    <div className="Board">

      <BoardHeader boardId={id} boardName={boardName} mode='on'/>


      <div class="col-md-12 themed-grid-col" style={{ padding: '20px' }}>

        <div class="col-md-12 themed-grid-col" style={{ marginTop: '20px', marginBottom: '20px' }}>
          <div class="pb-3">
            <table class="table table-hover">
              <tr>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
              </tr>
              {articles.map((articles) => (
                <tr key={articles.id}>
                  <td scope='col'><Link to={`/article/${articles.id}`}>{articles.title}</Link></td>
                  <td scope='col'><Link>{articles.writer.nickname}</Link></td>
                  <td scope='col'>{articles.views}</td>
                </tr>
              ))}
            </table>
          </div>
        </div>

        <div class="col-md-6 themed-grid-col">페이지 네이션 부분</div>



      </div>




    </div >
  );
}

export default BoardPage;
