import React, { useState, useEffect } from "react";
import {
  Nav,
  Navbar,
  Button,
  FormControl,
  Form,
  Image,
  Container,
} from "react-bootstrap";
import { useRoutes } from "hookrouter";
import Routes from "../routes/AllPrivateRoutes";

import JarsService from "../services/JarsService";
import AuthService from "../services/AuthService";

import JMoneyLogo from "../images/logo.svg";

const Dashboard = (props) => {
  const [content, setContent] = useState("");

  const routeResult = useRoutes(Routes);

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
  }, []);

  return (
    <main>
      <Navbar bg="dark" expand="lg" variant="dark">
        <Image src={JMoneyLogo} rounded fluid className="mr-3" />
        <Navbar.Brand>JMoney</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="/jmoney/dashboard">Dashboard</Nav.Link>
            <Nav.Link href="/jmoney/jars">Jars</Nav.Link>
            <Nav.Link href="/jmoney/income">Income</Nav.Link>
            <Nav.Link href="/jmoney/forecasts">Forecasts</Nav.Link>
            <Nav.Link href="/jmoney/finance">Finance</Nav.Link>
            <Nav.Link href="/jmoney/howtosearch">How to search</Nav.Link>
          </Nav>
          <Form inline>
            <FormControl type="text" placeholder="Search" className="mr-sm-2" />
            <Button variant="outline-success">Search</Button>
          </Form>
        </Navbar.Collapse>
      </Navbar>

      <Container>{routeResult}</Container>
    </main>
  );
};

export default Dashboard;
