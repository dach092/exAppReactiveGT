
import React from 'react'
import { Container, Row, Col, Card } from 'react-bootstrap';

const Index = () => {
    return (
        <>
            <Container style={{ paddingTop: '1%' }}>
                <Row className="align-items-end">
                    <Col xs={{ order: 'last' }}></Col>
                    <Col xs>
                        <Card>
                            <Card.Body>
                                <Card.Title > Examen Galaxy Training</Card.Title>
                                <a>Alumno: David Anderson Cruz Huertas</a><br />
                                <a>Correo: dach_092@hotmail.com</a>
                            </Card.Body>
                        </Card>
                    </Col>
                    <Col xs={{ order: 'first' }}></Col>
                </Row>
            </Container>
        </>
    )
}

export default Index;