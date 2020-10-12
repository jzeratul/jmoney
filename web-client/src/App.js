import React, { useState, useEffect } from 'react';
import { Row, Container } from 'react-bootstrap';

import JMoneyMenu from './components/JMoneyMenu';
import JMoneyEmptyRow from './components/JMoneyEmptyRow';
import JMoneyTable from './components/JMoneyTable';
import JMoneyJarButton from './components/JMoneyJarButton';
import JMoneyForm from './components/JMoneyForm';

const App = props => {

  const [jUser, setJUser] = useState({jars: []});
  const [payments, setPayments] = useState([]);
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);

  useEffect(() => {

    fetch("http://localhost:8080/get")
      .then(res => res.json())
      .then(
        (result) => {

          console.log(result);
          setJUser(result);
          setPayments(result.jars[0].lastPayments);
          setIsLoaded(true);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      )
  }, [])

  const jarSelectHandler = jar => {
    console.log("Jar Clicked: " + jar.name)
    setPayments(jar.lastPayments);
  };

  // useEffect(() => {
  //   setPayments(selectedJar.lastPayments);
  // }, [selectedJar])


  const formSubmitted = item => {
    console.log("Add Item: " + JSON.stringify(item))
  };

  let content = (
    <div>
      <JMoneyMenu currentUser={jUser}></JMoneyMenu>

      <JMoneyEmptyRow/>

      <Container fluid className="mt-5">

        <Row>
          <Container>
              <div className="section">
                {jUser.jars.map((jar, index) => (
                  <JMoneyJarButton
                        key={index}
                        jar={jar}
                        onClickHandler={jarSelectHandler}
                  ></JMoneyJarButton>
                ))}
              </div>
          </Container>
        </Row>

        <JMoneyEmptyRow/>

        <Row>
          <JMoneyForm formSubmitted={formSubmitted}></JMoneyForm>
        </Row>

        <JMoneyEmptyRow/>

        <Row>
          <JMoneyTable payments={payments}></JMoneyTable>
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
