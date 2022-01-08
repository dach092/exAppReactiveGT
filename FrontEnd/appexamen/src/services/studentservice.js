
import axios from 'axios';
import { URL_BASE, API_STUDENT } from '../configuration/uri';


export const AddStudent = async (estudiante) => {

    return axios.post(`${URL_BASE}${API_STUDENT}`, estudiante, {
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

const getHeaders = () => {

    const tokenString = sessionStorage.getItem('token');
    const userToken = JSON.parse(tokenString);

    const headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${userToken}`
    };

    return { headers }
}