import React, { useState, useEffect } from 'react'
import { Row, Container } from 'react-bootstrap'

import JMoneyMenu from './components/JMoneyMenu'
import JMoneyEmptyRow from './components/JMoneyEmptyRow'
import JMoneyTable from './components/JMoneyTable'
import JMoneyJarButtons from './components/JMoneyJarButtons'
import JMoneyForm from './components/JMoneyForm'

const Dashboard = props => {

  const [jUser, setJUser] = useState({jars: [{lastPayments: []}]})
  const [selectedJar, selectJar] = useState(jUser.jars[0])

  const [error, setError] = useState(null)
  const [isLoaded, setIsLoaded] = useState(false)

  useEffect(() => {

    fetch("http://localhost:2020/get")
      .then(res => res.json())
      .then(
        (result) => {
          setJUser(result)
          selectJar(result.jars[0])
          setIsLoaded(true)
        },
        (error) => {
          setIsLoaded(true)
          setError(error)
        }
      )
  }, [])

  const jarSelectHandler = jar => {
    selectJar(jar)
  }

  const formSubmitted = data => {

    const item = {
      reason: data.reason,
      amount: data.amount,
      paymentDate: new Date(),
      createdAt: new Date()
    }

    const thejar = jUser.jars.find(j => j.name === selectedJar.name)

    thejar.lastPayments.push(item);

    const saveUser = jUser

    fetch('http://localhost:2020/jmoney/jars', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(saveUser)
    })

    setJUser(saveUser)
  }

  let content = (
    <div>
      <JMoneyMenu currentUser={jUser}></JMoneyMenu>

      <JMoneyEmptyRow/>

      <Container fluid className="mt-5">

        <Row>
          <JMoneyJarButtons jars={jUser.jars} jarSelectHandler={jarSelectHandler} />
        </Row>

        <JMoneyEmptyRow/>

        <Row>
          <JMoneyForm formSubmitted={formSubmitted}></JMoneyForm>
        </Row>

        <JMoneyEmptyRow/>

        <Row>
          <JMoneyTable selectedJar={selectedJar}></JMoneyTable>
        </Row>

      </Container>
    </div>
  )

  if (error) {
    return <div>Error: {error.message}</div>
  } else if (!isLoaded) {
    return <div>Loading...</div>
  } else {
    return content
  }
}

export default Dashboard
