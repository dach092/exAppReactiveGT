
import React, { useEffect, useState } from 'react'
import { Container, Row, Button, Col, Table, Form } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';

import { ListInscription, DeleteInscription } from '../services/inscriptionservice';
import { ListSituation } from '../services/situationservice';

import Pagination from '../common/pagination';

const Listado_inscripciones = () => {

    const [currentPage, setCurrentPage] = useState(1);

    const [pages, setPages] = useState(5);

    const [listInscription, setListInscription] = useState([]);

    const [listSituation, setListSituation] = useState([]);

    const [filtroName, setFiltroName] = useState('');

    const [filtroSituacion, setFiltroSituacion] = useState('');

    const getListSituacion = async () => {
        const { data } = await ListSituation();

        setListSituation(data);
    }

    const handlerFiltroName = (event) => {
        setFiltroName(event.target.value);
    }

    const handlerFiltroSituacion = (event) => {
        setFiltroSituacion(event.target.value);
    }

    const handleSizeRegChange = (event) => {
        setPages(event.target.value);
    }

    const handlerLimpiar = async e => {

        e.preventDefault();

        setFiltroName('');
        setFiltroSituacion('');
    }

    const handlerBuscar = async e => {
        e.preventDefault();
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

    const indexLast = currentPage * pages;
    const indexFirst = indexLast - pages;

    const filterInscriptions = () => {

        if (filtroName.length === 0 && (filtroSituacion === '-1' || !filtroSituacion))
            return listInscription.slice(indexFirst, indexLast);

        if (filtroName.length > 0 && (filtroSituacion === '-1' || !filtroSituacion))
            return listInscription.filter(inscripcion => inscripcion.name.toLowerCase().indexOf(filtroName.toLowerCase()) > -1).slice(indexFirst, indexLast);

        if (filtroName.length === 0 && (filtroSituacion !== '-1' || filtroSituacion))
            return listInscription.filter(inscripcion => inscripcion.situationcode.includes(filtroSituacion)).slice(indexFirst, indexLast);;

        if (filtroName.length > 0 && (filtroSituacion !== '-1' || filtroSituacion))
            return listInscription.filter(inscripcion => (inscripcion.name.toLowerCase().indexOf(filtroName.toLowerCase()) > -1)
                && inscripcion.situationcode.includes(filtroSituacion)).slice(indexFirst, indexLast);
    }

    const paginate = pageNumber => setCurrentPage(pageNumber);

    const previousPage = pageNumber => setCurrentPage(pageNumber - 1);

    const nextPage = pageNumber => setCurrentPage(pageNumber + 1);

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
                        <Form.Group className="mb-3">
                            <Form.Label>Nombre del taller</Form.Label>
                            <Form.Control type="text" name="filtroSituacion" maxLength={50} value={filtroName} onChange={handlerFiltroName} />
                        </Form.Group>
                    </Col>
                    <Col>
                        <Form.Group className="mb-3">
                            <Form.Label>Situacion</Form.Label>
                            <Form.Select name="filtroName" value={filtroSituacion} onChange={handlerFiltroSituacion}>
                                <option value="-1">---SELECCIONE---</option>
                                {
                                    listSituation.map(situacion => (
                                        (
                                            <option key={situacion.id} value={situacion.code}>{situacion.name}</option>
                                        )
                                    ))
                                }
                            </Form.Select>
                        </Form.Group>
                    </Col>
                    <Col style={{ paddingTop: '2.4%' }}>
                        <Button variant="primary" type="button" onClick={handlerBuscar}>Buscar</Button>
                        <Button variant="secondary" type="button" style={{ marginLeft: '1%' }} onClick={handlerLimpiar}>Limpiar</Button>
                    </Col>
                </Row>
                <Row>
                    <Col md="auto" style={{ paddingTop: '0.5%' }}><a>Mostrar</a></Col>
                    <Col md="auto">
                        <Form.Group className="mb-3">
                            <Form.Select name="pages" value={pages} onChange={handleSizeRegChange}>
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="15">15</option>
                                <option value="20">20</option>
                            </Form.Select>
                        </Form.Group>
                    </Col>
                    <Col md="auto" style={{ paddingTop: '0.5%' }}><a>Registros</a></Col>
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
                                    filterInscriptions().map(inscripcion => (
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
                <Row>
                    <Col>
                        <Pagination
                            pages={pages}
                            total={listInscription.length}
                            paginate={paginate}
                            currentPage={currentPage}
                            previousPage={previousPage}
                            nextPage={nextPage}
                        />
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default Listado_inscripciones;
