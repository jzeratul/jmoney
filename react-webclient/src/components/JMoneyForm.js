import React from 'react'

import { Col, InputGroup, Button, Form, FormControl } from 'react-bootstrap'
import { useForm } from "react-hook-form"

const JMoneyForm = props => {

  const { register, handleSubmit } = useForm()

  const onSubmit = data => {
    props.formSubmitted(data);
  };

  return (
      <Form onSubmit={handleSubmit(onSubmit)}>
        <Form.Row className="align-items-center">
          <Col xs="6">
            <Form.Control
              placeholder="Item"
              name="reason"
              ref={register({ required: true, maxLength: 30 })}
            />
          </Col>
          <Col xs="4">
            <InputGroup>
              <InputGroup.Prepend>
                <InputGroup.Text>$</InputGroup.Text>
              </InputGroup.Prepend>
              <FormControl
                placeholder="Price"
                name="amount"
                ref={register({ required: true, minLength: 1, maxLength: 20 })}/>
            </InputGroup>
          </Col>
          <Col xs="2">
            <Button type="submit">
              >>
            </Button>
          </Col>
        </Form.Row>
      </Form>
  )
}

export default JMoneyForm