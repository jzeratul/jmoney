import React, { useState, useEffect } from "react";
import { Alert, Button, ButtonGroup, ButtonToolbar, Card, Col, Container, Dropdown, DropdownButton, Form, FormControl, InputGroup, Row, Table } from "react-bootstrap";

import JarsService from "../services/JarsService";
import AuthService from "../services/AuthService";
import { IconAtom, IconEdit, IconUpload, IconFolderPlus, IconArrowBack } from "@tabler/icons";

const Jars = props => {
  const testdata = [
    { name: "necessities", percent: 10, amount: 1000, variant: "dark" },
    { name: "freedom0000", percent: 20, amount: 2000, variant: "info" },
    { name: "play0000000", percent: 30, amount: 3000, variant: "success" },
    { name: "tests000000", percent: 40, amount: 4000, variant: "warning" },
    { name: "test1111111", percent: 50, amount: 5000, variant: "danger" },
  ];

  const [backup, setBackup] = useState([])
  const [content, setContent] = useState([])
  const [edits, setEdits] = useState([])
  const [totalPercent, setTotalPercent] = useState(0)
  const [hasError, setError] = useState(false)
  const [mustSave, setMustSave] = useState(false)

  const getTotalPercentage = (data) => {

    let sum = 0;
    data.forEach( jar => {
      sum = sum + jar.percent
    })
    return sum
  }

  useEffect(() => {

    setContent(testdata);
    setBackup(testdata)

    const p = getTotalPercentage(testdata);
    setTotalPercent(p)
    setError(100 !== p)

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
    // );
  }, []);

  const verifyIfMustSave = () => {

    for (var i = 0; i < content.length; ++i) {
      if(content[i].status === "new" || content[i].status === "deleted" || content[i].status === "updated") {
        return true
      }
    }
    return false
  }

  const jarColorChange = (idx, newvariant) => {
    let newcontent = [...content]
    content[idx].variant = newvariant
    setContent(newcontent)
  }

  const edit = (idx) => {
    let newedits = [...edits]
    newedits[idx] = true
    setEdits(newedits)
  }

  const onPercentChange = (idx, newvalue) => {
    let newcontent = [...content]
    newcontent[idx].percent = parseInt(newvalue)
    setContent(newcontent)
    setMustSave(true)

    const p = getTotalPercentage(newcontent);
    setTotalPercent(p)
    setError(100-p !== 0)
  }

  const onNameChange = (idx, newvalue) => {
    let newcontent = [...content]
    newcontent[idx].name = newvalue
    setContent(newcontent)
    setMustSave(true)
  }

  const saveChanges = () => {
    console.log(content)
  }

  const cancel = (idx) => {

    let newcontent = [...content]
    newcontent[idx] = backup[idx]

    let newedits = [...edits]
    newedits[idx] = false
    setEdits(newedits)

    setContent(newcontent)
    setMustSave(verifyIfMustSave())
  }

  return (
    <main>

      <Container className="mt-3">
        <Row>
          <Col>
            <Table striped bordered hover>
              <thead>
                <tr>
                  <th>%</th>
                  <th>Jar Name</th>
                  <th>Color</th>
                  <th>Available</th>
                  <th>
                    <IconFolderPlus size={36} color="red" stroke={3} strokeLinejoin="miter" />
                  </th>
                </tr>
              </thead>

              <tbody>
                {content.map(function (jar, idx) {
                  return (
                    <tr key={idx}>
                      <td>
                        { edits[idx] ?
                          <Form.Control type="number" size="sm" value={jar.percent} onChange={(event) => {onPercentChange(idx, event.target.value)}}/>
                        :
                          <p>{jar.percent}</p>
                        }
                      </td>

                      <td>
                        { edits[idx] ?
                          <Form.Control type="text" size="sm" value={jar.name} onChange={(event) => {onNameChange(idx, event.target.value)}}/>
                        :
                          <p>{jar.name}</p>
                        }
                      </td>
                      <td>
                        { edits[idx] ?

                          <Dropdown as={ButtonGroup} size="sm">
                            <Button variant={jar.variant} size="sm">   </Button>

                            <Dropdown.Toggle split variant={jar.variant} id="dropdown-split-basic" size="sm" />

                            <Dropdown.Menu>
                              <Dropdown.Item as="button" variant="danger">Test</Dropdown.Item>
                              <Dropdown.Item onClick={() => jarColorChange(idx, "primary")}   ><Button variant="primary"    size="sm" >1</Button></Dropdown.Item>
                              <Dropdown.Item onClick={() => jarColorChange(idx, "secondary")} ><Button variant="secondary"  size="sm" >2</Button></Dropdown.Item>
                              <Dropdown.Item onClick={() => jarColorChange(idx, "success")}   ><Button variant="success"    size="sm" >3</Button></Dropdown.Item>
                              <Dropdown.Item onClick={() => jarColorChange(idx, "warning")}   ><Button variant="warning"    size="sm" >4</Button></Dropdown.Item>
                              <Dropdown.Item onClick={() => jarColorChange(idx, "dark")}      ><Button variant="dark"       size="sm" >5</Button></Dropdown.Item>
                              <Dropdown.Item onClick={() => jarColorChange(idx, "info")}      ><Button variant="info"       size="sm" >6</Button></Dropdown.Item>
                              <Dropdown.Item onClick={() => jarColorChange(idx, "danger")}    ><Button variant="danger"     size="sm" >7</Button></Dropdown.Item>
                            </Dropdown.Menu>
                          </Dropdown>
                          :
                          <p><Button variant={jar.variant} size="md"></Button></p>
                        }
                      </td>
                      <td>{jar.amount}</td>
                      <td>
                      { edits[idx] ?
                        <Button variant="outline-danger" size="sm" onClick={() => {cancel(idx)}}><IconArrowBack size={16} color="blue" stroke={3} strokeLinejoin="miter" />cancel</Button>
                        :
                        <Button variant="outline-default" size="sm" onClick={() => {edit(idx)}}><IconEdit size={16} color="red" stroke={3} strokeLinejoin="miter" />edit</Button>
                      }
                      </td>
                    </tr>
                  );
                })}
                <tr>
                  <td colSpan="3">
                      {hasError ?
                        <Alert variant="danger">Total percent must be 100%. Current total is {totalPercent}.</Alert>
                      :
                        <Alert variant="light">Total percent is 100%</Alert>
                      }
                  </td>
                  <td colSpan="2">
                    {mustSave && !hasError ? <Button variant="primary" size="lg" onClick={() => saveChanges()} >Save Changes</Button> : <div/>}
                  </td>
                </tr>
              </tbody>
            </Table>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default Jars;
