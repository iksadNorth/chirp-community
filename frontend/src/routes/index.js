import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import * as c from '../ComponentsUsers';
import * as b from '../ComponentsBoard';
import Header from '../Header';

import RouteProxy from './RouteProxy';

export default function RouteComponent() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>

        <Route path="/login" element={
          <RouteProxy>
          <c.Login />
          </RouteProxy>
        } />

        <Route path="/signup" element={
          <RouteProxy>
          <c.SignUp />
          </RouteProxy>
        } />

        <Route path="/mypage" element={
          <RouteProxy>
          <c.MyPage />
          </RouteProxy>
        } />

        <Route path="/user/:userId" element={
          <RouteProxy>
          <c.UserPage />
          </RouteProxy>
        } />

        <Route path="/update/board" element={
          <RouteProxy>
          <c.BoardDashBoard />
          </RouteProxy>
        } />

        <Route path="/" element={
          <RouteProxy>
          <b.MainPage />
          </RouteProxy>
        } />

        <Route path="/board/*" element={
          <RouteProxy>
          <b.BoardPage />
          </RouteProxy>
        } />

        <Route path="/article/*" element={
          <RouteProxy>
          <b.ArticlePage />
          </RouteProxy>
        } />

        <Route path="/board/:boardId/createArticle" element={
          <RouteProxy>
          <b.ArticleCreatePage />
          </RouteProxy>
        } />

        <Route path="board/:boardId/article/:articleId/updateArticle" element={
          <RouteProxy>
          <b.ArticleCreatePage />
          </RouteProxy>
        } />


      </Routes>
    </BrowserRouter>
  );
}
