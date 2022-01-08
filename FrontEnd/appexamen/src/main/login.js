
import React, { useState } from 'react';
import { Button, Card, Container, Form, Row, Col } from 'react-bootstrap';
import { LoginUser } from '../services/loginservice';

const Login = ({ setToken, setInfo }) => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handlerSubmit = async e => {
        e.preventDefault();

        const { token, info } = await LoginUser({ username, password });

        setToken(token);
        setInfo(info);
    }

    return (
        <>
            <Container style={{ paddingTop: '10%' }}>
                <Row className="justify-content-center">
                    <Col xs={{ order: 'last' }}></Col>
                    <Col xs>
                        <Card
                            bg={'light'}
                            text={'Dark'}
                        >
                            <Card.Header>Inicio de sesión Examen App</Card.Header>
                            <Card.Body>
                                <Form onSubmit={handlerSubmit}>
                                    <Form.Group className="mb-3" controlId="cUsername">
                                        <Form.Label>Usuario</Form.Label>
                                        <Form.Control type="text" placeholder="Ingrese usuario" onChange={e => setUsername(e.target.value)} />
                                    </Form.Group>
                                    <Form.Group className="mb-3" controlId="cPassword">
                                        <Form.Label>Contraseña</Form.Label>
                                        <Form.Control type="password" placeholder="Ingrese contraseña" onChange={e => setPassword(e.target.value)} />
                                    </Form.Group>
                                    <Button variant="primary" type="submit">
                                        Ingresar
                                    </Button>
                                </Form>
                            </Card.Body>
                        </Card>
                    </Col>
                    <Col xs={{ order: 'first' }}></Col>
                </Row>
            </Container>
        </>
    )
}

export default Login;