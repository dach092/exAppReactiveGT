
import axios from 'axios';
import { URL_BASE, API_LOGIN } from '../configuration/uri';

export const LoginUser = async (usuario) => {

    return axios.post(`${URL_BASE}${API_LOGIN}`, usuario, {
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {

        if (response.status === 200) {
            return {
                token: response.headers.authorization.split(' ')[1],
                info: `${response.data.firstname} ${response.data.lastname}`,
                isOk: true,
                message: ''
            }
        }

        return {
            token: '',
            info: '',
            isOk: false,
            message: 'Credenciales invalidas'
        }
        
    }).catch(error => {

        return {
            token: '',
            info: '',
            isOk: false,
            message: 'Credenciales invalidas'
        }
    });
}