import React, { useState } from 'react';
import { Link } from 'react-router-dom';
const CreateUserPage = (props) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [errorCreateMessage, setErrorCreateMessage] = useState('');
  
  const handleLogin = async () => {
  if (!username || !password || !email) {
      setErrorCreateMessage('Username, parolă și email obligatorii!');
      return;
    }
    setErrorCreateMessage('');
  try {
    const response = await fetch('http://localhost:8080/uac/new-user', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type':'application/json'
      },
      body: JSON.stringify({"username":username,"password":password,"email":email}),
    });
   if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
   window.location.href = '/';
  } catch (err) {
    console.error('Error:', err);
  }
};
  return (//render
  
    <div className="container mt-5">
  <h2>Pagina de creare cont</h2>
  {errorCreateMessage && <p className="text-danger">{errorCreateMessage}</p>}
  {props.token ? (
    <div>
    <p className="text-success">Autentificare cu succes.</p>
    <Link to="/dashboard" className="btn btn-success">Acasă</Link>
    </div>
  ) : (
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
      <div className="mb-3">
        <label htmlFor="email" className="form-label">
          Email:
          <input type="email" id="email" className="form-control" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </label>
      </div>
      <button type="submit" className="btn btn-primary">Creare cont</button>
    </form>
  )}
</div>

  );
};
export default CreateUserPage;

