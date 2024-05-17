import React, { useState } from 'react';
import { Link } from 'react-router-dom';
const LoginPage = (props) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorLoginMessage, setErrorLoginMessage] = useState('');
  
  const handleLogin = async () => {
  if (!username || !password) {
      console.error('Username și parolă sunt obligatorii');
      return;
    }

  try {
    const response = await fetch('http://localhost:8080/uac/login', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type':'application/json'
      },
      body: JSON.stringify({"username":username,"password":password}),
    });
    if(response.status===403){
        setErrorLoginMessage("Utilizator sau parolă incorecte!");
    }
    else if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    else{
    setErrorLoginMessage('');
    const data = await response.json();
    props.setToken(data.token);
    }
  } catch (err) {
    console.error('Error:', err);
  }
};
  return (//render
  
    <div className="container mt-5">
  <h2>Pagina de autentificare</h2>
  {errorLoginMessage && <p className="text-danger">{errorLoginMessage}</p>}
  {props.token ? (
    <div>
    <p className="text-success">Autentificare cu succes.</p>
    <Link to="/dashboard" className="btn btn-success">Acasă</Link>
    </div>
  ) : (
  <div>
    <Link to="/create" className="text-primary">Nu ai cont? Creează unul apăsând aici!</Link>
    <form onSubmit={(e) => { e.preventDefault(); handleLogin(); }} className="mb-3">
      <div className="mb-3">
        <label htmlFor="username" className="form-label">
          Username:
          <input type="text" id="username" className="form-control" value={username} onChange={(e) => setUsername(e.target.value)} required />
        </label>
      </div>
      <div className="mb-3">
        <label htmlFor="password" className="form-label">
          Password:
          <input type="password" id="password" className="form-control" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </label>
      </div>
      <button type="submit" className="btn btn-primary">Login</button>
    </form>
    </div>
  )}
</div>

  );
};
export default LoginPage;

