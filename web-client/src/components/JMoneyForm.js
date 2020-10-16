import React from 'react'

import { Col, InputGroup, Button, Form, FormControl } from 'react-bootstrap'
import { useForm } from "react-hook-form"

const JMoneyForm = props => {

  const { register, handleSubmit } = useForm()

  const onSubmit = data => {
    console.log("We are here + strange")
    props.formSubmitted({
      reason: data.reason,
      amount: data.amount,
      paymentDate: new Date(),
      createdAt: new Date()
    })
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