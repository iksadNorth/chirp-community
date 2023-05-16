import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import * as c from '../components';

export default function RouteComponent() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/login" element={
          <c.Login />
        } />
        
      </Routes>
    </BrowserRouter>
  );
}
