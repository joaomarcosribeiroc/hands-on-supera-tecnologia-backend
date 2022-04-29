import axios from 'axios'; 
//wrapper no axios. permite melhor abstração da funcionalidade. 
import React, { useState,  useEffect } from 'react';

const GameList = () => {
  
  const [games, setGames] = useState([]);
  
  const loadGames = () => {
    // URL como variável de ambiente
    axios.get("http://localhost:8080/produtos")
    .then(function (response) {
      setGames(response.data);
    })
    .catch(function (error) {
      console.log(error);
    })
  } 

  useEffect(loadGames, []);
  //Colocar chamadas do useEffects para arquivo separado

  return (
      <ul>
        <>
          {//Criar componente para o objeto game para adicionar validações e comportamentos.
          // Separation of Concerns
            games.map((game) => (
              <div key={game.id}>  
                <h1>{game.name}</h1>
                <h2>{game.score}</h2>
                <img src={`http://localhost:8080/${game.image}`}></img>
              </div>
            ))
          }
        </>
      </ul>
  );
}

export default GameList;

//Tentar fazer carrinho com Redux para facilitar atualização/compartilhamento de estado entre os componentes.

