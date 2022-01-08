
import React, { useState } from 'react'
import { Container, Row, Col, Form, Button, Card } from 'react-bootstrap';
import { toast } from 'react-toastify';
import { AddSituation } from '../services/situationservice';

const Man_situation = () => {

    const [values, setValues] = useState({
        code: '',
        name: ''
    });

    const [formerrors, setFormErrors] = useState({});

    const handleChange = (event) => {

        setValues((values) => ({
            ...values,
            [event.target.name]: event.target.value,
        }));
    };

    const handlerSubmit = async e => {

        e.preventDefault();

        if (validate(values)) {

            const { isOk, message } = await AddSituation({ ...values, state: 0 });

            setValues({
                code: '',
                name: '',
                state: 0
            });

            return (isOk) ? toast.info(message) : toast.error(message)
        }
    };

    const validate = () => {

        let errors = {};

        if (!values.code) {
            errors.code = "Código de Situación es requerido";
        }

        if (!values.name) {
            errors.name = "Descripción de Situación es requerido";
        }

        setFormErrors(errors);

        return (Object.keys(errors).length === 0);
    };

    return (

        <>
            <Container style={{ paddingTop: '1%' }}>
                <Row className="align-items-end">
                    <Col xs={{ order: 'last' }}></Col>
                    <Col xs>
                        <Card>
                            <Card.Body>
                                <Card.Title > Mantenimiento de Situaciones</Card.Title>
                                <Form onSubmit={handlerSubmit}>
                                    <Form.Group className="mb-3" controlId="cCodigoSituacion">
                                        <Form.Label>Código Situación</Form.Label>
                                        <Form.Control type="text" name="code" maxLength={2} placeholder="Ingrese Código Situación" value={values.code} onChange={handleChange} />
                                        {formerrors.code && (
                                            <p className="text-warning">{formerrors.code}</p>
                                        )}
                                    </Form.Group>

                                    <Form.Group className="mb-3" controlId="cDescSituacion">
                                        <Form.Label>Descripción Situación</Form.Label>
                                        <Form.Control type="text" name="name" maxLength={50} placeholder="Ingrese Descripción Situación" value={values.name} onChange={handleChange} />
                                        {formerrors.name && (
                                            <p className="text-warning">{formerrors.name}</p>
                                        )}
                                    </Form.Group>
                                    <Button variant="primary" type="submit">
                                        Guardar
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

export default Man_situation;