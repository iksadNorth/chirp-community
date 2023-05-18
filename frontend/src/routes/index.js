import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import * as c from '../ComponentsUsers';

export default function RouteComponent() {
  return (
    <BrowserRouter>
      <c.Header />
      <Routes>

        <Route path="/login" element={
          <c.Login />
        } />
        
      </Routes>
      <c.Footer />
    </BrowserRouter>
  );
}
