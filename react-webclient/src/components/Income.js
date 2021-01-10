import React, { useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

const Income = (props) => {
  const [content] = useState("");
  return (
    <Container>
      <h2>Income!</h2>
      <Row>
        <Col>{content}</Col>
      </Row>
    </Container>
  );
};

export default Income;
