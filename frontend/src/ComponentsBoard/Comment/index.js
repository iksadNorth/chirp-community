import './index.css';

import { useEffect, useState } from 'react';
import { get, post, del, patch } from '../../api';
import Page from '../../ComponentsUtils/List/PaginationBar'

import { useSelector } from "react-redux";

import * as u from '../../ComponentsUtils';
import { Link } from 'react-router-dom';


function CommentBody(props) {

  const userId = useSelector(state => state.AuthReducer.id);

  const [selectedCommentId, setSelectedCommentId] = useState(null);
  const [updatedComments, setUpdatedComments] = useState([]);

  const enterEditMode = (commentId) => {
    setSelectedCommentId(commentId);
  };

  const exitEditMode = () => {
    setSelectedCommentId(null);
  };





  return (
    <div>
      {props.comments && props.comments.map((comment) => (
        <div key={comment.id} className='mb-4'>
          {/* 작성자 */}
          <div className='d-flex justify-content-between'>
            <div className="writer d-flex fw-bold" style={{ fontSize: '1.2em' }}>
              <Link to={`/user/${comment.writer.id}`} className='nav-link active'>{comment.writer.nickname}</Link>
            </div>

            <div>
              {userId === comment.writer.id ? (
                // {/* 수정/삭제 버튼 */}
                <div className='float-end'>
                  {selectedCommentId === comment.id ? (
                    // 적용 모드
                    <>
                      <button onClick={() => {
                        exitEditMode();
                        patch(`/api/v1/article_comment/${selectedCommentId}`, {
                          body: JSON.stringify({
                            content: updatedComments
                          })
                        });
                      }}>
                        적용
                      </button>
                      <button onClick={exitEditMode}>취소</button>
                    </>
                  ) : (
                    // 수정 모드
                    <div className='d-flex justify-content-end'>
                      <button onClick={() => { enterEditMode(comment.id); setUpdatedComments(comment.content); }}>수정</button>
                      <button onClick={() => del(`/api/v1/article_comment/${comment.id}`)}>삭제</button>
                    </div>
                  )}
                </div>
              ) : null}
            </div>
          </div>
          <div className='d-flex justify-content-between'>
            <div style={{ display: 'flex', alignItems: 'center' }}>
              {/* 댓글 내용 */}
              {selectedCommentId === comment.id ? (
                // 수정 모드
                <textarea
                  type="text"
                  value={updatedComments}
                  onChange={(e) => {
                    setUpdatedComments(e.target.value);
                  }}
                />
              ) : (
                // 일반 모드
                <div style={{ width: '100%' }}>{comment.content}</div>
              )}
            </div>
            <div>
              <u.LikesComSm id={comment.id} />
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}



function CreateButton(props) {
  return (
    <button style={{ width: '60px' }} onClick={() => {
      post(`/api/v1/article_comment`, {
        body: JSON.stringify({
          content: props.newComment,
          article_id: props.article_id
        })
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log('에러발생 :', err);
        })
    }}>등록</button>
  )
}



function Comment(props) {

  const [comments, setComments] = useState();
  const [newComment, setNewComment] = useState();

  const [page, setPage] = useState(0);
  const [numTotalPages, setNumTotalPages] = useState(1);

  // const [writer, setWriter] = useState();
  // let writer = '';


  useEffect(() => {
    get(`/api/v1/article_comment/article/${props.article_id}`)
      .then((res) => {
        setComments(res.content);
        setNumTotalPages(res.totalPages);
        console.log('article_comment - res:', res);
        console.log('article_comment - contents:', res.content);
        console.log('article_comment - content[0]:', res.content[0]);
      })
      .catch((err) => {
        console.log(err);
      })
  }, [])


  console.log(newComment);

  return (
    <div className="Comment">
      {/* 댓글 */}
      <section className="mb-5">
        <div className="card bg-light">
          <div className="card-body">

            {/* 댓글 작성 부분 */}
            <form className="mb-4">
              <div className="d-flex">
                <textarea className="form-control" rows="3" placeholder="댓글을 입력해주세요!" onChange={(e) => setNewComment(e.target.value)}></textarea>
                <CreateButton article_id={props.article_id} newComment={newComment}></CreateButton>
              </div>
            </form>

            {/* 댓글 내용 부분 */}
            <div className="d-flex flex-column mb-4">
              <div className="ms-3 w-100">
                <CommentBody comments={comments}></CommentBody>
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
          </div>
        </div>
      </section>



      {/* 대댓글 예시 */}
      {/* <div class="d-flex mt-4">
                <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                <div class="ms-3">
                    <div class="fw-bold">Commenter Name</div>
                    And under those conditions, you cannot establish a capital-market evaluation of that enterprise. You can't get investors.
                </div>
            </div> */}
      {/* 유저 썸네일 */}
      {/* <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div> */}
    </div>
  );
}

export default Comment;
