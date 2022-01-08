
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
            message: 'Error al guardar.'
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