import React, { useState, useEffect } from 'react';
const HomePage = (props) => {
  const [score, setScore] = useState('');
  const [epoch, setEpoch] = useState('');
  const [question, setQuestion] = useState('');
  const [answerA,setAnswerA] = useState('');
  const [answerB,setAnswerB] = useState('');
  const [answerC,setAnswerC] = useState('');
  const [answerD,setAnswerD] = useState('');
  const [correctAnswer,setCorrectAnswer] = useState('');
  const [selectedAnswer, setSelectedAnswer] = useState('');
  const [course, setCourse] = useState('');
  const [incorrectAnswer,setIncorrectAnswer] = useState('');
  const [newPassword,setNewPassword] = useState('');
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');
  const epochs=['Preistorica','Antica','Medievala','Moderna','Contemporana'];
  if(props.token === undefined || props.token===''){
    props.resetProps();
    window.location.href = '/';
  }
  const GetMessages = async () => {
      const response = await fetch(`http://localhost:8080/chat`, {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${props.token}`,
          },
        });
      const chatData = await response.json();
      setMessages(chatData);
  };
    const fetchData = async () => {
      try {
        var response = await fetch(`http://localhost:8080/details`, {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${props.token}`,
          },
        });
        if (!response.ok) {
          // Tratează cazul în care cererea nu este reușită
          console.error('Cererea nu a reușit:', response.statusText);
          if(response.status===403){
            props.resetProps();window.location.href = '/';
          }
          return;
        }
        const studentData = await response.json();
        setScore(studentData.score);
        setEpoch(studentData.epoch_name);
        response = await fetch(`http://localhost:8080/lecture/`+studentData.epoch_name.toLowerCase(), {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${props.token}`,
          },
        });
        if (!response.ok) {
          console.error('Cererea nu a reușit:', response.statusText);
          return;
          }        
        const questionData = await response.json(); 
        setQuestion(questionData.questionContent);
        setAnswerA(questionData.answerA);
        setAnswerB(questionData.answerB);
        setAnswerC(questionData.answerC);
        setAnswerD(questionData.answerD);
        setCorrectAnswer(questionData.correctAnswer);
        setCourse(questionData.content);
      } catch (error) {
        console.error('Eroare în timpul cererii:', error);
      }
    };  
    useEffect(() => {
    GetMessages();
  }, []);
  useEffect(() => {
    fetchData();
  }, [score]);
  const logoutUser = async () => {
      if (props.token) {
        try {
          const response = await fetch('http://localhost:8080/uac/logout', {
            method: 'POST',
            headers: {
              'Authorization': `Bearer ${props.token}`,
            },
          });
          if(response.status===401 || response.status===403){
            props.resetProps();
            window.location.href = '/login';
          }
          if (response.status===200) {
             props.resetProps();
            window.location.href = '/';
          } else {
            console.error('Logout failed:', response.statusText);
          }
        } catch (error) {
          console.error('Error during logout:', error);
        }
      } else {
        props.resetProps();
        window.location.href = '/';
      }
    };
  const handleAnswerSelect = (event) => {
    setSelectedAnswer(event.target.value);
  };
  const checkAnswer = async () => {
    if(selectedAnswer==='')
        return;
    if(selectedAnswer!==correctAnswer){
        setIncorrectAnswer("Răspuns greșit!");
        return;
    }   
    setIncorrectAnswer("");
    var response = await fetch('http://localhost:8080/details/new-score', {
      method: 'PUT',
      headers: {
        'Authorization': `Bearer ${props.token}`,
        'Content-Type':'application/json'
      },
      body: 100,
    });
    if(response.status!==204)
        return;
    response = await fetch('http://localhost:8080/details/new-epoch', {
      method: 'PUT',
      headers: {
        'Authorization': `Bearer ${props.token}`,
        'Content-Type':'application/json'
      },
      body: epochs[(epochs.indexOf(epoch)+1)%epochs.length],
    });
    if(response.status!==204)
        return;
    setScore(score+100);
  };
  const changePassword = async () => {
    if(newPassword==='')
        return;
    var response = await fetch('http://localhost:8080/uac/accounts', {
      method: 'PUT',
      headers: {
        'Authorization': `Bearer ${props.token}`,
        'Content-Type':'application/json'
      },
      body: newPassword,
    });
    if(response.status!==200)
        return;
    alert("Parola a fost modificată cu succes!");
  };
  const handleInputChange = (event) => {
    setNewMessage(event.target.value);
  };
  const handleSendMessage = async () => {
    if (newMessage.trim() !== '') {
      const response = await fetch('http://localhost:8080/chat/new-message', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        Authorization: `Bearer ${props.token}`,
        'Content-Type':'application/json'
      },
      body: newMessage,
    });
      if(response.ok)
        GetMessages();
    }
  };
  
    return(
    <div style={{ display: 'flex' }}>
    <div className="container mt-5">
        <div className="mb-4">
          <p>Scor: {score}</p>
          <p>Epocă: {epoch}</p>
          <p>{course}</p>
          <p>{question}</p>
          <span className="text-danger">{incorrectAnswer}</span>
          <form>
        <label>
          <input
            type="radio"
            name="answer"
            value={answerA}
            checked={selectedAnswer === answerA}
            onChange={handleAnswerSelect}
          />
          &nbsp; {answerA}
        </label>
        <br />
        <label>
          <input
            type="radio"
            name="answer"
            value={answerB}
            checked={selectedAnswer === answerB}
            onChange={handleAnswerSelect}
          />
          &nbsp; {answerB}
        </label>
        <br />
        <label>
          <input
            type="radio"
            name="answer"
            value={answerC}
            checked={selectedAnswer === answerC}
            onChange={handleAnswerSelect}
          />
          &nbsp; {answerC}
        </label>
        <br />
        <label>
          <input
            type="radio"
            name="answer"
            value={answerD}
            checked={selectedAnswer === answerD}
            onChange={handleAnswerSelect}
          />
          &nbsp; {answerD}
        </label>
      </form><br />
      <button class="btn btn-primary" onClick={checkAnswer}>Verifică răspunsul</button>
        </div>
        <h4>Actualizare parolă</h4>
        <form onSubmit={(e) => { e.preventDefault(); changePassword(); }}>
        <div className="mb-3">
              <label htmlFor="newPassword" className="form-label">
                Parolă nouă:
                <input type="password" id="newPassword" className="form-control" value={newPassword} onChange={(e) => setNewPassword(e.target.value)} />
              </label>
            </div>
        <button type="submit" className="btn btn-primary">Actualizare parolă</button>
          </form><br />     
        <button className="btn btn-secondary" onClick={logoutUser}>Deconectare</button>
    </div>
    <div style={{ flex: 1 }}>
          <div className="chat-window" style={{ height: '200px', overflow:'auto' }}>
        {messages.map((message, index) => (
          <div key={index} className={`message ${message.username}`}>
            {message.username}:{message.content}
          </div>
        ))}
      </div>
      <div className="input-area">
        <input
          type="text"
          placeholder="Scrie un mesaj..."
          value={newMessage}
          onChange={handleInputChange}
        />
        <button onClick={handleSendMessage} className="btn btn-primary">Trimite</button>
      </div>
        </div>
    </div>
    );
};

export default HomePage;
