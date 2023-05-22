import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import * as c from '../ComponentsUsers';
import * as b from '../ComponentsBoard';
import Header from '../Header';

export default function RouteComponent() {
  return (
    <BrowserRouter>
      <Header/>
      <Routes>

        <Route path="/login" element={
          <c.Login />
        } />
        
        <Route path="/signup" element={
          <c.SignUp />
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
        
      </Routes>    
      <c.Footer />
    </BrowserRouter>
  );
}
