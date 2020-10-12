import React from 'react';

import { Col, InputGroup, Button, Form, FormControl } from 'react-bootstrap';
import { useForm } from "react-hook-form";

const JMoneyForm = props => {

  const { register, handleSubmit } = useForm();

  const onSubmit = data => console.log(data);

  return (
      <Form onSubmit={handleSubmit(onSubmit)}>
        <Form.Row className="align-items-center">
          <Col xs="auto">
            <Form.Control
              className="mb-6"
              placeholder="Item"
              name="reason"
              ref={register({ required: true, maxLength: 30 })}
            />
          </Col>
          <Col xs="auto">
            <InputGroup className="mb-3">
              <InputGroup.Prepend>
                <InputGroup.Text>$</InputGroup.Text>
              </InputGroup.Prepend>
              <FormControl
                placeholder="Price"
                name="amount"
                ref={register({ required: true, minLength: 1, maxLength: 20 })}/>
            </InputGroup>
          </Col>
          <Col xs="auto">
            <Button type="submit" className="mb-3">
              >>
            </Button>
          </Col>
        </Form.Row>
      </Form>
  );
};

export default JMoneyForm;