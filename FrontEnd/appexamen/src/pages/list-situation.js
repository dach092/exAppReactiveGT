
import React from 'react'
import { Container, Row, Button, Col, Table } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const Listado_situacion = () => {
    return (
        <>
            <Container style={{ paddingTop: '1%' }}>
                <Row>
                    <Col sm={4}>
                        <Link to="/situation-mant">
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
                                    <th>#</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Username</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Mark</td>
                                    <td>Otto</td>
                                    <td>@mdo</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jacob</td>
                                    <td>Thornton</td>
                                    <td>@fat</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td colSpan={2}>Larry the Bird</td>
                                    <td>@twitter</td>
                                </tr>
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default Listado_situacion;