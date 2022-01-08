
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';

import './App.css';

import useToken from './hooks/useToken';
import useInfo from './hooks/useInfo';
import MenuNavBar from './layout/navbar';

import Login from './main/login';
import Index from './pages/index';
import ListadoAlumno from './pages/list-student';
import ListadoInscripciones from './pages/list-inscription';
import ListadoSituacion from './pages/list-situation';
import MantSituacion from './pages/man-situation';

const App = () => {

  const { token, setToken } = useToken();

  const { info, setInfo } = useInfo();

  if (!token) {
    return <Login setToken={setToken} setInfo={setInfo} />
  }

  return (
    <div className="App">
      <BrowserRouter>
        <MenuNavBar infoUser={info} />

        <Routes>
          <Route exact path="/index" element={<Index />} />
          <Route exact path="/inscription-list" element={<ListadoInscripciones />} />
          <Route exact path="/situation-list" element={<ListadoSituacion />} />
          <Route exact path="/student-list" element={<ListadoAlumno />} />
          <Route exact path="/situation-mant" element={<MantSituacion />} />
        </Routes>

      </BrowserRouter>
      <ToastContainer hideProgressBar={false} closeOnClick autoClose={2000} hideProgressBar={true} />
    </div>
  );
}

export default App;
