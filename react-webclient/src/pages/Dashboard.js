import React from "react";
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

import JMoneyLogo from "../images/logo.svg";

const Dashboard = (props) => {
  const routeResult = useRoutes(Routes);

  return (
    <main>
      <Navbar bg="dark" expand="lg" variant="dark">
        <Image src={JMoneyLogo} rounded fluid className="mr-3" />
        <Navbar.Brand>JMoney</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="/jmoney/dashboard">Dashboard</Nav.Link>
            <Nav.Link href="/jmoney/dashboard/jars">Jars</Nav.Link>
            <Nav.Link href="/jmoney/dashboard/income">Income</Nav.Link>
            <Nav.Link href="/jmoney/dashboard/forecasts">Forecasts</Nav.Link>
            <Nav.Link href="/jmoney/dashboard/finance">Finance</Nav.Link>
            <Nav.Link href="/jmoney/dashboard/howtosearch">
              How to search
            </Nav.Link>
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
