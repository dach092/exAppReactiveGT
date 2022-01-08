
import React from 'react';
import { Container, Navbar, Nav, Button, Form } from 'react-bootstrap';

const NavbarNew = ({ infoUser }) => {
    return (
        <>
            <Navbar bg="light" variant="light">
                <Container>
                    <Nav className="me-auto">
                        <Nav.Link href="/index">Inicio</Nav.Link>
                        <Nav.Link href="/inscription-list">Inscripciones</Nav.Link>
                        <Nav.Link href="/situation-list">Situacion</Nav.Link>
                    </Nav>
                    <Navbar.Collapse className="justify-content-end">
                        <Navbar.Text>
                            Inicio Sesión: <a>{infoUser}</a>
                        </Navbar.Text>
                        <Form className="d-flex">
                            <div className="me-2" />
                            <Button variant="outline-success">Cerrar Sesión</Button>
                        </Form>

                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    )
}

export default NavbarNew;