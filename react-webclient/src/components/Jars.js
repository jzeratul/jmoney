import React, { useState, useEffect } from "react";
import { Col, Container, Row } from "react-bootstrap";

import JarsService from "../services/JarsService";
import AuthService from "../services/AuthService";

const Jars = (props) => {
  const [content, setContent] = useState("");

  useEffect(() => {
    JarsService.getUserJars().then(
      (response) => {
        setContent(response.data);
      },
      (error) => {
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        if (403 === error.response.data.status) {
          AuthService.logout();
          props.history.push("/jmoney/sessionexpired");
          window.location.reload();
        }
        setContent(_content);
      }
    );
  });

  return (
    <main>
      <Container>
        <h2>Jars!</h2>
        <Row>
          <Col>{content}</Col>
        </Row>
      </Container>
    </main>
  );
};

export default Jars;
