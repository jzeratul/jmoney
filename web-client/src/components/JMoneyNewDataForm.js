import React, { useState } from 'react';

import { Col, InputGroup, Button, Form, FormControl } from 'react-bootstrap';

const JMoneyNewDataForm = props => {

  const [selectedJar, addItem] = useState(props.selectedJar);

  return (
      <Form>
        <Form.Row className="align-items-center">
          <Col xs="auto">
            <Form.Label htmlFor="inlineFormInput" srOnly>
              Name
            </Form.Label>
            <Form.Control
              className="mb-2"
              id="inlineFormInput"
              placeholder="Jane Doe"
            />
          </Col>
          <Col xs="auto">
            <Form.Label htmlFor="inlineFormInputGroup" srOnly>
              Username
            </Form.Label>
            <InputGroup className="mb-2">
              <InputGroup.Prepend>
                <InputGroup.Text>@</InputGroup.Text>
              </InputGroup.Prepend>
              <FormControl id="inlineFormInputGroup" placeholder="Username" />
            </InputGroup>
          </Col>
          <Col xs="auto">
            <Button type="submit" className="mb-2">
              save
            </Button>
          </Col>
        </Form.Row>
      </Form>
  );
};

export default JMoneyNewDataForm;