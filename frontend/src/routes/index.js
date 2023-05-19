import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import * as c from '../ComponentsUsers';
import Header from '../Header';

export default function RouteComponent() {
  return (
    <BrowserRouter>
      <Header/>
      <Routes>

        <Route path="/login" element={
          <c.Login />
        } />
        
        <Route path={process.env.REACT_APP_SIGNUP_URL} element={
          <c.SignUp />
        } />
        
      </Routes>
      <c.Footer />
    </BrowserRouter>
  );
}
