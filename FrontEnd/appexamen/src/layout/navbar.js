
import React from 'react';
import { Container, Navbar, Nav, Button, Form } from 'react-bootstrap';
import { useLocation } from 'react-router-dom';

const NavbarNew = ({ infoUser, removeToken, removeInfo, setToken, setInfo }) => {

    const location = useLocation();

    const LogOut = () => {
        removeToken();
        removeInfo();

        setToken('');
        setInfo('');
    }

    return (
        <>
            <Navbar bg="light" variant="light">
                <Container>
                    <Nav activeKey={location.pathname} className="me-auto">
                        <Nav.Link href="/">Inicio</Nav.Link>
                        <Nav.Link href="/inscription-list">Inscripciones</Nav.Link>
                        <Nav.Link href="/situation-list">Situacion</Nav.Link>
                    </Nav>
                    <Navbar.Collapse className="justify-content-end">
                        <Navbar.Text>
                            Inicio Sesión: <a>{infoUser}</a>
                        </Navbar.Text>
                        <Form className="d-flex">
                            <div className="me-2" />
                            <Button variant="outline-success" onClick={LogOut}>Cerrar Sesión</Button>
                        </Form>

                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    )
}

export default NavbarNew;