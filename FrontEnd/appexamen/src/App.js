
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';

import './App.css';

import useToken from './hooks/useToken';
import useInfo from './hooks/useInfo';
import MenuNavBar from './layout/navbar';

import Login from './main/login';
import Index from './pages/index';
import ListadoInscripciones from './pages/list-inscription';
import ListadoSituacion from './pages/list-situation';
import MantSituacion from './pages/man-situation';
import MantInscripcion from './pages/man-inscription';
import MantEstudiante from './pages/man-student';

const App = () => {

  const { token, setToken, removeToken } = useToken();

  const { info, setInfo, removeInfo } = useInfo();

  if (!token) {
    return <Login setToken={setToken} setInfo={setInfo} />
  }

  return (
    <div className="App">
      <BrowserRouter>
        <MenuNavBar infoUser={info} removeToken={removeToken} removeInfo={removeInfo} setToken={setToken} setInfo={setInfo} />

        <Routes>
          <Route exact path="/" element={<Index />} />
          <Route exact path="/inscription-list" element={<ListadoInscripciones />} />
          <Route exact path="/situation-list" element={<ListadoSituacion />} />
          <Route exact path="/situation-mant/:id" element={<MantSituacion />} />
          <Route exact path="/inscription-mant/:id" element={<MantInscripcion />} />
          <Route exact path="/student-mant/:id" element={<MantEstudiante />} />
        </Routes>

      </BrowserRouter>
      <ToastContainer hideProgressBar={false} autoClose={2000} hideProgressBar={true} />
    </div>
  );
}

export default App;

