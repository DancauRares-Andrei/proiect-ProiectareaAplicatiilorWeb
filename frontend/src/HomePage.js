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
  if(props.token === undefined || props.token===''){
    props.resetProps();
    window.location.href = '/';
  }
  useEffect(() => {//componentDidMount 
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
    fetchData();
  },[props]);
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
  const checkAnswer = () => {
    if(selectedAnswer==='')
        return;
    console.log(correctAnswer)
    if(selectedAnswer!==correctAnswer){
        setIncorrectAnswer("Răspuns greșit!");
        return;
    }   
    setIncorrectAnswer("");
  };
    return(
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
        <button className="btn btn-secondary" onClick={logoutUser}>Deconectare</button>
    </div>
    );
};

export default HomePage;
