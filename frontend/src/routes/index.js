import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import * as c from '../ComponentsUsers';
import * as b from '../ComponentsBoard';
import Header from '../Header';

export default function RouteComponent() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>

        <Route path="/login" element={
          <c.Login />
        } />

        <Route path="/signup" element={
          <c.SignUp />
        } />

        <Route path="/mypage" element={
          <c.MyPage />
        } />

        <Route path="/user/:userId" element={
          <c.UserPage />
        } />

        <Route path="/update/board" element={
          <c.BoardDashBoard />
        } />

        <Route path="/" element={
          <b.MainPage />
        } />

        <Route path="/board/*" element={
          <b.BoardPage />
        } />

        <Route path="/article/*" element={
          <b.ArticlePage />
        } />

        <Route path="/board/:boardId/createArticle" element={
          <b.ArticleCreatePage />
        } />

        <Route path="board/:boardId/article/:articleId/updateArticle" element={
          <b.ArticleCreatePage />
        } />


      </Routes>
    </BrowserRouter>
  );
}
