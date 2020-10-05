import React, { useState, useEffect } from 'react';
import { Row, Container } from 'react-bootstrap';

import JMoneyMenu from './components/JMoneyMenu';
import JMoneyNewDataForm from './components/JMoneyNewDataForm';
import JMoneyEmptyRow from './components/JMoneyEmptyRow';
import JMoneyTable from './components/JMoneyTable';
import JMoneyJarButton from './components/JMoneyJarButton';

const App = props => {

  const [jUser, setJUser] = useState({jars: [{lastPayments: [{}]}]});

  const [selectedJar, selectJar] = useState({lastPayments: [{}]});

  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);

  useEffect(() => {

    fetch("http://localhost:8080/get")
      .then(res => res.json())
      .then(
        (result) => {
          setJUser(result);
          setIsLoaded(true);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      )
  }, [])


  const jarSelectHandler = jarName => {
    const newJar = jUser.jars.find(j => j.name === jarName);

    selectJar(newJar);
  };

  let content = (
    <div>
      <JMoneyMenu></JMoneyMenu>

      <JMoneyEmptyRow></JMoneyEmptyRow>

      <Container fluid className="mt-5">

        <Row>
          <div className="section">
            {jUser.jars.map((jar, index) => (
              <JMoneyJarButton
                    key={index}
                    jar={jar}
                    onClickHandler={jarSelectHandler}
              ></JMoneyJarButton>
            ))}
          </div>
        </Row>

        <JMoneyEmptyRow></JMoneyEmptyRow>

        <Row>
          <JMoneyNewDataForm selectedJar={selectedJar}></JMoneyNewDataForm>
        </Row>

        <Row>
          <JMoneyTable selectedJar={selectedJar}></JMoneyTable>
        </Row>

      </Container>
    </div>
  );



  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return content;
  }
};

export default App;
