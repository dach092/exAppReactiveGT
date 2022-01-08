
import React, { useEffect, useState } from 'react'

import { Container, Row, Col, Form, Button, Card } from 'react-bootstrap';
import { Link, useParams, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

import { SearchInscriptionById, UpdateInscription } from '../services/inscriptionservice';
import { AddStudent } from '../services/studentservice';

const Man_student = () => {

    const { id } = useParams();

    const navigate = useNavigate();

    const [inscription, setInscription] = useState({});

    const [formerrors, setFormErrors] = useState({});

    const [values, setValues] = useState({
        firstname: '',
        lastname: '',
        age: 0,
        address: '',
        email: '',
        state: 0
    });

    const loadForInscription = async () => {
        const { data } = await SearchInscriptionById(id);

        setInscription(data);
    }

    const handleChange = (event) => {

        setValues((values) => ({
            ...values,
            [event.target.name]: event.target.value,
        }));
    };

    const clearAll = () => {
        setValues({
            firstname: '',
            lastname: '',
            age: 0,
            address: '',
            email: '',
            state: 0
        });
    };

    const addNewRegistration = async () => {

        const { isOk, message } = await AddStudent(values);

        await UpdateInscription({ ...inscription, numberrecord: inscription.numberrecord + 1 }, id);

        clearAll();
        (isOk) ? toast.info(message) : toast.error(message)

        navigate("/inscription-list");
    }

    const handlerSubmit = async e => {

        e.preventDefault();

        if (validate()) {
            addNewRegistration();
        }
    };

    const renderDefault = async () => {
        loadForInscription();
    }

    const validate = () => {
        let errors = {};

        if (!values.firstname) {
            errors.firstname = "Nombre Completo es requerido";
        }

        if (!values.lastname) {
            errors.lastname = "Apellidos Completo es requerido";
        }

        if (!values.age) {
            errors.age = "Edad es requerido";
        } else if (values.age < 18) {
            errors.age = "Estudiante debe ser mayor de edad";
        }

        if (!values.address) {
            errors.address = "Dirección es requerido";
        }

        if (!values.email) {
            errors.email = "Email es requerido";
        } else if (!/\S+@\S+\.\S+/.test(values.email)) {
            errors.email = "Email es invalido";
        }

        setFormErrors(errors);

        return (Object.keys(errors).length === 0);
    };

    useEffect(() => {
        renderDefault();
    }, []);

    return (
        <>
            <Container style={{ paddingTop: '1%' }}>
                <Row className="align-items-end">
                    <Col >
                        <Card>
                            <Card.Body>
                                <Card.Title> Registro de Estudiantes </Card.Title>
                                <Form onSubmit={handlerSubmit}>
                                    <Row>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Nombre Completo</Form.Label>
                                                <Form.Control type="text" name="firstname" maxLength={50} placeholder="Ingrese Nombre Completo" value={values.firstname} onChange={handleChange} />
                                                {formerrors.firstname && (
                                                    <p className="text-warning">{formerrors.firstname}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Apellidos Completo</Form.Label>
                                                <Form.Control type="text" name="lastname" maxLength={50} placeholder="Ingrese Apellidos Completo" value={values.lastname} onChange={handleChange} />
                                                {formerrors.lastname && (
                                                    <p className="text-warning">{formerrors.lastname}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Edad</Form.Label>
                                                <Form.Control type="number" name="age" placeholder="Ingrese Edad" value={values.age} onChange={handleChange} />
                                                {formerrors.age && (
                                                    <p className="text-warning">{formerrors.age}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Dirección</Form.Label>
                                                <Form.Control type="text" name="address" maxLength={250} placeholder="Ingrese Dirección" value={values.address} onChange={handleChange} />
                                                {formerrors.address && (
                                                    <p className="text-warning">{formerrors.address}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Email</Form.Label>
                                                <Form.Control type="email" name="email" maxLength={50} placeholder="Ingrese Correo" value={values.email} onChange={handleChange} />
                                                {formerrors.email && (
                                                    <p className="text-warning">{formerrors.email}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                        <Col>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <hr />
                                    </Row>
                                    <Button variant="primary" type="submit">
                                        Guardar
                                    </Button>
                                    <Link to="/inscription-list">
                                        <Button variant="danger" type="button" style={{ marginLeft: '1%' }}>
                                            Cancelar
                                        </Button>
                                    </Link>
                                </Form>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default Man_student;