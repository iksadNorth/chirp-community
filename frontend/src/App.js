import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect} from 'react';

function App() {

  const [name, setName] = useState([]);
  
  useEffect(() => {
    fetch("/api/v1/board/1") 
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setName([data]);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  
  console.log(name);

  return (
    <div className="App">
 
      <a>aaa</a>

      <ul>
        {name.map((item) => (
          <li key={item.id}>{item.name}</li>
        ))}
      </ul>


    </div>
  );
}

export default App;
