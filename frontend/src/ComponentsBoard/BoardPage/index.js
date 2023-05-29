import './index.css';

import { Link, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { get } from '../../api';
import Page from '../../ComponentsUtils/List/PaginationBar'

import BoardHeader from '../BoardHeader';


function BoardPage() {

  const { '*': id } = useParams();
  const [articles, setArticles] = useState([]);
  const [boardName, setBoardName] = useState('');

  const [page, setPage] = useState(0);
  const [numTotalPages, setNumTotalPages] = useState(1);


  useEffect(() => {
    get(`/api/v1/board/${id}/article`)
      .then((res01) => {
        setArticles(res01.content);
        setNumTotalPages(res01.totalPages)
        console.log('res01:',res01);
      })
      .then((res01) => {
        get(`/api/v1/board/${id}`)
          .then((res02) => {
            setBoardName(res02.name);
          })
      })
      .catch((err) => {
        console.log(err);
      })
  }, [id])

  console.log('articles_content :', articles);

  return (
    <div className="Board">

      <BoardHeader boardId={id} boardName={boardName} mode='on' />


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

        <div class="justify-content-center">
          <Page className="mb-3"
            // numTotalPages={props.numTotalPages}
            numTotalPages={numTotalPages}
            handlePage={setPage}
            radius={3}
          />
        </div>



      </div>




    </div >
  );
}

export default BoardPage;
