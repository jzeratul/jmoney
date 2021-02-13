import React, { useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

const Forecasts = (props) => {
  const [content] = useState("");
  return (
    <Container>
      <h2>Forecasts!</h2>
      <Row>
        <Col>{content}</Col>
      </Row>
    </Container>
  );
};

export default Forecasts;
