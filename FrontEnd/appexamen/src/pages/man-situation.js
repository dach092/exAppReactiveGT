
import React, { useEffect, useState } from 'react'
import { Container, Row, Col, Form, Button, Card } from 'react-bootstrap';
import { Link, useParams, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { AddSituation, SearchSituationById, UpdateSituation } from '../services/situationservice';

const Man_situation = () => {

    const { id } = useParams();

    const navigate = useNavigate();

    const [values, setValues] = useState({
        code: '',
        name: ''
    });

    const [formerrors, setFormErrors] = useState({});

    const loadForUpdate = async () => {

        const { isOk, data } = await SearchSituationById(id);

        if (isOk && id !== 'new') {
            setValues({
                code: data.code,
                name: data.name
            });
        }
    };

    const handleChange = (event) => {

        setValues((values) => ({
            ...values,
            [event.target.name]: event.target.value,
        }));
    };

    const newSituation = async () => {

        const { isOk, message } = await AddSituation({ ...values, state: 0 });
        setValues({ code: '', name: '' });
        (isOk) ? toast.info(message) : toast.error(message)
    }

    const updateSituation = async () => {

        const { isOk, message } = await UpdateSituation({ ...values, state: 0 }, id);
        setValues({ code: '', name: '' });
        (isOk) ? toast.info(message) : toast.error(message)

        navigate("/situation-list");
    }

    const handlerSubmit = async e => {

        e.preventDefault();

        if (validate()) {

            if (id === 'new') {
                newSituation();
            } else {
                updateSituation();
            }
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

    useEffect(() => {
        loadForUpdate();
    }, []);

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
                                        <Form.Control type="text" name="code" maxLength={5} placeholder="Ingrese Código Situación" value={values.code} onChange={handleChange} />
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
                                    <Link to="/situation-list">
                                        <Button variant="danger" type="button" style={{ marginLeft: '1%' }}>
                                            Cancelar
                                        </Button>
                                    </Link>
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