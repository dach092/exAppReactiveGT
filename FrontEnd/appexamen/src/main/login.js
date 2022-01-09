
import React, { useState } from 'react';
import { Button, Card, Container, Form, Row, Col } from 'react-bootstrap';
import { toast, ToastContainer } from 'react-toastify';

import { LoginUser } from '../services/loginservice';

const Login = ({ setToken, setInfo }) => {

    const [formerrors, setFormErrors] = useState({});

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handlerSubmit = async e => {
        e.preventDefault();

        if (!validate())
            return;

        const { token, info, isOk, message } = await LoginUser({ username, password });

        if (!isOk) {
            toast.error(message);
            return;
        }

        setToken(token);
        setInfo(info);
    };

    const validate = () => {
        let errors = {};

        if (!username) {
            errors.username = "Usuario es requerido";
        }

        if (!password) {
            errors.password = "Contraseña es requerido";
        }

        setFormErrors(errors);

        return (Object.keys(errors).length === 0);
    };

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
                                        <Form.Control type="text" placeholder="Ingrese usuario" name="username" value={username} onChange={e => setUsername(e.target.value)} />
                                        {formerrors.username && (
                                            <p className="text-warning">{formerrors.username}</p>
                                        )}
                                    </Form.Group>
                                    <Form.Group className="mb-3" controlId="cPassword">
                                        <Form.Label>Contraseña</Form.Label>
                                        <Form.Control type="password" placeholder="Ingrese contraseña" name="password" value={password} onChange={e => setPassword(e.target.value)} />
                                        {formerrors.password && (
                                            <p className="text-warning">{formerrors.password}</p>
                                        )}
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
            <ToastContainer hideProgressBar={false} autoClose={2000} hideProgressBar={true} />
        </>
    )
}

export default Login;