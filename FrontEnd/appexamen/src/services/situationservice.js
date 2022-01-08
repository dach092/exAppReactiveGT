
import axios from 'axios';
import { URL_BASE, API_SITUATION } from '../configuration/uri';

export const AddSituation = async (situacion) => {

    return axios.post(`${URL_BASE}${API_SITUATION}`, situacion, {
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

export const UpdateSituation = async (situacion, id) => {

    return axios.put(`${URL_BASE}${API_SITUATION}/${id}`, situacion, {
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

export const DeleteSituation = async (id) => {
    return axios.delete(`${URL_BASE}${API_SITUATION}/${id}`, {
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

export const ListSituation = async () => {

    return axios.get(`${URL_BASE}${API_SITUATION}`, {
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

export const SearchSituationById = async (id) => {

    return axios.get(`${URL_BASE}${API_SITUATION}/${id}`, {
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

export const getHeaders = () => {

    const tokenString = sessionStorage.getItem('token');
    const userToken = JSON.parse(tokenString);

    const headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${userToken}`
    };

    return { headers }
}