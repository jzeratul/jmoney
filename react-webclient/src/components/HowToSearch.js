import React, { useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

const HowToSearch = (props) => {
  const [content] = useState("");
  return (
    <Container>
      <h2>HowToSearch!</h2>
      <Row>
        <Col>{content}</Col>
      </Row>
    </Container>
  );
};

export default HowToSearch;
