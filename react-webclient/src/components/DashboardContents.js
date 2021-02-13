import React, { useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

const DashboardContents = (props) => {
  const [content] = useState("");

  return (
    <Container>
      <h2>DashboardContents!</h2>
      <Row>
        <Col>{content}</Col>
      </Row>
    </Container>
  );
};

export default DashboardContents;
