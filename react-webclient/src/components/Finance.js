import React, { useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

const Finance = (props) => {
  const [content] = useState("");
  return (
    <Container>
      <h2>Finance!</h2>
      <Row>
        <Col>{content}</Col>
      </Row>
    </Container>
  );
};

export default Finance;
