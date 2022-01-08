
import React, { useEffect, useState } from 'react'

import { Container, Row, Col, Form, Button, Card } from 'react-bootstrap';
import { Link, useParams, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

import { ListSituation } from '../services/situationservice';
import { AddInscription, SearchInscriptionById, UpdateInscription } from '../services/inscriptionservice';

const Man_inscription = () => {

    const { id } = useParams();

    const navigate = useNavigate();

    const [listSituation, setListSituation] = useState([]);

    const [values, setValues] = useState({
        code: '',
        name: '',
        startdate: '',
        situationcode: '',
        numberrecord: 0
    });

    const [instructor, setInstructor] = useState({
        code: '',
        firstname: '',
        lastname: '',
        age: 0,
        address: '',
        email: '',
    })

    const [speciality, setSpeciality] = useState({
        code: '',
        description: ''
    })

    const [formerrors, setFormErrors] = useState({});

    const loadForUpdate = async () => {
        const { isOk, data } = await SearchInscriptionById(id);

        if (isOk && id !== 'new') {
            setValues({
                code: data.code,
                name: data.name,
                startdate: new Intl.DateTimeFormat('fr-CA').format(data.startdate).toString(),
                situationcode: data.situationcode,
                numberrecord: data.numberrecord
            });

            setInstructor({
                code: data.instructor.code,
                firstname: data.instructor.firstname,
                lastname: data.instructor.lastname,
                age: data.instructor.age,
                address: data.instructor.address,
                email: data.instructor.email,
            });

            setSpeciality({
                code: data.instructor.speciality.code,
                description: data.instructor.speciality.description
            });
        }
    };

    const handleChange = (event) => {

        setValues((values) => ({
            ...values,
            [event.target.name]: event.target.value,
        }));
    };

    const handleChangeInstructor = (event) => {

        setInstructor((instructor) => ({
            ...instructor,
            [event.target.name]: event.target.value,
        }));
    };

    const handleChangeSpeciality = (event) => {

        setSpeciality((speciality) => ({
            ...speciality,
            [event.target.name]: event.target.value,
        }));
    };

    const clearAll = () => {
        setValues({
            code: '',
            name: '',
            startdate: '',
            situationcode: '',
            numberrecord: 0
        });

        setInstructor({
            code: '',
            firstname: '',
            lastname: '',
            age: 0,
            address: '',
            email: '',
        });

        setSpeciality({
            code: '',
            description: ''
        });
    };

    const getFechaFormato = (prefijo) => {
        const today = new Date();
        const newDate = `${prefijo}${today.getFullYear()}${today.getMonth()}${today.getDay()}${today.getHours()}${today.getMinutes()}${today.getSeconds()}`;

        return newDate
    }

    const newInscription = async () => {

        const { isOk, message } = await AddInscription({
            ...values, state: 0, instructor: {
                ...instructor,
                code: getFechaFormato('I'),
                speciality: {
                    ...speciality,
                    code: getFechaFormato('S')
                }
            }
        });

        clearAll();
        (isOk) ? toast.info(message) : toast.error(message)
    };

    const updateInscription = async () => {
        const { isOk, message } = await UpdateInscription({
            ...values, state: 0, instructor: {
                ...instructor,
                speciality: {
                    ...speciality
                }
            }
        }, id);

        clearAll();
        (isOk) ? toast.info(message) : toast.error(message)

        navigate("/inscription-list");
    };

    const handlerSubmit = async e => {

        e.preventDefault();

        if (validate()) {

            if (id === 'new') {
                newInscription();
            } else {
                updateInscription();
            }
        }
    };

    const renderDefault = async () => {
        const { data } = await ListSituation();
        setListSituation(data);

        loadForUpdate();
    };

    const validate = () => {
        let errors = {};

        if (!values.code) {
            errors.code = "Código de Inscripción es requerido";
        }

        if (!values.name) {
            errors.name = "Nombre de Inscripción es requerido";
        }

        if (!values.startdate) {
            errors.startdate = "Fecha de Inicio es requerido";
        }

        if (!values.situationcode || values.situationcode === '-1') {
            errors.situationcode = "Situación es requerido";
        }

        if (!instructor.firstname) {
            errors.firstname = "Nombre Instructor es requerido";
        }

        if (!instructor.lastname) {
            errors.lastname = "Apellido Instructor es requerido";
        }

        if (!instructor.age) {
            errors.age = "Edad Instructor es requerido";
        } else if (instructor.age < 18) {
            errors.age = "Instructor debe ser mayor de edad";
        }

        if (!instructor.address) {
            errors.address = "Dirección Instructor es requerido";
        }

        if (!instructor.email) {
            errors.email = "Email Instructor es requerido";
        } else if (!/\S+@\S+\.\S+/.test(instructor.email)) {
            errors.email = "Email Instructor es invalido";
        }

        if (!speciality.description) {
            errors.description = "Especialidad Instructor es requerido";
        }

        setFormErrors(errors);

        return (Object.keys(errors).length === 0);
    }

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
                                <Card.Title> Registro Inscripciones </Card.Title>
                                <Form onSubmit={handlerSubmit}>
                                    <Row>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Código Inscripción</Form.Label>
                                                <Form.Control type="text" name="code" maxLength={5} placeholder="Ingrese Código Inscripción" value={values.code} onChange={handleChange} />
                                                {formerrors.code && (
                                                    <p className="text-warning">{formerrors.code}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Nombre Inscripción</Form.Label>
                                                <Form.Control type="text" name="name" maxLength={50} placeholder="Ingrese Nombre Inscripción" value={values.name} onChange={handleChange} />
                                                {formerrors.name && (
                                                    <p className="text-warning">{formerrors.name}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Fecha Inicio</Form.Label>
                                                <Form.Control type="date" name="startdate" value={values.startdate} onChange={handleChange} />
                                                {formerrors.startdate && (
                                                    <p className="text-warning">{formerrors.startdate}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Situacion</Form.Label>
                                                <Form.Select name="situationcode" value={values.situationcode} onChange={handleChange}>
                                                    <option value="-1">---SELECCIONE---</option>
                                                    {
                                                        listSituation.map(situacion => (
                                                            (
                                                                <option key={situacion.id} value={situacion.code}>{situacion.name}</option>
                                                            )
                                                        ))
                                                    }
                                                </Form.Select>
                                                {formerrors.situationcode && (
                                                    <p className="text-warning">{formerrors.situationcode}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <hr />
                                    </Row>
                                    <Row>
                                        <Col>
                                            <Form.Label className="h6">Información Instructor</Form.Label>
                                        </Col>
                                        <Col></Col>
                                    </Row>
                                    <Row>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Nombre Instructor</Form.Label>
                                                <Form.Control type="text" name="firstname" maxLength={50} placeholder="Ingrese Nombre Instructor" value={instructor.firstname} onChange={handleChangeInstructor} />
                                                {formerrors.firstname && (
                                                    <p className="text-warning">{formerrors.firstname}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Apellido Instructor</Form.Label>
                                                <Form.Control type="text" name="lastname" maxLength={50} placeholder="Ingrese Apellido Instructor" value={instructor.lastname} onChange={handleChangeInstructor} />
                                                {formerrors.lastname && (
                                                    <p className="text-warning">{formerrors.lastname}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Edad Instructor</Form.Label>
                                                <Form.Control type="number" name="age" maxLength={2} placeholder="Ingrese Edad Instructor" value={instructor.age} onChange={handleChangeInstructor} />
                                                {formerrors.age && (
                                                    <p className="text-warning">{formerrors.age}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Dirección Instructor</Form.Label>
                                                <Form.Control type="text" name="address" maxLength={250} placeholder="Ingrese Direccion Instructor" value={instructor.address} onChange={handleChangeInstructor} />
                                                {formerrors.address && (
                                                    <p className="text-warning">{formerrors.address}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Email Instructor</Form.Label>
                                                <Form.Control type="email" name="email" maxLength={50} placeholder="Ingrese Correo Instructor" value={instructor.email} onChange={handleChangeInstructor} />
                                                {formerrors.email && (
                                                    <p className="text-warning">{formerrors.email}</p>
                                                )}
                                            </Form.Group>
                                        </Col>
                                        <Col>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Especialidad</Form.Label>
                                                <Form.Control type="text" name="description" maxLength={50} placeholder="Ingrese Especialidad" value={speciality.description} onChange={handleChangeSpeciality} />
                                                {formerrors.description && (
                                                    <p className="text-warning">{formerrors.description}</p>
                                                )}
                                            </Form.Group>
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

export default Man_inscription;