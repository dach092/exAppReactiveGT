
import React, { useEffect, useState } from 'react'
import { Container, Row, Button, Col, Table } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';

import { ListInscription, DeleteInscription } from '../services/inscriptionservice';
import { ListSituation } from '../services/situationservice';

const Listado_inscripciones = () => {

    const [listInscription, setListInscription] = useState([]);

    const [listSituation, setListSituation] = useState([]);

    const getListSituacion = async () => {
        const { data } = await ListSituation();

        setListSituation(data);
    }

    const renderDefault = async () => {

        const { isOk, data, message } = await ListInscription();

        if (isOk) {
            toast.info(message);
            setListInscription(data);
        } else {
            toast.error(message);
            setListInscription([]);
        }

        getListSituacion();
    };

    const deleteHandler = async (id) => {

        const { isOk, message } = await DeleteInscription(id);

        if (isOk) {
            toast.info(message);
        } else {
            toast.error(message);
        }

        renderDefault();
    }

    useEffect(() => {
        renderDefault();
    }, []);

    return (
        <>
            <Container style={{ paddingTop: '1%' }}>
                <Row>
                    <Col sm={4}>
                        <Link to="/inscription-mant/new">
                            <Button variant="primary">Agregar Inscripción</Button>
                        </Link>
                    </Col>
                </Row>
                <Row>
                    <Col xs={{ order: 'last' }}></Col>
                    <Col xs>
                        <p style={{ fontWeight: 'bold', fontSize: '20px', paddingTop: '1%' }}>Listado de Inscripciones</p>
                    </Col>
                    <Col xs={{ order: 'first' }}></Col>
                </Row>
                <Row>
                    <Col>
                        <Table striped bordered hover>
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Taller</th>
                                    <th>Fecha</th>
                                    <th>Instructor</th>
                                    <th>Inscritos</th>
                                    <th>Situacion</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    listInscription.map(inscripcion => (
                                        (
                                            <tr key={inscripcion.id}>
                                                <td>{inscripcion.code}</td>
                                                <td>{inscripcion.name}</td>
                                                <td>
                                                    {new Intl.DateTimeFormat("en-GB").format(inscripcion.startdate)}
                                                </td>
                                                <td>{inscripcion.instructor.firstname} {inscripcion.instructor.lastname}</td>
                                                <td>{inscripcion.numberrecord}</td>
                                                <td>
                                                    {
                                                        listSituation.filter(item => item.code.includes(inscripcion.situationcode))
                                                            .map(item => item.name)
                                                    }
                                                </td>
                                                <td>
                                                    <Link to={`/student-mant/${inscripcion.id}`}>
                                                        <Button variant="success">Inscribir</Button>
                                                    </Link>

                                                    <Link style={{ marginLeft: '1%' }} to={`/inscription-mant/${inscripcion.id}`}>
                                                        <Button variant="secondary">Editar</Button>
                                                    </Link>

                                                    <Button variant="warning" style={{ marginLeft: '1%' }} onClick={e => deleteHandler(inscripcion.id)} >Eliminar</Button>
                                                </td>
                                            </tr>
                                        )
                                    ))
                                }
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default Listado_inscripciones;
