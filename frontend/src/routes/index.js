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
          <b.Main />
        } />

        <Route path="/board/*" element={
          <b.Board />
        } />

        <Route path="/article/*" element={
          <b.Article />
        } />
        
      </Routes>    
      <c.Footer />
    </BrowserRouter>
  );
}
