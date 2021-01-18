import React, { useState } from "react";
import { Nav, Navbar, Button, FormControl, Form, Image, Container } from "react-bootstrap";

import DashboardContents from "../components/DashboardContents";
import Jars from "../components/Jars";
import Income from "../components/Income";
import Forecasts from "../components/Forecasts";
import Finance from "../components/Finance";
import HowToSearch from "../components/HowToSearch";

import JMoneyLogo from "../images/logo.svg";

const Dashboard = (props) => {
  const [link, setLink] = useState(0);

  return (
    <main>
      <Navbar bg="dark" expand="lg" variant="dark">
        <Image src={JMoneyLogo} rounded fluid className="mr-3" />
        <Navbar.Brand>JMoney</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link className={0 === link ? "active" : ""} onClick={() => setLink(0)}>
              Dashboard
            </Nav.Link>
            <Nav.Link className={1 === link ? "active" : ""} onClick={() => setLink(1)}>
              Jars
            </Nav.Link>
            <Nav.Link className={2 === link ? "active" : ""} onClick={() => setLink(2)}>
              Income
            </Nav.Link>
            <Nav.Link className={3 === link ? "active" : ""} onClick={() => setLink(3)}>
              Forecasts
            </Nav.Link>
            <Nav.Link className={4 === link ? "active" : ""} onClick={() => setLink(4)}>
              Finance
            </Nav.Link>
            <Nav.Link className={5 === link ? "active" : ""} onClick={() => setLink(5)}>
              How to search
            </Nav.Link>
          </Nav>
          <Form inline>
            <FormControl type="text" placeholder="Search" className="mr-sm-2" />
            <Button variant="outline-success">Search</Button>
          </Form>
        </Navbar.Collapse>
      </Navbar>

      <Container>
        {0 === link && <DashboardContents />}
        {1 === link && <Jars />}
        {2 === link && <Income />}
        {3 === link && <Forecasts />}
        {4 === link && <Finance />}
        {5 === link && <HowToSearch />}
      </Container>
    </main>
  );
};

export default Dashboard;
