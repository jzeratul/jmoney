import React from 'react'
import { Navbar, Nav, NavDropdown } from 'react-bootstrap'

const JMoneyMenu = props => {
  return (
    <Navbar bg="dark" expand="sm" variant="dark" fixed="top">
    <Navbar.Brand href="#home">JMoney :)</Navbar.Brand>
    <Navbar.Toggle aria-controls="basic-navbar-nav" />
    <Navbar.Collapse id="basic-navbar-nav">
      <Nav className="mr-auto">
        <Nav.Link href="#home">Dashboard</Nav.Link>
        <Nav.Link href="#home">Income</Nav.Link>
      </Nav>
      <Nav>
        <NavDropdown title={props.currentUser.username} id="basic-nav-dropdown">
          <NavDropdown.Item href="#action/logout">Logout</NavDropdown.Item>
        </NavDropdown>
        <Nav.Link href=""></Nav.Link>
      </Nav>
    </Navbar.Collapse>
  </Navbar>
  )
}

export default JMoneyMenu
