import { IconAtom2 } from "@tabler/icons";
import "bootstrap/dist/css/bootstrap.min.css";
import React from "react";

import { Button, Card, Col, Container, Row, Image } from "react-bootstrap";
import JMoneyLogo from "../images/logo.svg";

const LandingPage = () => {
  return (
    <main>
      <Container>
        <Row>
          <Col>
            <Card style={{ width: "18rem" }}>
              <Card.Header>
                <Image src={JMoneyLogo} rounded fluid className="mr-3" />
              </Card.Header>
              <Card.Body>
                <Card.Title>Get in</Card.Title>
                <Card.Text>your account</Card.Text>
                <Button variant="primary" href="/jmoney/login">
                  Login
                </Button>
              </Card.Body>
            </Card>
          </Col>
          <Col>
            <Card style={{ width: "18rem" }}>
              <Card.Header>
                <IconAtom2
                  size={36}
                  color="red"
                  stroke={3}
                  strokeLinejoin="miter"
                />
              </Card.Header>
              <Card.Body>
                <Card.Title>Register</Card.Title>
                <Card.Text>an account</Card.Text>
                <Button variant="primary" href="/jmoney/register">
                  Go
                </Button>
              </Card.Body>
            </Card>
          </Col>
          <Col>
            <Card style={{ width: "18rem" }}>
              <Card.Header>
                <Image src={JMoneyLogo} rounded fluid className="mr-3" />
              </Card.Header>
              <Card.Body>
                <Card.Title>Try</Card.Title>
                <Card.Text>the features</Card.Text>
                <Button variant="primary" href="/jmoney/dashboard/testme">
                  Test me
                </Button>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </main>
  );
};

export default LandingPage;
