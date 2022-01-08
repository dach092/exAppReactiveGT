
import axios from 'axios';
import { URL_BASE, API_INSCRIPTION } from '../configuration/uri';

const getHeaders = () => {

    const tokenString = sessionStorage.getItem('token');
    const userToken = JSON.parse(tokenString);

    const headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${userToken}`
    };

    return { headers }
}

export const AddInscription = async (inscripcion) => {

    return axios.post(`${URL_BASE}${API_INSCRIPTION}`, inscripcion, {
        headers: getHeaders().headers
    }).then(response => {

        if (response.status === 201) {
            return {
                isOk: true,
                message: 'Guardado correctamente.'
            }
        }

        return {
            isOk: false,
            message: 'No se realizo el guardado.'
        }

    });
}

export const UpdateInscription = async (inscripcion, id) => {

    return axios.put(`${URL_BASE}${API_INSCRIPTION}/${id}`, inscripcion, {
        headers: getHeaders().headers
    }).then(response => {

        if (response.status === 200) {
            return {
                isOk: true,
                message: 'Guardado correctamente.'
            }
        }

        return {
            isOk: false,
            message: 'No se realizo el guardado.'
        }

    });
}

export const DeleteInscription = async (id) => {
    return axios.delete(`${URL_BASE}${API_INSCRIPTION}/${id}`, {
        headers: getHeaders().headers
    }).then(response => {

        if (response.status === 200) {
            return {
                isOk: true,
                message: 'Elimino correctamente.'
            }
        }

        return {
            isOk: false,
            message: 'No se elimino.'
        }

    });
}

export const ListInscription = async () => {

    return axios.get(`${URL_BASE}${API_INSCRIPTION}`, {
        headers: getHeaders().headers
    }).then(response => {

        if (response.status === 200 && response.data.length > 0) {
            return {
                isOk: true,
                message: 'Registros encontrados.',
                data: response.data
            }
        }

        return {
            isOk: false,
            message: 'No se encontro registros.',
            data: []
        }

    });
}

export const SearchInscriptionById = async (id) => {

    return axios.get(`${URL_BASE}${API_INSCRIPTION}/${id}`, {
        headers: getHeaders().headers
    }).then(response => {

        if (response.status === 200) {
            return {
                isOk: true,
                message: 'Registros encontrados.',
                data: response.data
            }
        }

        return {
            isOk: false,
            message: 'No se encontro registros.',
            data: {}
        }

    });
}