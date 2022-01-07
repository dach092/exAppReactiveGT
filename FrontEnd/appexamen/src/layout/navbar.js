
import React from 'react';
import { Container, Navbar, Nav, Button, Form } from 'react-bootstrap';

const NavbarNew = () => {
    return (
        <>
            <Navbar bg="light" variant="light">
                <Container>
                    <Nav className="me-auto">
                        <Nav.Link href="/index">Inicio</Nav.Link>
                        <Nav.Link href="/inscripcion-list">Inscripciones</Nav.Link>
                        <Nav.Link href="/situacion-list">Situacion</Nav.Link>
                        <Nav.Link href="/alumno-list">Alumno</Nav.Link>
                    </Nav>
                    <Navbar.Collapse className="justify-content-end">
                        <Navbar.Text>
                            Inicio Sesión: <a>David Cruz Huertas</a>
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