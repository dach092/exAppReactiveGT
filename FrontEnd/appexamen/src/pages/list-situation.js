
import React, { useEffect, useState } from 'react'
import { Container, Row, Button, Col, Table } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import { ListSituation, DeleteSituation } from '../services/situationservice';

const Listado_situacion = () => {

    const [listSituation, setListSituation] = useState([]);

    const renderDefault = async () => {

        const { isOk, data, message } = await ListSituation();

        if (isOk) {
            toast.info(message);
            setListSituation(data);
        } else {
            toast.error(message);
            setListSituation([]);
        }
    };

    const deleteHandler = async (id) => {

        const { isOk, message } = await DeleteSituation(id);

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
                        <Link to="/situation-mant/new">
                            <Button variant="primary">Agregar Situacion</Button>
                        </Link>
                    </Col>
                </Row>
                <Row>
                    <Col xs={{ order: 'last' }}></Col>
                    <Col xs>
                        <p style={{ fontWeight: 'bold', fontSize: '20px', paddingTop: '1%' }}>Listado de Situaciones</p>
                    </Col>
                    <Col xs={{ order: 'first' }}></Col>
                </Row>
                <Row>
                    <Col>
                        <Table striped bordered hover>
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    listSituation.map(situation => (
                                        (
                                            <tr key={situation.id}>
                                                <td>{situation.code}</td>
                                                <td>{situation.name}</td>
                                                <td>
                                                    <Link to={`/situation-mant/${situation.id}`}>
                                                        <Button variant="secondary">Editar</Button>
                                                    </Link>
                                                    <Button variant="warning" style={{ marginLeft: '1%' }} onClick={e => deleteHandler(situation.id)} >Eliminar</Button>
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

export default Listado_situacion;