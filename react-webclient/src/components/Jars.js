import React, { useState, useEffect } from "react"
import { Alert, Button, ButtonGroup, Card, CardDeck, Container, Dropdown, Form, InputGroup } from "react-bootstrap"

import JarsService from "../services/JarsService"
import AuthService from "../services/AuthService"
import { IconEdit, IconPlus } from "@tabler/icons"

const Jars = props => {
  const testdata = [
    { name: "necessities", percent: 10, amount: 1000, variant: "dark" },
    { name: "freedom0000", percent: 20, amount: 2000, variant: "info" },
    { name: "play0000000", percent: 30, amount: 3000, variant: "success" },
    { name: "tests000000", percent: 40, amount: 4000, variant: "warning" },
    { name: "test1111111", percent: 50, amount: 5000, variant: "danger" },
  ];

  const [jars, setJars] = useState([])
  const [defaultJarsButton, showDefaultJarsButton] = useState([])
  const [backupJars, setBackupJars] = useState([])
  const [totalPercent, setTotalPercent] = useState(0)
  const [jarsInEditMode, setJarsInEditMode] = useState([])
  const [nameFieldsErrors, setNameFieldsErrors] = useState([])
  const [totalPercentError, setTotalPercentError] = useState(false)
  const [showSaveAllButton, setShowSaveAllButton] = useState(false)

  const getTotalPercentage = (data) => {

    let sum = 0;
    data.forEach(jar => {
      sum = sum + jar.percent
    })
    return sum
  }

  useEffect(() => {

    setJars(testdata)
    setBackupJars(JSON.parse(JSON.stringify(testdata)))
    computeTotalPercentage(testdata)
    showDefaultJarsButton(testdata.length === 0)

    // JarsService.getUserJars().then(
    //   (response) => {
    //     setContent(response.data);
    //   },
    //   (error) => {
    //     const _content = (error.response && error.response.data && error.response.data.message) || error.message || error.toString();

    //     if (403 === error.response.data.status) {
    //       AuthService.logout();
    //       props.history.push("/jmoney/sessionexpired");
    //       window.location.reload();
    //     }
    //     setContent(_content);
    //   }
    // )
  }, [])

  const shouldShowSaveAllButton = () => {
    let currentEdits = 0
    for (var i = 0; i < jarsInEditMode.length; ++i) {
      if (jarsInEditMode[i]) {
        currentEdits++
      }
    }
    return currentEdits > 1
  }

  const computeTotalPercentage = (newcontent) => {
    const p = getTotalPercentage(newcontent);
    setTotalPercent(p)
    setTotalPercentError(100 - p !== 0)
  }

  const onJarColorChange = (idx, newvariant) => {
    let newcontent = [...jars]
    jars[idx].variant = newvariant
    setJars(newcontent)
    setShowSaveAllButton(shouldShowSaveAllButton())
  }

  const onEditJar = (idx) => {
    let newedits = [...jarsInEditMode]
    newedits[idx] = true
    setJarsInEditMode(newedits)
    setShowSaveAllButton(shouldShowSaveAllButton())
  }

  const onPercentChange = (idx, newvalue) => {
    let newcontent = [...jars]
    newcontent[idx].percent = parseInt(newvalue)
    setJars(newcontent)

    computeTotalPercentage(newcontent)
    setShowSaveAllButton(shouldShowSaveAllButton())
  }

  const onNameChange = (idx, newvalue) => {
    let newcontent = [...jars]
    newcontent[idx].name = newvalue

    if ("new" !== newcontent[idx].status) {
      newcontent[idx].status = "updated"
    }

    setJars(newcontent)
    validateNameFields(newcontent)
    setShowSaveAllButton(shouldShowSaveAllButton())
  }

  const onSaveChanges = (idx) => {
    console.log(jars)
  }

  const onCancelEdit = (idx) => {

    let newcontent = [...jars]
    let newedits = [...jarsInEditMode]

    if ("new" !== newcontent[idx].status) {
      newcontent[idx] = backupJars[idx]
      newedits[idx] = false
    } else {
      newcontent.splice(idx, 1)
      newedits.splice(idx, 1)
    }

    computeTotalPercentage(newcontent);
    setJarsInEditMode(newedits)
    setJars(newcontent)
    setShowSaveAllButton(shouldShowSaveAllButton())
    validateNameFields(newcontent)
    showDefaultJarsButton(newcontent.length === 0)
  }

  const newJar = () => {

    let newcontent = [...jars, { name: "newjar", percent: 0, amount: 0, variant: "danger", status: "new" }]

    setJars(newcontent)
    let newedits = [...jarsInEditMode]
    newedits[newcontent.length - 1] = true
    setJarsInEditMode(newedits)
    validateNameFields(newcontent)
    showDefaultJarsButton(false)
  }

  const validateNameFields = (newcontent) => {
    let errors = [];
    for (let i = 0; i < newcontent.length; i++) {
      let identities = 0;
      for (let j = 0; j < newcontent.length; j++) {
        if (newcontent[i].name === newcontent[j].name) {
          identities++;
        }
      }
      if (identities > 1) {
        errors[i] = "Jar name is not unique";
      }
    }
    setNameFieldsErrors(errors)
  }

  const generateDefaultJars = () => {

    const defaultJars = [
      { name: "necessities", percent: 55, amount: 0, variant: "dark", status: "new" },
      { name: "learn", percent: 15, amount: 0, variant: "info", status: "new" },
      { name: "play", percent: 10, amount: 0, variant: "success", status: "new" },
      { name: "long term spending", percent: 15, amount: 0, variant: "warning", status: "new" },
      { name: "give", percent: 5, amount: 0, variant: "danger", status: "new" },
    ];
    const newjars = [...jars, ...defaultJars]

    setJars(newjars)
    setBackupJars(JSON.parse(JSON.stringify(newjars)))
    computeTotalPercentage(newjars)

    showDefaultJarsButton(false)
  }

  const onDeleteJar = (idx) => {
    const newjars = [...jars]
    newjars.remove(idx)

    setJars(newjars)
  }

  return (
    <>
      <Container className="mt-4 mb-4">

        {totalPercentError ?
          <Alert variant="danger">Total percent is {totalPercent}. It must be 100.</Alert>
          :
          (showSaveAllButton && !totalPercentError) ?
            <Button variant="primary" size="lg" onClick={() => onSaveChanges()} >Save all Changes</Button>
            :
            <></>
        }
        <CardDeck>
          {jars.map(function (jar, idx) {
            return (
              <Card key={idx} className="mt-3" style={{ minWidth: '18rem', maxWidth: '18rem' }}>
                <Card.Header>
                  {jarsInEditMode[idx] ?
                    <>
                      <Button variant="outline-primary" size="sm" onClick={() => { onSaveChanges(idx) }}>save</Button>
                      <Button variant="outline-success" size="sm" onClick={() => { onCancelEdit(idx) }}>cancel</Button>
                      <Button variant="outline-danger" className="push-right" size="sm" onClick={() => { onDeleteJar(idx) }}>delete</Button>
                    </>
                    :
                    <>
                      <Button variant="outline-default" size="sm" onClick={() => { onEditJar(idx) }}><IconEdit size={16} color="red" stroke={3} strokeLinejoin="miter" />edit</Button>
                    </>}
                </Card.Header>
                <Card.Body>
                  <Card.Title>
                    {jarsInEditMode[idx] ?
                      <Form.Group>
                        <Form.Control type="text" size="sm" value={jar.name} onChange={(event) => { onNameChange(idx, event.target.value) }}
                          isValid={!nameFieldsErrors[idx]}
                          isInvalid={!!nameFieldsErrors[idx]}
                          maxLength="100"
                          minLength="3"
                        />
                        <Form.Control.Feedback type="invalid">{nameFieldsErrors[idx]}</Form.Control.Feedback>
                      </Form.Group>
                      :
                      <>{jar.name}</>}
                  </Card.Title>
                  <Card.Subtitle className="mb-2 text-muted">
                    {jarsInEditMode[idx] ?
                      <>
                        <InputGroup className="mb-2" size="sm">
                          <Form.Control type="number" value={jar.percent} onChange={(event) => { onPercentChange(idx, event.target.value) }}
                            min="0" max="100" width="100" />
                          <InputGroup.Append>
                            <InputGroup.Text>%</InputGroup.Text>
                          </InputGroup.Append>
                        </InputGroup>

                        <Dropdown as={ButtonGroup} size="sm">
                          <Button variant={jar.variant} size="md">   </Button>
                          <Dropdown.Toggle split variant={jar.variant} id="dropdown-split-basic" size="sm" />
                          <Dropdown.Menu>
                            <Dropdown.Item as="button" variant="danger">Test</Dropdown.Item>
                            <Dropdown.Item onClick={() => onJarColorChange(idx, "primary")}   ><Button variant="primary" size="md" >1</Button></Dropdown.Item>
                            <Dropdown.Item onClick={() => onJarColorChange(idx, "secondary")} ><Button variant="secondary" size="md" >2</Button></Dropdown.Item>
                            <Dropdown.Item onClick={() => onJarColorChange(idx, "success")}   ><Button variant="success" size="md" >3</Button></Dropdown.Item>
                            <Dropdown.Item onClick={() => onJarColorChange(idx, "warning")}   ><Button variant="warning" size="md" >4</Button></Dropdown.Item>
                            <Dropdown.Item onClick={() => onJarColorChange(idx, "dark")}      ><Button variant="dark" size="md" >5</Button></Dropdown.Item>
                            <Dropdown.Item onClick={() => onJarColorChange(idx, "info")}      ><Button variant="info" size="md" >6</Button></Dropdown.Item>
                            <Dropdown.Item onClick={() => onJarColorChange(idx, "danger")}    ><Button variant="danger" size="md" >7</Button></Dropdown.Item>
                          </Dropdown.Menu>
                        </Dropdown>
                      </>
                      :
                      <>
                        <p>{jar.percent}%</p>
                        <Button variant={jar.variant} size="lg"></Button>
                      </>}
                  </Card.Subtitle>
                </Card.Body>
              </Card>
            )
          })}

          <Card border="gray" style={{ minWidth: '18rem', maxWidth: '18rem' }} bg='light' className="mt-3">
            <Card.Body>
              <Button style={{ width: '100%', height: '100%' }} variant="default" size="lg" onClick={() => newJar()} >
                <IconPlus size={50} stroke={3} strokeLinejoin="miter" />
              </Button>
            </Card.Body>
          </Card>

          {defaultJarsButton ? <Card border="red" style={{ minWidth: '18rem', maxWidth: '18rem' }} bg='light' className="mt-3">
            <Card.Body>
              <Button style={{ width: '100%', height: '100%' }} variant="warning" size="lg" onClick={() => generateDefaultJars()} >
                <IconPlus size={50} stroke={3} strokeLinejoin="miter" />
                Generate Default Jars
              </Button>
            </Card.Body>
          </Card> : <></>}
        </CardDeck>

      </Container>
    </ >
  )
}

export default Jars
