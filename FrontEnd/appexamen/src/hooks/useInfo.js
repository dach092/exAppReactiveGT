
import { useState } from 'react'

const UseInfo = () => {

    const getInfo = () => {
        const InfoString = sessionStorage.getItem('info');
        const InfoUser = JSON.parse(InfoString);

        return InfoUser;
    };

    const [info, setInfo] = useState(getInfo());

    const saveInfo = (userInfo) => {
        sessionStorage.setItem('info', JSON.stringify(userInfo));
        setInfo(userInfo);
    };

    return {
        setInfo: saveInfo,
        info
    }
}

export default UseInfo;