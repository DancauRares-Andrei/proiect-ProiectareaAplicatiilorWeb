import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from './LoginPage';
import LayoutPage from './LayoutPage';
import HomePage from './HomePage';
import React, { useState } from 'react';
import './App.css';

function App() {
  const [token, setToken] = useState(null);
  const [rol, setRol] = useState('');
  const [uid, setUid] = useState('');
  const resetProps=()=>{
    setToken('');
    setRol('');
    setUid('');
  };
  return (
    <BrowserRouter>
    <Routes>
    <Route path="/" element={<LayoutPage />}>
    <Route index element={<LoginPage setToken={setToken} token={token} uid={uid} setUid={setUid} rol={rol} setRol={setRol} resetProps={resetProps}/>} />
    <Route path="/dashboard" element={<HomePage token={token} resetProps={resetProps}/>} />
    </Route>
    </Routes>
    </BrowserRouter>
  );
}

export default App;
